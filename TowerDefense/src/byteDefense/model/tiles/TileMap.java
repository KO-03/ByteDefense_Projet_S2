package byteDefense.model.tiles;

import java.io.BufferedReader;
import java.io.FileReader;

public class TileMap {
	
	public static final int TILES_SIZE = 13;
	
	// Données correspondant aux composantes visuelles (tiles) de la tilemap 
	public static final int CORNER = 1;
	public static final int HORIZONTAL_PATH = 2;
	public static final int VERTICAL_PATH = 3;
	public static final int VERTICAL_WALL = 4;
	public static final int INTERSECTION_PATH = 5;
	public static final int HORIZONTAL_WALL = 6;
	public static final int TOWER_ZONE = 7;

	private int[] tiles;
	
	public TileMap() {
		this.tiles = new int[169];
		this.setTiles();
	}
	
	public void setTiles() {
		try {
			FileReader input = new FileReader("./resources/tiles.txt");
			BufferedReader br = new BufferedReader(input);
		    String line = null;
		    
		    while ((line = br.readLine()) != null) {
		    	String[] values = line.split(", ");
		    	for (int i = 0; i < values.length; i++) {
		    		this.tiles[i] = Integer.parseInt(values[i]);
		    	}
		    }
		    
		    br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getCase(int x, int y) {
		return tiles[x + TILES_SIZE * y];
	}
}