/*
 * TileMapView.java
 * Cette classe gére la partie visuelle de la Tile Map, ses responsabilités sont de :
 * - récupèrer et afficher l'image corrspondant à la tuile.
 * 
 */
package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.TileMap;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class TileMapView {

	// Données correspondant aux composantes visuelles (tiles) de la tilemap 
	public static final int CORNER = 1;
	public static final int HORIZONTAL_PATH = 2;
	public static final int VERTICAL_PATH = 3;
	public static final int VERTICAL_WALL = 4;
	public static final int INTERSECTION_PATH = 5;
	public static final int HORIZONTAL_WALL = 6;
	public static final int TOWER_ZONE = 7;
	
	//url des images des tuiles
	private static final File CORNER_SRC_IMG = new File("./resources/corner.png"); 
	private static final File HORIZONTAL_PATH_SRC_IMG = new File("./resources/horizontal_path.png"); 
	private static final File VERTICAL_PATH_SRC_IMG= new File("./resources/vertical_path.png"); 
	private static final File VERTICAL_WALL_SRC_IMG = new File("./resources/vertical_wall.png"); 
	private static final File INTERSECTION_PATH_SRC_IMG = new File("./resources/intersection_path.png"); 
	private static final File HORIZONTAL_WALL_SRC_IMG = new File("./resources/horizontal_wall.png"); 
	private static final File TOWER_ZONE_SRC_IMG = new File("./resources/tower_zone.png"); 

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
		case CORNER:
			tileFile = CORNER_SRC_IMG;
			break;
		case HORIZONTAL_PATH:
			tileFile = HORIZONTAL_PATH_SRC_IMG;
			break;
		case VERTICAL_PATH:
			tileFile = VERTICAL_PATH_SRC_IMG;
			break;
		case VERTICAL_WALL:
			tileFile = VERTICAL_WALL_SRC_IMG;
			break;
		case INTERSECTION_PATH:
			tileFile = INTERSECTION_PATH_SRC_IMG;
			break;
		case HORIZONTAL_WALL:
			tileFile = HORIZONTAL_WALL_SRC_IMG;
			break;
		case TOWER_ZONE:
			tileFile = TOWER_ZONE_SRC_IMG;
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