/*
 * Controller.java
 * Cette classe fait le lien entre le modele et la vue, ses responsabilitees sont :
 * - initialiser le gameMaster
 * - initialiser la vue des ennemis et celle des tourelles
 * - initialiser la vue de la GameArea
 * - gerer la gameLoop et les actions du jeu (effectuer un tour)
 * - gerer les entites de vue (tourelle, plateau de jeu, grille d'ennemis et de tourelles)
 * - creer les listener sur la liste des GameObject de l'environnement de jeu
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
import byteDefense.model.ennemies.Ennemy;
import byteDefense.model.towers.AdCube;
import byteDefense.model.towers.Antivirus;
import byteDefense.model.towers.AuthenticationPoint;
import byteDefense.model.towers.Firewall;
import byteDefense.model.towers.SudVPN;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class Controller implements Initializable {

	@FXML
	private TilePane gameBoard;
	@FXML
	private Pane gridEnnemies;
	@FXML
	private Pane gridTowers;
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

	private EnnemyView ev;
	private TowerView tv;
	private GameMaster gm;
	private BulletView bv;
	private Timeline gameLoop;
	private int time;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.gm = new GameMaster(15);
		
		new GameAreaView(this.gm.getGameArea(), this.gameBoard);
		this.ev = new EnnemyView(this.gridEnnemies);
		this.tv = new TowerView(this.gridTowers, this.adcube, this.antivirus, this.authentipoint, this.firewall, this.sudvpn);
		this.bv = new BulletView(this.gridTowers);
		
		this.generateGameobjectListListener();
		this.generateBulletsListener();
		this.mouseDraggedOnTowers();
		this.initAnimation();
		this.gameLoop.play();
	}

	private void initAnimation() {
		this.gameLoop = new Timeline();
		this.time = 0;
		this.gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(
				Duration.seconds(0.1), 
				(event ->{
					if (this.time == 10000) {
						System.out.println("fini");
						this.gameLoop.stop();
					} else if (this.time % 5 == 0) {
						this.gm.aTurn();
					} 
					this.time++;
				}));

		this.gameLoop.getKeyFrames().add(kf);
	}

	private void generateGameobjectListListener() {
		this.gm.getGameEnvironment().getGameObjectsList().addListener((ListChangeListener <GameObject>) c-> {
			while (c.next()) {
				for (GameObject gameObject : c.getAddedSubList()) {
					if (gameObject instanceof Ennemy)
						this.ev.addGameObject(gameObject);
					else
						this.tv.addGameObject(gameObject);
				}
				for (GameObject gameObject : c.getRemoved()) {
					if (gameObject instanceof Ennemy)
						this.ev.removeGameObject(gameObject);
					else
						this.tv.removeGameObject(gameObject);
				}
			}
		});
	}

	private void generateBulletsListener() {
		this.gm.getGameEnvironment().getBullets().addListener((ListChangeListener <Bullet>) c-> {
			while (c.next()) {
				for (Bullet bullet : c.getAddedSubList())
					this.bv.addBulletImgView(bullet);
				for (Bullet bullet : c.getRemoved())
					this.bv.removeBulletImgView(bullet);
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
				int x = (int) event.getX() / tileSize * tileSize; 
				int y = (int) event.getY() / tileSize * tileSize;

				if (gm.getGameArea().isPlaceable(x, y)) {
					GameEnvironment ge = gm.getGameEnvironment();
					
					if (tower == adcube)
						ge.addGameObject(new AdCube(x, y, ge));
					else if (tower == antivirus)
						ge.addGameObject(new Antivirus(x, y, ge));
					else if (tower == authentipoint)
						ge.addGameObject(new AuthenticationPoint(x, y, ge));
					else if (tower == firewall)
						ge.addGameObject(new Firewall(x, y, ge));
					else if (tower == sudvpn)
						ge.addGameObject(new SudVPN(x, y, ge));
				}
				tower.setX(initialX);
				tower.setY(741); // 741 est l'ordonnee des imageView des tourelles dans leur menu d'achat
			}
		});
	}
}