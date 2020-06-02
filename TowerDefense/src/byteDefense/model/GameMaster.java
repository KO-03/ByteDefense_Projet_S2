/*
 * GameMaster.java
 * Cette classe represente un maitre de jeu, ses responsabilites sont de:
 * - stocker, initialiser et recuperer les donn�es n�cessaires au fonctionnement du jeu (BFS, gameArea, wave...)
 * - rassembler les differentes actions qui ont lieu lors d'un tour (actions de vagues d'ennemis, de tourelles...)
 * - 
 */

package byteDefense.model;

import byteDefense.model.ennemies.Wave;
import byteDefense.model.towers.Tower;
import byteDefense.utilities.BFS;

public class GameMaster {

	private Wave waveEnnemy;
	private GameArea gameArea;
	private BFS bfsMap;
	private GameEnvironment gameEnv;
	
	public GameMaster() {
		this.gameArea = new GameArea();
		this.bfsMap = new BFS(this.gameArea);
		this.gameEnv = new GameEnvironment();
		this.waveEnnemy = new Wave(1, this.bfsMap, this.gameEnv);
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
	
	public GameEnvironment getGameEnvironment() {
		return this.gameEnv;
	}
	
	public void aTurn() {
		GameObject gameObject;

		this.waveEnnemy.waveHandler();
		
		for (int i = this.getGameEnvironment().getGameObjectsList().size() - 1; i >= 0; i--) {
			gameObject = this.getGameEnvironment().getGameObjectsList().get(i);

			if (gameObject instanceof Tower)
				gameObject.act();
			
			if(!gameObject.isAlive())
				gameEnv.removeGameObject(gameObject);
		}
	}
}
