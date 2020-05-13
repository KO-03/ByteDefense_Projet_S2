package byteDefense.model;

import java.io.BufferedReader;
import java.io.FileReader;

public class TileMap {
	
	public static final int TILES_SIZE = 14;
	private int[] tiles;
	
	public TileMap() {
		this.tiles = new int[196];
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