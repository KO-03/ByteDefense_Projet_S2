package byteDefense.model.tiles;

import java.io.BufferedReader;
import java.io.FileReader;

public class TileMap {
	
	public static final int TILES_SIZE = 13;
	private static final int CORNER = 1;
	private static final int HORIZONTAL_PATH = 2;
	private static final int VERTICAL_PATH = 3;
	private static final int VERTICAL_WALL = 4;
	private static final int INTERSECTION_PATH = 5;
	private static final int HORIZONTAL_WALL = 6;
	private static final int TOWER_ZONE = 7;

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
	
	public String getCaseNamePng(int mapCase) {
		String nameUrlTile = "";
		
		switch (mapCase) {
			case TileMap.CORNER:
				nameUrlTile = "corner";
				break;
			case TileMap.HORIZONTAL_PATH:
				nameUrlTile = "horizontal_wall";
				break;
			case TileMap.VERTICAL_PATH:
				nameUrlTile = "vertical_wall";
				break;
			case TileMap.VERTICAL_WALL:
				nameUrlTile = "vertical_path";
				break;
			case TileMap.INTERSECTION_PATH:
				nameUrlTile = "intersection_path";
				break;
			case TileMap.HORIZONTAL_WALL:
				nameUrlTile = "horizontal_path";
				break;
			case TileMap.TOWER_ZONE:
				nameUrlTile = "tower_zone";
				break;
		}
		return nameUrlTile;
	}
	
}