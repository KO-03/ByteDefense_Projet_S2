/*
 * GameMaster.java
 * Cette classe represente un maitre de jeu, ses responsabilites sont de:
 * - rassembler les differentes actions ayant lieu lors d'un tour
 * - lancer le bfs pour le deplacement des ennemies
 * - faire tourner les vagues
 * - faire agir tous les ennemies et de les retirer s'ils sont morts ou s'ils sont arrivé
 *
 */

package byteDefense.model;

import byteDefense.model.ennemies.Ennemy;
import byteDefense.model.ennemies.Wave;
import byteDefense.utilities.BFS;

public class GameMaster {

	private Wave waveEnnemy;
	private GameArea gameArea;
	private BFS bfsMap;
	
	public GameMaster() {
		this.gameArea = new GameArea();
		this.bfsMap = new BFS(21);
		
		this.bfsMap.createPathList(gameArea);
		
		this.waveEnnemy = new Wave(1, bfsMap); 
	}
	
	public GameArea getMap() {
		return this.gameArea;
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
		
		Ennemy ennemy;
		for (int i = this.waveEnnemy.sizeOfEnnemies() - 1; i >= 0; i--) {
			ennemy = this.waveEnnemy.getEnnemies().get(i);
			
			this.waveEnnemy.verifyEnnemi(gameArea, ennemy);
		}
	}
}
