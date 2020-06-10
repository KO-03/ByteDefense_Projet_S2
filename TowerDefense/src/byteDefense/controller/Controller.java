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
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class Controller implements Initializable {

	private static Image play;
	private static Image pause;
	
	@FXML
	private TilePane gameBoard; // Plateau de jeu
	@FXML
    private Pane gridBullets; // Grille de tirs
	@FXML
    private Pane gridEnemies; // Grille d'ennemis
	@FXML
    private Pane gridTowers; // Grille de tourelles
	
	// Dans le magasin
	@FXML
	private ImageView adcube; 
	@FXML
	private ImageView antivirus;
	@FXML
	private ImageView authentipoint;
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
	private Label attackStat;
    @FXML
    private Label defenseStat;
    @FXML
    private Label attackSpeedStat;
    @FXML
    private Label attackRangeStat;
    
    @FXML
    private Button launchWaveBt;
    @FXML
    private Button gameControls;
    @FXML
    private ImageView pauseButton; // Bouton pause
    @FXML
    private ImageView playButton; // Bouton jouer
    @FXML
    private Label timer; // Minuteur
    
    private GameMaster gm;
	private int enemiesNbrModel;
	private boolean gameStatus;
	private EnemyView ev;
	private TowerView tv;
	private BulletView bv;
	private Timeline gameLoop;
	private int time;
	private int seconde;
	private int minute;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.loadPlayPauseImage();
		this.gm = new GameMaster();
		this.enemiesNbrModel = 0;
		this.seconde = 0;
        this.minute = 0;
		
		new GameAreaView(this.gm.getGameArea(), this.gameBoard);
		this.ev = new EnemyView(this.gridEnemies);
		this.tv = new TowerView(this.gridTowers, this.adcube, this.antivirus, this.authentipoint, this.firewall, this.sudvpn);
		this.bv = new BulletView(this.gridBullets);
		
		this.generateGameObjectsListener();
		this.generateWalletListener();
		this.mouseDraggedOnShop();
		this.initAnimation();
		this.gameLoop.play();
		this.gameStatus = false;
	}
	
	private void generateWalletListener() {
		this.byteCoin.textProperty().bind(this.gm.getWalletProperty().asString());	
	}
	
	private void loadPlayPauseImage() {
		try {
			play = new Image(new File("./resources/icons/play-button.png").toURI().toURL().toString());
			pause = new Image(new File("./resources/icons/pause-button.png").toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
    private void playAndPause(ActionEvent event) {
    	this.gameControls.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {    	    	
				if(gameStatus == true) {
			        gameControls.setGraphic(new ImageView(play));
	    	        gameLoop.pause();
	    	        disableMouseDraggedOnTowers();
	    	        gameStatus = false;
    	    	}else {
    	    		gameControls.setGraphic(new ImageView(pause));
        	        gameLoop.play();
        	        enableMouseDraggedOnTowers();
        	        gameStatus = true;
    	    	}    	    		    	      
    	    }
    	});
    }
	
	private void initAnimation() {
		this.gameLoop = new Timeline();
        this.time = 0;
        this.gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(Duration.seconds(0.25), (event -> {
			if (this.time % 4 == 0) {
				if(this.gameStatus == true && this.gm.isWaveRunning())
					this.gm.addMoney(1);
				this.updateTimer();
				this.gm.aTurn();
				if (this.gm.playerLooses()) {
					System.out.println("LOOSE");
					this.gameLoop.stop();
				}
			}
			this.gm.getGameEnvironment().gameEnvironmentAction();
			if (this.gm.playerWins()) {
				System.out.println("WIN");
				this.gameLoop.stop();
			}
			this.time++;
		}));
		this.gameLoop.getKeyFrames().add(kf);
	}

	private void updateTimer() {
		this.seconde++;
		if (seconde == 60) {
			seconde = 0;
			minute++;
		}
		timer.setText(String.format("%02d", minute) + ":" + String.format("%02d", seconde));
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
			while (c.next()) {
				for (Enemy enemy : c.getAddedSubList())
					this.ev.addLivingObjectView(enemy);
				for (Enemy enemy : c.getRemoved())
					this.ev.removeGameObjectView(enemy);
				this.enemiesNbr.setText(Integer.toString(this.enemiesNbrModel));
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
	
	private void mouseDraggedOnShop() {
		this.adcube.setOnMouseDragged(event -> {
			dragAndDrop(this.adcube, (int) this.adcube.getX());
		});
		this.antivirus.setOnMouseDragged(event -> {
			dragAndDrop(this.antivirus, (int) this.antivirus.getX());
		});
		this.authentipoint.setOnMouseDragged(event -> {
			dragAndDrop(this.authentipoint, (int) this.authentipoint.getX());
		});
		this.firewall.setOnMouseDragged(event -> {
			dragAndDrop(this.firewall, (int) this.firewall.getX());
		});
		this.sudvpn.setOnMouseDragged(event -> {
			dragAndDrop(this.sudvpn, (int) this.sudvpn.getX());
		});
	}

	private void dragAndDrop(ImageView tower, int initialX) { 
		int tileSize = GameArea.TILE_SIZE;

		tower.setOnMouseDragged(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				tower.setX((int) event.getX() - tileSize / 2);
				tower.setY((int) event.getY() - tileSize / 2);
			}
		});

		tower.setOnMouseReleased(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				Tower newTower;
				int x = (int) event.getX() / tileSize * tileSize; 
				int y = (int) event.getY() / tileSize * tileSize;

				if (gm.towerIsPlaceable(x, y) && !gm.tileIsReserved(x, y)) {
					GameEnvironment ge = gm.getGameEnvironment();
					
					if (tower == adcube)
						newTower = new AdCube(x, y, ge);
					else if (tower == antivirus)
						newTower = new Antivirus(x, y, ge);
					else if (tower == authentipoint)
						newTower = new AuthenticationPoint(x, y, ge);
					else if (tower == firewall)
						newTower = new Firewall(x, y, ge);
					else if (tower == sudvpn)
						newTower = new SudVPN(x, y, ge);
					else
						newTower = null;
					
					// le joueur possede assez de byteCoin pour acheter la tourelle
					if (gm.debitMoney(newTower.getCost()))
						ge.addTower(newTower);
				}
				tower.setX(initialX);
				tower.setY(741); // 741 est l'ordonnee des imageView des tourelles dans leur menu d'achat
			}
		});
	}
	
	private void disableMouseDraggedOnTowers() {
		this.adcube.setDisable(true);
		this.antivirus.setDisable(true);
		this.authentipoint.setDisable(true);
		this.firewall.setDisable(true);
		this.sudvpn.setDisable(true);
	}
	
	private void enableMouseDraggedOnTowers() {
		this.adcube.setDisable(false);
		this.antivirus.setDisable(false);
		this.authentipoint.setDisable(false);
		this.firewall.setDisable(false);
		this.sudvpn.setDisable(false);
	}
	
	@FXML
   private void launchWave(ActionEvent event) {
    	if(!this.gm.isWaveRunning()) {
    		this.gm.incrementWaveNumber();
    		if(this.gameStatus == false)
    			this.gameStatus = true;
    	}
    }
}