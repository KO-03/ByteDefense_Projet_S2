/*
 * Cette classe fait le lien entre le modele et la vue, ses responsabilitees sont :
 * - initialiser la TileMap
 * - gerer la gameLoop
 */

package byteDefense.controller;

import java.net.URL;
import java.util.ResourceBundle;

import byteDefense.model.TileMap;
import byteDefense.model.ennemies.Ennemy;
import byteDefense.model.ennemies.Rookit;
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
	
	private Ennemy ennemy;
	private TileMap map;
    private Timeline gameLoop;
    private int time;
    
    private int startTile =20;
    
    
    BFS bfsMap = new BFS(43);//43
    
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.map = new TileMap();
		new TileMapView(this.map, this.gameBoard);
		bfsMap.createPathList();// cree la liste des chemins parcourables
		
		bfsMap.fillGraph(); // liste avec les voisins
		bfsMap.BFS_algo(14); // point d'arrivee et de lancement de l'algo
		
		this.ennemy = new Rookit();
		new EnnemyView(this.ennemy, this.gridEnnemies);
		
		this.initAnimation();
		this.gameLoop.play();
    }

    private void moveEnnemy(int startTile) {
    	int i =startTile;
		int xPos = (int) bfsMap.pathList.get(i).getX();//recupere le X et Y du Point2D selon l'indice
		int yPos = (int) bfsMap.pathList.get(i).getY();
		System.out.println(" x :" + xPos + " y :" + yPos);
		this.ennemy.setX(xPos*48);
		this.ennemy.setY(yPos*48);
    }
    
    private void initAnimation() {
		this.gameLoop = new Timeline();
		this.time = 0;
		this.gameLoop.setCycleCount(Timeline.INDEFINITE);

		KeyFrame kf = new KeyFrame(
				Duration.seconds(0.1), 
				(ev ->{
					if (this.time == 100000) {
						System.out.println("fini");
						gameLoop.stop();
					} else if (this.time % 5 == 0) {
						System.out.println("un tour");
						this.moveEnnemy(startTile);
						if(startTile>0)//finish point pas la meileure façon // to review //
							startTile--;
						//this.ennemy.moveRandomlyEnnemy();
					}
					this.time++;
				}));
		
		this.gameLoop.getKeyFrames().add(kf);
	}
}