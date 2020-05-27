package byteDefense.model;

import byteDefense.model.ennemies.Ennemy;
import byteDefense.model.ennemies.Wave;
import byteDefense.utilities.BFS;

public class GameMaster {

	private Wave waveEnnemy;
	private TileMap map;
	private BFS bfsMap;
	
	public GameMaster() {
		this.map = new TileMap();
		this.bfsMap = new BFS(21);
		
		this.bfsMap.createPathList(map);
		
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
	
	public void onTurn() {
		if (this.waveEnnemy.getIndLastEnnemySpawn() < this.waveEnnemy.getWaveEnnemiesQty())
			this.waveEnnemy.fillEnnemyList();
		
		Ennemy e;
		for (int i = this.waveEnnemy.sizeOfEnnemies() - 1; i >= 0; i--) {
			e = this.waveEnnemy.getEnnemies().get(i);
			
			if (e.getcurrentIndTile() > this.getBfs().ARRIVAL_POINT)
				e.act();
			else if (e.getcurrentIndTile() == this.getBfs().ARRIVAL_POINT)
				this.waveEnnemy.removeEnnemy(e);
		}
	}
}
