package byteDefense.model.tiles;

public class TileMap {
	
	public static final int TILES_SIZE = 13;
	private static final int CORNER = 1;
	private static final int HORIZONTAL_PATH = 2;
	private static final int VERTICAL_PATH = 3;
	private static final int VERTICAL_WALL = 4;
	private static final int INTERSECTION_PATH = 5;
	private static final int HORIZONTAL_WALL = 6;
	private static final int TOWER_ZONE = 7;

	private int[] tiles = {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 3, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 3, 3, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 3, 6, 6, 6, 6, 6, 6, 5, 7, 7, 7, 7, 7, 3, 3, 7, 7, 7, 7, 7, 4, 7, 7, 7, 7, 7, 3, 3, 7, 7, 7, 7, 7, 4, 7, 7, 7, 7, 7, 3, 3, 7, 7, 7, 7, 7, 4, 7, 7, 7, 7, 7, 3, 3, 7, 7, 7, 7, 7, 4, 7, 7, 7, 7, 7, 3, 3, 7, 7, 7, 7, 7, 5, 6, 6, 6, 6, 6, 6, 3, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 3, 3, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 3, 3, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 3, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1};
	
	public TileMap() {
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