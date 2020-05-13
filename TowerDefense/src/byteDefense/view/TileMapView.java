package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.tiles.TileMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class TileMapView {
	
	private static final File fImg1 = new File("./resources/corner.png");
	private static final File fImg2 = new File("./resources/horizontal_wall.png"); 
	private static final File fImg3 = new File("./resources/vertical_wall.png"); 
	private static final File fImg4 = new File("./resources/vertical_path.png");
	private static final File fImg5 = new File("./resources/intersection_path.png");
	private static final File fImg6 = new File("./resources/horizontal_path.png");
	private static final File fImg7 = new File("./resources/tower_zone.png");
	
	private TileMap map;
	private TilePane gameBoard;
	
	public TileMapView(TileMap map, TilePane gameBoard) {
		this.map = map;
		this.gameBoard = gameBoard;
		this.generateMapView();
	}
	
	private void generateMapView() {
		File fImg;
		int tilesSize = TileMap.TILES_SIZE;
		
		for (int y = 0; y < tilesSize; y++) {
			for (int x = 0; x < tilesSize; x++) {
				fImg = checkFileImgTile(x, y);
				
				if (fImg != null) {
					ImageView tile = new ImageView();
					try {
						Image img = new Image(fImg.toURI().toURL().toString());
						
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
	
	private File checkFileImgTile(int x, int y) {
		File fImg = null;
		
		switch (this.map.getCase(x, y)) {
			case TileMap.CORNER:
				fImg = fImg1;
				break;
			case TileMap.HORIZONTAL_PATH:
				fImg = fImg2;			
				break;
			case TileMap.VERTICAL_PATH:
				fImg = fImg3;
				break;
			case TileMap.VERTICAL_WALL:
				fImg = fImg4;
				break;
			case TileMap.INTERSECTION_PATH:
				fImg = fImg5;
				break;
			case TileMap.HORIZONTAL_WALL:
				fImg = fImg6;
				break;
			case TileMap.TOWER_ZONE:
				fImg = fImg7;
				break;
		}
		return fImg;
	}
}