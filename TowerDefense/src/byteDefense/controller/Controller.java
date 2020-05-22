/*
 * Cette classe fait le lien entre le modele et la vue, ses responsabilitees sont :
 * - initialiser la TileMap
 * - gerer la gameLoop
 */

package byteDefense.controller;

import java.net.URL;
import java.util.ResourceBundle;

import byteDefense.model.TileMap;
import byteDefense.model.ennemies.Wave;
import byteDefense.utilities.BFS;
import byteDefense.view.EnnemyView;
import byteDefense.view.TileMapView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
	
	private Wave waveEnnemy;
	private TileMap map;
    private Timeline gameLoop;
    private int time;
    private BFS bfsMap = new BFS(21);
    private int indEnnemy = -1;
    
    private EnnemyView eView;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.map = new TileMap();
		new TileMapView(this.map, this.gameBoard);
		
		this.waveEnnemy = new Wave(1, bfsMap); 
		
		bfsMap.createPathList();// cree la liste des chemins parcourables
		bfsMap.fillGraph(); // liste avec les voisins
		bfsMap.BFS_algo(0); // point d'arrivee et de lancement de l'algo

		eView = new EnnemyView(this.gridEnnemies);
		
		this.initAnimation();
		this.gameLoop.play();
    }
    
    private void initAnimation() {
		this.gameLoop = new Timeline();
		this.time = 0;
		this.gameLoop.setCycleCount(Timeline.INDEFINITE);
		
		KeyFrame kf = new KeyFrame(
				Duration.seconds(0.1), 
				(ev ->{
					if (this.time == 10000) {
						System.out.println("fini");
						this.gameLoop.stop();
					} else if (this.time % 5 == 0) {
						System.out.println("un tour");
						
						if (this.indEnnemy < this.waveEnnemy.sizeOfEnnemies() - 1) {
							this.indEnnemy++;
							this.eView.addEnnemy(this.waveEnnemy.getEnnemy(this.indEnnemy));
						}
							
						for (int i = this.indEnnemy; i >= 0; i--)
							this.waveEnnemy.getEnnemy(i).act();
					}
					this.time++;
				}));
		
		this.gameLoop.getKeyFrames().add(kf);
	}
}