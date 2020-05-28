/*
 * GameMaster.java
 * Cette classe represente un maitre de jeu, ses responsabilites sont de:
 * - stocker, initialiser et recuperer les données nécessaires au fonctionnement du jeu (BFS, gameArea, wave...)
 * - rassembler les differentes actions qui ont lieu lors d'un tour (actions de vagues d'ennemis, de tourelles...)
 * - 
 */

package byteDefense.model;

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
	
	public GameArea getGameArea() {
		return this.gameArea;
	}
	
	public BFS getBfs() {
		return this.bfsMap;
	}
	
	public Wave getWaveEnnemy() {
		return this.waveEnnemy;
	}
	
	public void oneTurn() {
		this.waveEnnemy.waveHandler();
	}
}
