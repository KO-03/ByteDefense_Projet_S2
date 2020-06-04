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

import java.net.URL;
import java.util.ResourceBundle;

import byteDefense.model.Bullet;
import byteDefense.model.GameArea;
import byteDefense.model.GameEnvironment;
import byteDefense.model.GameMaster;
import byteDefense.model.GameObject;
import byteDefense.model.enemies.Ennemy;
import byteDefense.model.towers.AdCube;
import byteDefense.model.towers.Antivirus;
import byteDefense.model.towers.AuthenticationPoint;
import byteDefense.model.towers.Firewall;
import byteDefense.model.towers.SudVPN;
import byteDefense.model.towers.Tower;
import byteDefense.view.BulletView;
import byteDefense.view.EnnemyView;
import byteDefense.view.GameAreaView;
import byteDefense.view.TowerView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class Controller implements Initializable {

	@FXML
	private TilePane gameBoard;
	@FXML
	private Pane grid;
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
    private Label waveNbr;
    @FXML
    private Label enemiesNbr;
    @FXML
    private Label byteCoin; // Argent du jeu
    @FXML
    private Label attackStat;
    @FXML
    private Label attackSpeedStat;
    @FXML
    private Label defenseStat;
    @FXML
    private Label attackRangeStat;

	private GameMaster gm;
	private int nbrEnemies;
	private EnnemyView ev;
	private TowerView tv;
	private BulletView bv;
	private Timeline gameLoop;
	private int time;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.gm = new GameMaster();
		this.nbrEnemies = 0;
		
		new GameAreaView(this.gm.getGameArea(), this.gameBoard);
		this.ev = new EnnemyView(this.grid);
		this.tv = new TowerView(this.grid, this.adcube, this.antivirus, this.authentipoint, this.firewall, this.sudvpn);
		this.bv = new BulletView(this.grid);
		
		this.generateGameObjectListListener();
		this.generateBulletsListener();
		this.generateWalletListener();
		this.mouseDraggedOnTowers();
		this.initAnimation();
		this.gameLoop.play();
	}

	private void generateWalletListener() {
		this.byteCoin.textProperty().bind(this.gm.getWalletProperty().asString());	
	}
	
	private void initAnimation() {
		this.gameLoop = new Timeline();
        this.time = 0;
        this.gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(
				Duration.seconds(0.3), 
				(event ->{
					if (this.time == 10000) {
						System.out.println("fini");
						this.gameLoop.stop();
					} else if (this.time % 5 == 0) {
						this.gm.aTurn();
					} else
                    	this.gm.getGameEnvironment().bulletsHandler();
					this.time++;
				}));

		this.gameLoop.getKeyFrames().add(kf);

	}

	private void generateGameObjectListListener() {
		this.gm.getGameEnvironment().getGameObjectsList().addListener((ListChangeListener <GameObject>) c-> {
			while (c.next()) {
				for (GameObject gameObject : c.getAddedSubList()) {
					if (gameObject instanceof Ennemy) {
						this.ev.addGameObject(gameObject);
						this.nbrEnemies++;
					} else
						this.tv.addGameObject(gameObject);
				}
				for (GameObject gameObject : c.getRemoved()) {
					if (gameObject instanceof Ennemy) {
						this.ev.removeGameObject(gameObject);				
						this.nbrEnemies--;
					} else
						this.tv.removeGameObject(gameObject);
				}
				this.enemiesNbr.setText(Integer.toString(this.nbrEnemies));
			}
		});
	}

	private void generateBulletsListener() {
		this.gm.getGameEnvironment().getBullets().addListener((ListChangeListener <Bullet>) c-> {
			while (c.next()) {
				for (Bullet bullet : c.getAddedSubList())
					this.bv.addBulletView(bullet);
				for (Bullet bullet : c.getRemoved())
					this.bv.removeBulletView(bullet);
			}
		});
	}
	
	private void mouseDraggedOnTowers() {
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
				Tower newTower = null;
				int x = (int) event.getX() / tileSize * tileSize; 
				int y = (int) event.getY() / tileSize * tileSize;

				if (gm.getGameArea().isPlaceable(x, y)) {
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
					
					// le joueur ne possede pas assez de byteCoin pour acheter la tourelle
					if(gm.debitMoney(newTower.getCost()))
						ge.addGameObject(newTower);
				}
				tower.setX(initialX);
				tower.setY(741); // 741 est l'ordonnee des imageView des tourelles dans leur menu d'achat
			}
		});
	}
}