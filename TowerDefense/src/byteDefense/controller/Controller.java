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
import byteDefense.model.ennemies.Ennemy;
import byteDefense.model.towers.AdCube;
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
		this.tv = new TowerView(gridTowers, adcube, antivirus, authentipoint, firewall, sudvpn);
		
		this.generateEnnemiesListener();
		this.mouseDraggedOnTowers();
		this.initAnimation();
		this.gameLoop.play();
	}
	
	private void initAnimation() {
		this.gameLoop = new Timeline();
		this.time = 0;
		this.gameLoop.setCycleCount(Timeline.INDEFINITE);
		
		KeyFrame kf = new KeyFrame(
				Duration.seconds(0.05), 
				(ev ->{
					if (this.time == 10000) {
						System.out.println("fini");

						this.gameLoop.stop();
					} else if (this.time % 5 == 0) {
						gm.aTurn();
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
		adcube.setOnMouseDragged(event -> {
			dragAndDrop(adcube, (int) adcube.getX());
		});
		antivirus.setOnMouseDragged(event -> {
			dragAndDrop(antivirus, (int) antivirus.getX());
		});
		authentipoint.setOnMouseDragged(event -> {
			dragAndDrop(authentipoint, (int) authentipoint.getX());
		});
		firewall.setOnMouseDragged(event -> {
			dragAndDrop(firewall, (int) firewall.getX());
		});
		sudvpn.setOnMouseDragged(event -> {
			dragAndDrop(sudvpn, (int) sudvpn.getX());
		});
	}
	
	private void dragAndDrop(ImageView tower, int initialX) { 
		int TileSize = GameArea.TILE_SIZE;
		
		tower.setOnMouseDragged(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				
				int x = (int) event.getX() - TileSize / 2; // - 24 pour prend le milieu de l'ImageView
				int y = (int) event.getY() - TileSize / 2;
				
				tower.setX(x);
				tower.setY(y);
			}
		});
		
		tower.setOnMouseReleased(new EventHandler <MouseEvent>() {
			public void handle(MouseEvent event) {
				int x = (int) event.getX() / TileSize * TileSize; // / 48 * 48 pour placer l'ImageView dans une tuile
				int y = (int) event.getY() / TileSize * TileSize;
				
				if (gm.getGameArea().isPlaceable(x, y)) {
					tv.addGameObject(new AdCube(x, y));
					System.out.println(tower);
				}

				tower.setX(initialX);
				tower.setY(741);
			}
		});
	}
}