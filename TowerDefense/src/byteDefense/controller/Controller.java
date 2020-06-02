/*
 * Controller.java
 * Cette classe fait le lien entre le modele et la vue, ses responsabilitees sont :
 * - initialiser le gameMaster
 * - initialiser la	vue du GameObject
 * - initialiser la vue de la TileMap
 * - gerer la gameLoop et les actions du jeu (effectuer un tour)
 * - gerer les entites de vue (tourelle, plateau de jeu, grille d'ennemis et de tourelles)
 * - gerer le glisser deposer d'une tourelle et la positionner
 */

package byteDefense.controller;

import java.net.URL;
import java.util.ResourceBundle;

import byteDefense.model.GameArea;
import byteDefense.model.GameMaster;
import byteDefense.model.GameObject;
import byteDefense.model.ennemies.Ennemy;
import byteDefense.model.towers.AdCube;
import byteDefense.model.towers.Antivirus;
import byteDefense.model.towers.AuthenticationPoint;
import byteDefense.model.towers.Firewall;
import byteDefense.model.towers.SudVPN;
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
	private Timeline gameLoop;
	private int time;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.gm = new GameMaster();

		new GameAreaView(this.gm.getGameArea(), this.gameBoard);
		this.ev = new EnnemyView(this.gridEnnemies);
		this.tv = new TowerView(this.gridTowers, this.adcube, this.antivirus, this.authentipoint, this.firewall, this.sudvpn);

		this.generateEnnemiesListener();
		this.mouseDraggedOnTowers();
		this.initAnimation();
		this.gameLoop.play();
		
		this.gm.getGameEnvironment().getGameObjectsList().addListener((ListChangeListener <GameObject>) c-> {
			while (c.next()) {
				if (c.wasRemoved()) {
					for (GameObject gameObject : c.getRemoved()) {
						if (gameObject instanceof Ennemy)
							gridEnnemies.getChildren().remove(this.gridEnnemies.lookup("#" + gameObject.getId()));
						else
							gridTowers.getChildren().remove(this.gridEnnemies.lookup("#" + gameObject.getId()));
					}
				}
			}
		});
	}

	private void initAnimation() {
		this.gameLoop = new Timeline();
		this.time = 0;
		this.gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(
				Duration.seconds(0.5), 
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

	private void generateEnnemiesListener() {
		this.gm.getWaveEnnemy().getEnnemies().addListener(new ListChangeListener<Ennemy>() {
			@Override
			public void onChanged(ListChangeListener.Change<? extends Ennemy> c) {
				while (c.next()) {
					for (Ennemy e : c.getAddedSubList())
						ev.addGameObject(e);
					for (Ennemy e : c.getRemoved())
						ev.removeEnnemy(e);
				}
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

				int x = (int) event.getX() - tileSize / 2; 
				int y = (int) event.getY() - tileSize / 2;

				tower.setX(x);
				tower.setY(y);
			}
		});

		tower.setOnMouseReleased(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				int x = (int) event.getX() / tileSize * tileSize; 
				int y = (int) event.getY() / tileSize * tileSize;

				if (gm.getGameArea().isPlaceable(x, y)) {
					if (tower == adcube)
						tv.addGameObject(new AdCube(x, y, gm.getGameEnvironment()));
					else if (tower == antivirus)
						tv.addGameObject(new Antivirus(x, y, gm.getGameEnvironment()));
					else if (tower == authentipoint)
						tv.addGameObject(new AuthenticationPoint(x, y, gm.getGameEnvironment()));
					else if (tower == firewall)
						tv.addGameObject(new Firewall(x, y, gm.getGameEnvironment()));
					else if (tower == sudvpn)
						tv.addGameObject(new SudVPN(x, y, gm.getGameEnvironment()));
				}

				tower.setX(initialX);
				tower.setY(741);
			}
		});
	}
}