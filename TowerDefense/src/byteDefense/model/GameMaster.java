package byteDefense.model;

import byteDefense.model.ennemies.Wave;
import byteDefense.utilities.BFS;

public class GameMaster {

	private Wave waveEnnemy;
	private TileMap map;
	private BFS bfsMap;
	
	public GameMaster() {
		this.map = new TileMap();
		this.bfsMap = new BFS(21);
		this.waveEnnemy = new Wave(1, bfsMap); 
	}
	
	public TileMap getMap() {
		return this.map;
	}
	
	public BFS getBfs() {
		return this.bfsMap;
	}
	
	public Wave getWaveEnnemy() {
		return this.waveEnnemy;
	}
}
