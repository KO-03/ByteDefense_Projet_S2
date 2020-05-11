package byteDefense.model.tiles;

public class Tile {
	
	private int x;
	private int y;
	String fileSource;
	
	public Tile(int x, int y, String source) {
		this.x = x;
		this.y = y;
		this.fileSource = source; 
	}

	public String getSource() {
		return this.fileSource;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

}