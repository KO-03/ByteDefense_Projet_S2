/*
 * Controller.java
 * Cette classe fait le lien entre le modele et la vue, ses responsabilitees sont :
 * - charger les textures du bouton de controle
 * - generer tous les listener des objects du jeu
 * - creer les listener et bind entre le modele et la vue
 * - initialiser le bouton de controle
 * - verifier si la partie est en cours
 * - changer la couleur du prix de chaque type en fonction de l'argent
 * - retourner le pourcentage d'infection des ennemies
 * - mettre a jour les informations d'une tourelle dans la vue
 * - gerer le deplacement et la vente des tourelles sur le terrain
 * - mettre a jour le minuteur
 * - tourner le jeu en boucle
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
import javafx.scene.input.MouseButton;
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
	
	// Informations des tourelles du magasin
	@FXML
	private Label name; // Nom de la tourelle
	@FXML
	private Label attackStat;
	@FXML
	private Label defenseStat;
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
	private Button quitBt;
	@FXML
	private Button gameControls; // Bouton pour gerer le pause/play
	@FXML
	private Label timer; // Minuteur
	@FXML
	private Label message; // Message qui indique l'etat de la partie (vague en cours, en pause, entre-vague, fin de partie)
	@FXML
	private ProgressBar laptopHpBar; //barre de vie de l'ordianteur
	
	private static GameMaster gm;
	private static boolean playActivated; // indique si le bouton play/pause a ete presse
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

	/* Methode qui genere tous les listener pour mettre a jour la vue en fonction des 
	 * donnees de l'environnement du jeu
	 */
	private void generateGameObjectsListener() {
		gm.getGameEnvironment().getTowers().addListener((ListChangeListener <Tower>) c-> {
			while (c.next()) {
				for (Tower tower : c.getAddedSubList())
					this.tv.addTowerView(tower);
				for (Tower tower : c.getRemoved())
					this.tv.removeGameObjectView(tower);
			}
		});
		
		gm.getGameEnvironment().getEnemies().addListener((ListChangeListener <Enemy>) c-> {
			int nbEnemyDead = 0;
			int nbEnemySpawned = 0;
			
			while (c.next()) {
				for (Enemy enemy : c.getAddedSubList()) {
					this.ev.addEnemyView(enemy);
					nbEnemySpawned++;
				}
				for (Enemy enemy : c.getRemoved()) {
					if (!enemy.isAlive()) // Gain de byteCoin quand un ennemi est tue
						gm.addMoney(enemy.getLoot());
					this.ev.removeGameObjectView(enemy);					
					nbEnemyDead++;
				}
				int nbEnemy = Integer.parseInt(this.enemiesNbr.getText());
				this.enemiesNbr.setText(Integer.toString(nbEnemy - nbEnemyDead + nbEnemySpawned));
			}
		});
		
		gm.getGameEnvironment().getBullets().addListener((ListChangeListener <Bullet>) c-> {
			while (c.next()) {
				for (Bullet bullet : c.getAddedSubList())
					this.bv.addBulletView(bullet);
				for (Bullet bullet : c.getRemoved())
					this.bv.removeGameObjectView(bullet);
			}
		});
	}
		
	/* Methode de creation des liens entre le modele et les vues (byteCoin, 
	 * numero de la vague en cours, couts des tourelles dans le magasin)
	 */
	private void createBindAndListeners() {
		this.byteCoin.textProperty().bind(gm.getByteCoinProperty().asString());
		this.waveNbr.textProperty().bind(gm.getWaveInProgressNbrProperty().asString());		
		this.costAdcube.setText(Integer.toString(AdCube.getCostProperty().getValue()));
		this.costAntivirus.setText(Integer.toString(Antivirus.getCostProperty().getValue()));
		this.costAuthPoint.setText(Integer.toString(AuthenticationPoint.getCostProperty().getValue()));
		this.costFirewall.setText(Integer.toString(Firewall.getCostProperty().getValue()));
		this.costSudVPN.setText(Integer.toString(SudVPN.getCostProperty().getValue()));
	}
	
	// Methode qui initialise les actions du bouton de controle (play/pause) de la partie
	private void initPlayAndPause() {
		this.gameControls.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (playActivated) { // le bouton play a ete presse
					gameControls.setGraphic(new ImageView(playImg));
					gameLoop.pause();
					playActivated = false;
					message.setText("PAUSE");
				} else {
					gameControls.setGraphic(new ImageView(pauseImg));
					gameLoop.play();
					playActivated = true;
					message.setText("PLAY");
				}
			}
		});
    }
	
	/* Methode qui verifie si le bouton est sur play et si le joueur a gagne ou perdu 
	 * (pour activer ou pas les actions sur les towers)
	 */
	private static boolean isRunning() {
		if (playActivated && !gm.playerLooses() && !gm.playerWins())
			return true;
			
		return false;
	}
	
	// Methode qui initialise les actions de glisser-deposer sur une tourelle du magasin
	private void dragAndDropOnTowerShop(ImageView tower, int initialX) { 
		int tileSize = GameArea.TILE_SIZE; // taille d'une tuile dans le plateau de jeu
		
		// La tourelle suit le curseur de la souris lorsque au glisser de la souris
		tower.setOnMouseDragged(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				if (isRunning()) {
					tower.setX((int) event.getX() - tileSize / 2); 
					tower.setY((int) event.getY() - tileSize / 2);
				}
			}
		});
		
		// La tourelle est placee au relacher de la souris (avec debit de byteCoin)
		tower.setOnMouseReleased(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				if (isRunning()) {
					Tower newTower;
					int x = (int) event.getX() / tileSize * tileSize; 
					int y = (int) event.getY() / tileSize * tileSize;
	
					// La tuile ou la tourelle a ete relachee n'est pas prise et est placeable
					if (gm.towerIsPlaceable(x, y) && !gm.tileIsReserved(x, y)) {
						GameEnvironment gameEnv = gm.getGameEnvironment();
						
						if (tower == adcube)
							newTower = new AdCube(x, y, gameEnv);
						else if (tower == antivirus)
							newTower = new Antivirus(x, y, gameEnv);
						else if (tower == authenticationpoint)
							newTower = new AuthenticationPoint(x, y, gameEnv);
						else if (tower == firewall)
							newTower = new Firewall(x, y, gameEnv);
						else if (tower == sudvpn)
							newTower = new SudVPN(x, y, gameEnv);
						else
							newTower = null;
						
						// Si le joueur possede assez de byteCoin pour acheter la tourelle
						if (gm.debitMoney(newTower.getCost()))
							gameEnv.addTower(newTower);
					}
					tower.setX(initialX);
					tower.setY(800); // 800 est l'ordonnee des imageView des tourelles dans leur menu d'achat
				}
			}
		});
	}
	
	// Methode qui initialise pour chaque type de tourelle les actions de glisser-deposer dans le magasin
	private void mouseDraggedAndDropOnTowersShop() {
		this.adcube.setOnMouseDragged(event -> {
			this.dragAndDropOnTowerShop(this.adcube, (int)this.adcube.getX());
		});
		this.antivirus.setOnMouseDragged(event -> {
			this.dragAndDropOnTowerShop(this.antivirus, (int)this.antivirus.getX());
		});
		this.authenticationpoint.setOnMouseDragged(event -> {
			this.dragAndDropOnTowerShop(this.authenticationpoint, (int)this.authenticationpoint.getX());
		});
		this.firewall.setOnMouseDragged(event -> {
			this.dragAndDropOnTowerShop(this.firewall, (int)this.firewall.getX());
		});
		this.sudvpn.setOnMouseDragged(event -> {
			this.dragAndDropOnTowerShop(this.sudvpn, (int)this.sudvpn.getX());
		});
	}
	
	// Methode qui change les couleurs des prix dans le magasin pour préciser si l'on peut acheter ou non les tourelles
	private void canBuy() {
		int money = gm.getByteCoin();
		
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
	
	/* Fonction qui retourne le pourcentage de progression de l'infection en fonction des
	 * points de vie de base de l'ordinateur
	 */
	private float infectionProgressBarValue() {
		return (float)gm.getInfectionProgress() / GameMaster.getLaptopHp();
	}
	
	// Methode qui met a jour dans la vue les informations d'une tourelle du modele
	private void updateStats(Tower tower) {
		this.attackStat.setText(Integer.toString(tower.getAttack()));
		this.defenseStat.setText(Integer.toString(tower.getDefense()));
		this.attackRangeStat.setText(Integer.toString(tower.getAttackRange()));
	}

	/* Methode qui recupere et affiche le informations des tourelles du magasin lorsque 
	 * le curseur de la souris les survole 
	 */
	private void onMouseOverShopTowers() {
		GameEnvironment gameEnv = new GameEnvironment();
		
		adcube.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			if (newValue) {
				name.setText(adcube.getId().toUpperCase());
				this.updateStats(new AdCube(0, 0, gameEnv));
			}
		});
		antivirus.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			if (newValue) {
				name.setText(antivirus.getId().toUpperCase());
				this.updateStats(new Antivirus(0, 0, gameEnv));
			}
		});
		authenticationpoint.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			if (newValue) {
				name.setText(authenticationpoint.getId().toUpperCase());
				this.updateStats(new AuthenticationPoint(0, 0, gameEnv));
			}
		});
		firewall.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			if (newValue) {
				name.setText(firewall.getId().toUpperCase());
				this.updateStats(new Firewall(0, 0, gameEnv));
			}
		});
		sudvpn.hoverProperty().addListener((ChangeListener<Boolean>) (observable, oldValue, newValue) -> {
			if (newValue) {
				name.setText(sudvpn.getId().toUpperCase());
				this.updateStats(new SudVPN(0, 0, gameEnv));
			}
		});
	}
	
	// Methode qui gere le deplacement et la vente de tourelles deja placees sur le terrain
	public static void moveAndSellTowers(ImageView imgViewTower, Tower tower) {
		int tileSize = GameArea.TILE_SIZE;
		
		// La tourelle suit le curseur de la souris lorsque au glisser de la souris
		imgViewTower.setOnMouseDragged(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				// Aucune vague n'est en cours, le jeu n'est pas en pause et la partie n'est pas terminee
				if (isRunning() && !gm.isWaveRunning()) {
					imgViewTower.setX((int)event.getX() - tileSize / 2);
					imgViewTower.setY((int)event.getY() - tileSize / 2);
				}
			}
		});
		
		// La tourelle est placee au relacher de la souris (avec debit de byteCoin)
		imgViewTower.setOnMouseReleased(new EventHandler <MouseEvent>() {
			int initialX = (int)imgViewTower.getX();
			int initialY = (int)imgViewTower.getY();
			
			public void handle(MouseEvent event) {
				// Aucune vague n'est en cours, le jeu n'est pas en pause et la partie n'est pas terminee
				if (isRunning() && !gm.isWaveRunning()) {
					int x = (int)event.getX() / tileSize * tileSize;
					int y = (int)event.getY() / tileSize * tileSize;
					
					// La tuile ou la tourelle a ete relachee n'est pas prise et est placeable et le debit de byteCoin peut se faire
					if (gm.towerIsPlaceable(x, y) && !gm.tileIsReserved(x, y) && gm.debitMoney((int)Math.round(tower.getCost() * 0.5))) {
						// La tourelle est deplacee a l'endroit ou la souris a ete relachee
						tower.setX(x); 
						tower.setY(y);
						imgViewTower.setX(x); 
						imgViewTower.setY(y);
						initialX = x;
						initialY = y;
					} else {
						// La tourelle est reaffichee a sa position initiale
						imgViewTower.setX(initialX);
						imgViewTower.setY(initialY);
					}
				}
			}
		});
		
		// La tourelle disparait lorsque l'on fait un double clique dessus (avec gain de byteCoin)
		imgViewTower.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent mouseEvent) {
				// Aucune vague n'est en cours, le jeu n'est pas en pause et la partie n'est pas terminee
				if (isRunning() && !gm.isWaveRunning()) {
					// Un double clique est fait sur la tourelle
					if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2) {
						gm.removeEnvironmentTower(tower);
						gm.addMoney((int)Math.round(tower.getCost() * 0.7));
					}
				}
			}
		});
	}
	
	// Methode qui met a jour le minuteur
	private void updateTimer() {
		this.seconde++;
		if (this.seconde == 60) {
			this.seconde = 0;
			this.minute++;
		}
		this.timer.setText(String.format("%02d", this.minute) + ":" + String.format("%02d", this.seconde));
	}
	
	// Methode qui fait tourner le jeu en boucle (faire un tour, actions de l'environnement, achat, victoire et defaite, timer)
	private void initAnimation() {
		this.gameLoop = new Timeline();
		this.time = 0;
		this.gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.25), (event -> {
			if (this.time % 4 == 0) {		
				// Vague en cours
				if (gm.isWaveRunning()) {
					this.message.setText("Vague en cours...");
					if (playActivated) { // Bouton de controle sur play
						try {
							gm.aTurn();
						} catch (Exception e) {
							e.printStackTrace();
						}
						this.laptopHpBar.setProgress(this.infectionProgressBarValue());
					}
				} else
					this.message.setText("Lance une vague");
				
				this.updateTimer();
				
				if (gm.playerLooses()) {
					message.setText("DEFAITE !");
					this.gameLoop.stop();
					this.gameControls.setDisable(true);
				}
			} else {
				this.canBuy();
				gm.actionGameEnvironment();
				if (gm.playerWins()) {
					this.message.setText("VICTOIRE !");
					this.gameLoop.stop();
					this.gameControls.setDisable(true);
				}
			}
			this.time++;
		}));
		this.gameLoop.getKeyFrames().add(kf);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.loadPlayPauseImage();
		gm = new GameMaster();
		this.seconde = 0;
		this.minute = 0;
		
		new GameAreaView(gm.getGameArea(), this.gameBoard);
		this.ev = new EnemyView(this.enemiesGrid);
		this.tv = new TowerView(this.towersGrid, this.adcube, this.antivirus, this.authenticationpoint, this.firewall, this.sudvpn);
		this.bv = new BulletView(this.bulletsGrid);
		
		this.onMouseOverShopTowers();
		this.generateGameObjectsListener();
		this.createBindAndListeners();
		this.initPlayAndPause();
		this.mouseDraggedAndDropOnTowersShop();
		this.initAnimation();
		this.gameLoop.play();
		playActivated = true;
		this.quitBt.setOnMouseClicked(event -> System.exit(0));
	}
	
	// Methode qui lance une nouvelle vague lorsque le bouton est presser
	@FXML
	private void launchWave(ActionEvent event) {
		// Incrementation du numero de la vague (lancement d'une vague) lorsqu'aucune vague est en cours
		if(!gm.isWaveRunning())
			gm.incrementWaveInProgressNumber();
    }
}