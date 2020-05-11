package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.tiles.TileMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class TileMapView {

	private TileMap map;
	private TilePane gameBoard;
	
	public TileMapView(TileMap map, TilePane gameBoard) {
		this.map = map;
		this.gameBoard = gameBoard;
		this.generateMap();
	}
	
	private void generateMap() {
		File f;
		int tilesSize = TileMap.TILES_SIZE;
		
		for (int y = 0; y < tilesSize; y++) {
			for (int x = 0; x < tilesSize; x++) {
				f = new File("./resources/" + this.map.getCaseNamePng(this.map.getCase(x, y)) + ".png");
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
