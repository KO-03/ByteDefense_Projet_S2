/*
 * Controller.java
 * Cette classe fait le lien entre le modele et la vue, ses responsabilitees sont :
 * - initialiser le gameMaster
 * - initialiser la vue des ennemis et celle des tourelles
 * - initialiser la vue de la GameArea
 * - gerer la gameLoop et les actions du jeu (effectuer un tour et les actions des tirs)
 * - gerer les entites de vue (tourelle, plateau de jeu, grille d'ennemis et de tourelles)
 * - creer les listener sur la liste des GameObject de l'environnement de jeu
 * - creer le listener sur les byteCoin (argent du joueur) pour son affichage dynamique
 * - fixer dynamiquement l'affichage du nombre d'ennemis (l'attribut nbrEnemies) en fonction de leur evolution dans le modele
 * - gerer le glisser deposer d'une tourelle et son positionnement
 */

package byteDefense.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import byteDefense.model.Bullet;
import byteDefense.model.GameArea;
import byteDefense.model.GameEnvironment;
import byteDefense.model.GameMaster;
import byteDefense.model.LivingObject;
import byteDefense.model.enemies.Enemy;
import byteDefense.model.towers.AdCube;
import byteDefense.model.towers.Antivirus;
import byteDefense.model.towers.AuthenticationPoint;
import byteDefense.model.towers.Firewall;
import byteDefense.model.towers.SudVPN;
import byteDefense.model.towers.Tower;
import byteDefense.view.BulletView;
import byteDefense.view.EnemyView;
import byteDefense.view.GameAreaView;
import byteDefense.view.TowerView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class Controller implements Initializable {

	// Textures du bouton play/pause
	private static Image playImg;
	private static Image pauseImg;
	
	@FXML
	private TilePane gameBoard; // Plateau de jeu
	@FXML
    private Pane bulletsGrid; // Grille de tirs
	@FXML
    private Pane enemiesGrid; // Grille d'ennemis
	@FXML
    private Pane towersGrid; // Grille de tourelles
	
	// Tourelles du magasin
	@FXML
	private ImageView adcube; 
	@FXML
	private ImageView antivirus;
	@FXML
	private ImageView authenticationpoint;
	@FXML
	private ImageView firewall;
	@FXML
	private ImageView sudvpn;
	
    @FXML
    private Label waveNbr; // Numero de la vague
    @FXML
    private Label enemiesNbr; // Nombre d'ennemis vivants
    @FXML
    private Label byteCoin; // Argent du jeu
    
    // Informations des livingObjects
    @FXML
    private Label name; // Nom du livingObject
    @FXML
	private Label attackStat;
    @FXML
    private Label defenseStat;
    @FXML
    private Label attackSpeedStat;
    @FXML
    private Label attackRangeStat;
    
    // Couts dans la boutique
    @FXML
	private Label costAdcube;
    @FXML
	private Label costAntivirus;
    @FXML
	private Label costAuthPoint;
    @FXML
	private Label costFirewall;
    @FXML
	private Label costSudVPN;
    
    // Boutons de contrôles
    @FXML
    private Button launchWaveBt; // Bouton pour lancer une nouvelle vague
    @FXML
    private Button gameControls; // Bouton pour gerer le pause/play
    @FXML
    private Label timer; // Minuteur
    @FXML
    private Label message; // Message qui indique l'etat de la partie (vague en cours, en pause, entre-vague, fin de partie)
    @FXML
    private ProgressBar hpPC; //barre de vie de l'ordianteur
    
    private GameMaster gm;
    private boolean playActivated; // indique si le bouton play/pause a ete presse
	private EnemyView ev;
	private TowerView tv;
	private BulletView bv;
	private Timeline gameLoop;
	private int time;
	private int seconde;
	private int minute;

	// Methode de chargement des textures du bouton de controle
	private void loadPlayPauseImage() {
		try {
			playImg = new Image(new File("./resources/icons/play-button.png").toURI().toURL().toString());
			pauseImg = new Image(new File("./resources/icons/pause-button.png").toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	private void generateGameObjectsListener() {
		this.gm.getGameEnvironment().getTowers().addListener((ListChangeListener <Tower>) c-> {
			while (c.next()) {
				for (Tower tower : c.getAddedSubList())
					this.tv.addLivingObjectView(tower);
				for (Tower tower : c.getRemoved())
					this.tv.removeGameObjectView(tower);
			}
		});
		
		this.gm.getGameEnvironment().getEnemies().addListener((ListChangeListener <Enemy>) c-> {
			int nbEnemyDead = 0;
			int nbEnemySpawned= 0;
			
			while (c.next()) {
				for (Enemy enemy : c.getAddedSubList()) {
					this.ev.addLivingObjectView(enemy);
					nbEnemySpawned++;
				}
				for (Enemy enemy : c.getRemoved()) {
					// Gain de byteCoin quand un ennemi est tue
					if(!enemy.isAlive())
						this.gm.addMoney(enemy.getLoot());
					this.ev.removeGameObjectView(enemy);					
					nbEnemyDead++;
				}
				int nbEnemy = Integer.parseInt(this.enemiesNbr.getText());
				this.enemiesNbr.setText(Integer.toString(nbEnemy - nbEnemyDead + nbEnemySpawned));
			}
		});
		
		this.gm.getGameEnvironment().getBullets().addListener((ListChangeListener <Bullet>) c-> {
			while (c.next()) {
				for (Bullet bullet : c.getAddedSubList())
					this.bv.addBulletView(bullet);
				for (Bullet bullet : c.getRemoved())
					this.bv.removeGameObjectView(bullet);
			}
		});
	}
		
	// Methode de creation des liens entre le modele et les vues (byteCoin, numero de la vague en cours, couts des tourelles)
	private void createBindAndListeners() {
		this.byteCoin.textProperty().bind(this.gm.getByteCoinProperty().asString());
		this.waveNbr.textProperty().bind(this.gm.getWaveInProgressNbrProperty().asString());		
		this.costAdcube.setText(Integer.toString((AdCube.getCostProperty().getValue())));
		this.costAntivirus.setText(Integer.toString((Antivirus.getCostProperty().getValue())));
		this.costAuthPoint.setText(Integer.toString((AuthenticationPoint.getCostProperty().getValue())));
		this.costFirewall.setText(Integer.toString((Firewall.getCostProperty().getValue())));
		this.costSudVPN.setText(Integer.toString((SudVPN.getCostProperty().getValue())));
	}
	
	// Methode qui initialise les actions du bouton de controle de la partie
	private void initPlayAndPause() {
    	this.gameControls.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {    	    	
				if (playActivated) {
			        gameControls.setGraphic(new ImageView(playImg));
	    	        gameLoop.pause();
	    	        disableMouseDraggedAndDropOnTowers();
	    	        playActivated = false;
	    	        message.setText("PAUSE");
    	    	} else {
    	    		gameControls.setGraphic(new ImageView(pauseImg));
        	        gameLoop.play();
        	        enableMouseDraggedAndDropOnTowers();
        	        playActivated = true;
        	        message.setText("PLAY");
    	    	}    	    		    	      
    	    }
    	});
    }
	
	// Methode qui initialise les actions de glisser-deposer sur les tourelles du magasin
	private void dragAndDropOnTower(ImageView tower, int initialX) { 
		int tileSize = GameArea.TILE_SIZE; // taille d'une tuile dans le plateau de jeu

		// La tourelle suit le curseur de la souris lorsque au glisser de la souris
		tower.setOnMouseDragged(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				tower.setX((int) event.getX() - tileSize / 2); 
				tower.setY((int) event.getY() - tileSize / 2);
			}
		});
		
		// La tourelle est placee au relacher de la souris
		tower.setOnMouseReleased(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				Tower newTower;
				int x = (int) event.getX() / tileSize * tileSize; 
				int y = (int) event.getY() / tileSize * tileSize;

				// La tuile ou la tourelle a ete relachee n'est pas prise et est placeable
				if (gm.towerIsPlaceable(x, y) && !gm.tileIsReserved(x, y)) {
					GameEnvironment ge = gm.getGameEnvironment();
					
					if (tower == adcube)
						newTower = new AdCube(x, y, ge);
					else if (tower == antivirus)
						newTower = new Antivirus(x, y, ge);
					else if (tower == authenticationpoint)
						newTower = new AuthenticationPoint(x, y, ge);
					else if (tower == firewall)
						newTower = new Firewall(x, y, ge);
					else if (tower == sudvpn)
						newTower = new SudVPN(x, y, ge);
					else
						newTower = null;
					
					// Si le joueur possede assez de byteCoin pour acheter la tourelle
					if (gm.debitMoney(newTower.getCost()))
						ge.addTower(newTower);
				}
				tower.setX(initialX);
				tower.setY(800); // 800 est l'ordonnee des imageView des tourelles dans leur menu d'achat
			}
		});
	}
	
	// Methode qui initialise pour chaque type de tourelle les actions de glisser-deposer dans le magasin
	private void mouseDraggedOnShop() {
		this.adcube.setOnMouseDragged(event -> {
			dragAndDropOnTower(this.adcube, (int) this.adcube.getX());
		});
		this.antivirus.setOnMouseDragged(event -> {
			dragAndDropOnTower(this.antivirus, (int) this.antivirus.getX());
		});
		this.authenticationpoint.setOnMouseDragged(event -> {
			dragAndDropOnTower(this.authenticationpoint, (int) this.authenticationpoint.getX());
		});
		this.firewall.setOnMouseDragged(event -> {
			dragAndDropOnTower(this.firewall, (int) this.firewall.getX());
		});
		this.sudvpn.setOnMouseDragged(event -> {
			dragAndDropOnTower(this.sudvpn, (int) this.sudvpn.getX());
		});
	}
	
	// Methode de desactivation des evenements des tourelles du magasin
	private void disableMouseDraggedAndDropOnTowers() {
		this.adcube.setDisable(true);
		this.antivirus.setDisable(true);
		this.authenticationpoint.setDisable(true);
		this.firewall.setDisable(true);
		this.sudvpn.setDisable(true);
	}
	
	// Methode de activation des evenements des tourelles du magasin
	private void enableMouseDraggedAndDropOnTowers() {
		this.adcube.setDisable(false);
		this.antivirus.setDisable(false);
		this.authenticationpoint.setDisable(false);
		this.firewall.setDisable(false);
		this.sudvpn.setDisable(false);
	}
	
	private void updateTimer() {
		this.seconde++;
		if (this.seconde == 60) {
			this.seconde = 0;
			this.minute++;
		}
		this.timer.setText(String.format("%02d", this.minute) + ":" + String.format("%02d", this.seconde));
	}
	
	// Methode qui change les couleurs des prix dans le magasin pour préciser si l'on peut acheter ou non les tourelles
	private void canBuy() {
		int money = this.gm.getByteCoinProperty().getValue();
		
		if (money >= AdCube.getCostProperty().getValue())
			this.costAdcube.setTextFill(Color.GREEN);
		else
			this.costAdcube.setTextFill(Color.RED);
		
		if (money >= Antivirus.getCostProperty().getValue())
			this.costAntivirus.setTextFill(Color.GREEN);
		else
			this.costAntivirus.setTextFill(Color.RED);
		
		if (money >= AuthenticationPoint.getCostProperty().getValue())
			this.costAuthPoint.setTextFill(Color.GREEN);
		else
			this.costAuthPoint.setTextFill(Color.RED);
		
		if (money >= Firewall.getCostProperty().getValue())
			this.costFirewall.setTextFill(Color.GREEN);
		else
			this.costFirewall.setTextFill(Color.RED);
		
		if (money >= SudVPN.getCostProperty().getValue())
			this.costSudVPN.setTextFill(Color.GREEN);
		else
			this.costSudVPN.setTextFill(Color.RED);
	}
	
	// Methode qui fait tourner le jeu en boucle (faire un tour, actions de l'environnement, achat, victoire et defaite, timer)
	private void initAnimation() {
		this.gameLoop = new Timeline();
        this.time = 0;
        this.gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.25), (event -> {
			if (this.time % 4 == 0) {		
				// Vague en cours
				if (this.gm.isWaveRunning()) {
					this.message.setText("Vague en cours...");
					if (this.playActivated) { // Bouton de controle sur play
						this.gm.aTurn();
						this.hpPC.setProgress(this.progressBarValue());
					}
				} else 
					this.message.setText("Lance une vague");
				
				this.updateTimer();
				
				if (this.gm.playerLooses()) {
					message.setText("DEFAITE !");
					this.gameLoop.stop();
					this.disableMouseDraggedAndDropOnTowers();
				}
			} else {
				this.gm.actionGameEnvironment();
				this.canBuy();
				if (this.gm.playerWins()) {
					this.message.setText("VICTOIRE !");
					this.gameLoop.stop();
					this.disableMouseDraggedAndDropOnTowers();
				}
			}
			this.time++;
		}));
		this.gameLoop.getKeyFrames().add(kf);
	}
	
	private float progressBarValue() {
		float progressBarValue = (1-(float)this.gm.getInfectionProgress()/GameMaster.getPcHp());
		if(progressBarValue<=0)
			return 0;
		else
			return progressBarValue;
	}
	
	private void updateStats(LivingObject lo) {
		this.attackStat.setText(Integer.toString(lo.getAttack()));
	    this.defenseStat.setText(Integer.toString(lo.getDefense()));
	    this.attackSpeedStat.setText(Integer.toString(lo.getAttackSpeed()));
	    this.attackRangeStat.setText(Integer.toString(lo.getAttackRange()));		
	}

	// Methode qui recupere et affiche le nom des tourelles et des ennemis lorsqu'on les survole avec le curseur de la souris
	private void onMouseOverLivingObject() {
		GameEnvironment ge = new GameEnvironment();
		adcube.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
            	name.setText(adcube.getId().toUpperCase());
            	updateStats(new  AdCube(0, 0, ge));
            }
        });
		antivirus.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
            	name.setText(antivirus.getId().toUpperCase());
            	updateStats(new  Antivirus(0, 0, ge));
            }
        });
		authenticationpoint.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
            	name.setText(authenticationpoint.getId().toUpperCase());
            	updateStats(new  AuthenticationPoint(0, 0, ge));
            }
        });
		firewall.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
            	name.setText(firewall.getId().toUpperCase());
            	updateStats(new  Firewall(0, 0, ge));
            }
        });
		sudvpn.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
            if (newValue) {
            	name.setText(sudvpn.getId().toUpperCase());
            	updateStats(new  SudVPN(0, 0, ge));
            }
        });
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.loadPlayPauseImage();
		this.gm = new GameMaster();
		this.seconde = 0;
        this.minute = 0;
		
		new GameAreaView(this.gm.getGameArea(), this.gameBoard);
		this.ev = new EnemyView(this.enemiesGrid);
		this.tv = new TowerView(this.towersGrid, this.adcube, this.antivirus, this.authenticationpoint, this.firewall, this.sudvpn);
		this.bv = new BulletView(this.bulletsGrid);
		this.hpPC.setStyle("-fx-accent :  #1e838c;");
		this.onMouseOverLivingObject();
		this.generateGameObjectsListener();
		this.createBindAndListeners();
		this.initPlayAndPause();
		this.mouseDraggedOnShop();
		this.initAnimation();
		this.gameLoop.play();
		this.playActivated = true;
	}
	
	// Methode qui lance une nouvelle vague lorsque le bouton est presser
	@FXML
	private void launchWave(ActionEvent event) {
    	// Incrementation du numero de la vague (lancement d'une vague) lorsqu'aucune vague est en cours
		if(!this.gm.isWaveRunning())
    		this.gm.incrementWaveInProgressNumber();
    }
}