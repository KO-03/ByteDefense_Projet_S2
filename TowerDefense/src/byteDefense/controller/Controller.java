/*
 * Controller.java
 * Cette classe fait le lien entre le modele et la vue, ses responsabilitees sont :
 * - initialiser la TileMap et sa vue
 * - initialiser une vague d'ennemi et sa vue
 * - initialiser les tourelles et leur vue
 * - stocker le BFS et realiser les actions necessaires son application sur la vue
 * - gerer la gameLoop et les actions du jeu (mouvements de ennemis selon le BFS, 
 *   ajout de nouveaux ennemis a la vue selon le modele)
 */

package byteDefense.controller;

import java.net.URL;
import java.util.ResourceBundle;

import byteDefense.model.GameMaster;
import byteDefense.model.ennemies.Ennemy;
import byteDefense.model.towers.AdCube;
import byteDefense.model.towers.Antivirus;
import byteDefense.model.towers.AuthenticationPoint;
import byteDefense.model.towers.Firewall;
import byteDefense.model.towers.SudVPN;
import byteDefense.view.GameObjectView;
import byteDefense.view.TileMapView;
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
	
	private GameObjectView gov;
	
	private GameMaster gm;
    private Timeline gameLoop;
    private int time;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	gm = new GameMaster();
    	
    	new TileMapView(this.gm.getMap(), this.gameBoard);
    	gov = new GameObjectView(this.gridEnnemies);

		this.generateEnnemiesListener();

		adcube.setX(129);
		adcube.setY(741);
		antivirus.setX(213);
		antivirus.setY(741);
		authentipoint.setX(298);
		authentipoint.setY(741);
		firewall.setX(383);
		firewall.setY(741);
		sudvpn.setX(466);
		sudvpn.setY(741);
		
		gridTowers.getChildren().add(adcube);
		gridTowers.getChildren().add(antivirus);
		gridTowers.getChildren().add(authentipoint);
		gridTowers.getChildren().add(firewall);
		gridTowers.getChildren().add(sudvpn);
        
        this.dragAndDrop();
		
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
						System.out.println("un tour");
						gm.onTurn();
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
						gov.addGameObject(e);
					for (Ennemy e : c.getRemoved())
						gov.removeEnnemy(e);
				}
	        }
		});
    }
    
    private void dragAndDrop() { 
    	adcube.setOnMouseDragged(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                int x = (int) event.getSceneX()-46;
				int y = (int) event.getSceneY()-106;
				
				adcube.setX(x);
				adcube.setY(y);
            }
        });
    	
    	adcube.setOnMouseReleased(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                int x = ((int) event.getSceneX()-14)/48*48;
				int y = ((int) event.getSceneY()-74)/48*48;
				
				if (x >= 48 && y >= 48 && x < 624 && y < 624) {
					new GameObjectView(gridTowers).addGameObject(new AdCube(x, y));
					System.out.println("+ AdCube");
				}
				adcube.setX(129);
				adcube.setY(741);
            }
        });
    	
    	antivirus.setOnMouseDragged(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                int x = (int) event.getSceneX()-46;
				int y = (int) event.getSceneY()-106;
				
				antivirus.setX(x);
				antivirus.setY(y);
            }
        });
    	
    	antivirus.setOnMouseReleased(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                int x = ((int) event.getSceneX()-14)/48*48;
				int y = ((int) event.getSceneY()-74)/48*48;
				
				if (x >= 48 && y >= 48 && x < 624 && y < 624) {
					new GameObjectView(gridTowers).addGameObject(new Antivirus(x, y));;
					System.out.println("+ Antivirus");
				}
				antivirus.setX(213);
				antivirus.setY(741);
            }
        });
    	
    	authentipoint.setOnMouseDragged(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                int x = (int) event.getSceneX()-46;
				int y = (int) event.getSceneY()-106;
				
				authentipoint.setX(x);
				authentipoint.setY(y);
            }
        });
    	
    	authentipoint.setOnMouseReleased(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                int x = ((int) event.getSceneX()-14)/48*48;
				int y = ((int) event.getSceneY()-74)/48*48;
				
				if (x >= 48 && y >= 48 && x < 624 && y < 624) {
					new GameObjectView(gridTowers).addGameObject(new AuthenticationPoint(x, y));;
					System.out.println("+ Point d'authentification");
				}
				authentipoint.setX(298);
				authentipoint.setY(741);
            }
        });
    	
    	firewall.setOnMouseDragged(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                int x = (int) event.getSceneX()-46;
				int y = (int) event.getSceneY()-106;
				
				firewall.setX(x);
				firewall.setY(y);
            }
        });
    	
    	firewall.setOnMouseReleased(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                int x = ((int) event.getSceneX()-14)/48*48;
				int y = ((int) event.getSceneY()-74)/48*48;
				
				if (x >= 48 && y >= 48 && x < 624 && y < 624) {
					new GameObjectView(gridTowers).addGameObject(new Firewall(x, y));;
					System.out.println("+ Pare feu");
				}
				firewall.setX(383);
				firewall.setY(741);
            }
        });
    	
    	sudvpn.setOnMouseDragged(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                int x = (int) event.getSceneX()-46;
				int y = (int) event.getSceneY()-106;
				
				sudvpn.setX(x);
				sudvpn.setY(y);
            }
        });
    	
    	sudvpn.setOnMouseReleased(new EventHandler <MouseEvent>() {
            public void handle(MouseEvent event) {
                int x = ((int) event.getSceneX()-14)/48*48;
				int y = ((int) event.getSceneY()-74)/48*48;
				
				if (x >= 48 && y >= 48 && x < 624 && y < 624) {
					new GameObjectView(gridTowers).addGameObject(new SudVPN(x, y));;
					System.out.println("+ SudVPN");
				}
				sudvpn.setX(466);
				sudvpn.setY(741);
            }
        });
    }
}