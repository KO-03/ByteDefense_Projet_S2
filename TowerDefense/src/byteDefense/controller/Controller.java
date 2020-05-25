/*
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
import byteDefense.view.EnnemyView;
import byteDefense.view.TileMapView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class Controller implements Initializable {
	
	@FXML
    private TilePane gameBoard;
	@FXML
    private Pane gridEnnemies;
	
	private GameMaster gm;
    private Timeline gameLoop;
    private int time;
    
    private EnnemyView eView;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	gm = new GameMaster();
    	
    	new TileMapView(this.gm.getMap(), this.gameBoard);
		eView = new EnnemyView(this.gridEnnemies);

		this.generateEnnemiesListener();
		
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
						eView.addEnnemyView(e);
					for (Ennemy e : c.getRemoved())
						eView.removeEnnemy(e);
				}
	        }
		});
    }
}