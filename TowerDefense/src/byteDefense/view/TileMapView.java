/*
 * Partie visuelle de la Tile Map, récupère et affiche l'image corrspondant à la tuile.
 * 
 */
package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.tiles.TileMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class TileMapView {
	//url des images des tuiles
	private static final File CORNER_URL = new File("./resources/corner.png"); 
	private static final File HORIZONTAL_PATH_URL = new File("./resources/horizontal_path.png"); 
	private static final File VERTICAL_PATH_URL= new File("./resources/vertical_path.png"); 
	private static final File VERTICAL_WALL_URL = new File("./resources/vertical_wall.png"); 
	private static final File INTERSECTION_PATH_URL = new File("./resources/intersection_path.png"); 
	private static final File HORIZONTAL_WALL_URL = new File("./resources/horizontal_wall.png"); 
	private static final File TOWER_ZONE_URL = new File("./resources/tower_zone.png"); 

	private TileMap map;
	private TilePane gameBoard;

	public TileMapView(TileMap map, TilePane gameBoard) {
		this.map = map;
		this.gameBoard = gameBoard;
		this.generateMap();
	}

	public File getFile(int mapCase) {
		File tileFile = null;
		
		switch (mapCase) {
		case TileMap.CORNER:
			tileFile = CORNER_URL;
			break;
		case TileMap.HORIZONTAL_PATH:
			tileFile = HORIZONTAL_PATH_URL;
			break;
		case TileMap.VERTICAL_PATH:
			tileFile = VERTICAL_PATH_URL;
			break;
		case TileMap.VERTICAL_WALL:
			tileFile = VERTICAL_WALL_URL;
			break;
		case TileMap.INTERSECTION_PATH:
			tileFile = INTERSECTION_PATH_URL;
			break;
		case TileMap.HORIZONTAL_WALL:
			tileFile = HORIZONTAL_WALL_URL;
			break;
		case TileMap.TOWER_ZONE:
			tileFile = TOWER_ZONE_URL;
			break;
		}
		return tileFile;
	}

	private void generateMap() {
		File sourceFile;
		int tilesSize = TileMap.TILES_SIZE;

		for (int y = 0; y < tilesSize; y++) {
			for (int x = 0; x < tilesSize; x++) {
				sourceFile = this.getFile(this.map.getCase(x, y));
				
				if(sourceFile != null) {
					ImageView tile = new ImageView();
					
					try {
						Image img = new Image(sourceFile.toURI().toURL().toString());
						tile.setImage(img);
						tile.setX(x);
						tile.setY(y);
						this.gameBoard.getChildren().add(tile);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}		
			}
		}
	}

}
