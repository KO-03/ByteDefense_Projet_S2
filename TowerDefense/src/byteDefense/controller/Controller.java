/*
 * Controller.java
 * Cette classe fait le lien entre le modèle et la vue, ses responsabilités sont :
 * - initialiser la TileMap
 * - gérer la gameLoop
 */

package byteDefense.controller;

import java.net.URL;
import java.util.ResourceBundle;

import byteDefense.model.tiles.TileMap;
import byteDefense.view.TileMapView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

public class Controller implements Initializable {
	
	@FXML
    private TilePane gameBoard;
	
	private TileMap map;
    private Timeline gameLoop;
    private int time;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.map = new TileMap();
		new TileMapView(this.map, this.gameBoard);
		
		initAnimation();
		this.gameLoop.play();
	}
	
    private void initAnimation() {
		this.gameLoop = new Timeline();
		this.time = 0;
		this.gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(
				Duration.seconds(0.1), 
				(ev ->{
					if (this.time == 100) {
						System.out.println("fini");
						gameLoop.stop();
					} else if (this.time % 5 == 0) {
						System.out.println("un tour");
					}
					time++;
				})
				);
		this.gameLoop.getKeyFrames().add(kf);
	}
}