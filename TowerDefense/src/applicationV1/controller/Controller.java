package applicationV1.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import applicationV1.model.tiles.TileMap;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class Controller implements Initializable {
	
	private TileMap map;
	
    @FXML
    private TilePane gameBoard;
	
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.map = new TileMap();
		this.generateMap();
	}
	
    private void generateMap() {
		File f;
		int tilesSize = TileMap.TILES_SIZE;
		
		for (int y = 0; y < tilesSize; y++) {
			for (int x = 0; x < tilesSize; x++) {
				f = new File("./resources/" + this.map.getCaseUrl(this.map.getCase(x, y)) + ".png");
				ImageView tile = new ImageView();
			
				try {
					Image img = new Image(f.toURI().toURL().toString());
					
					tile.setImage(img);
					tile.setX(x);
					tile.setY(y);
					
					this.gameBoard.getChildren().add(tile);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
    }
}
