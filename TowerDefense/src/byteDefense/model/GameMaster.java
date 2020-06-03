/*
 * GameMaster.java
 * Cette classe represente un maitre de jeu, ses responsabilites sont de:
 * - stocker, initialiser et recuperer les donn�es n�cessaires au fonctionnement du jeu (BFS, gameArea, wave...)
 * - rassembler les differentes actions qui ont lieu lors d'un tour (actions de vagues d'ennemis, de tourelles...)
 * - 
 */

package byteDefense.model;

import java.util.ArrayList;

import byteDefense.model.ennemies.Wave;
import byteDefense.model.ennemies.WaveServices;
import byteDefense.utilities.BFS;
import byteDefense.utilities.WaveReader;

public class GameMaster {

	private WaveServices waveServices;
	private ArrayList<Wave> waves;
	private GameArea gameArea;
	private BFS bfs;
	private GameEnvironment gameEnv;
	
	public GameMaster() {
		this.gameArea = new GameArea();
		this.bfs = new BFS(this.gameArea);
		this.gameEnv = new GameEnvironment();
		this.waves = WaveReader.generateWaves("./resources/waves_informations.txt");
		this.waveServices = new WaveServices(this.bfs, this.gameEnv); 
	}
	
	public GameArea getGameArea() {
		return this.gameArea;
	}
	
	public BFS getBfs() {
		return this.bfs;
	}
	
	public WaveServices getWaveServices() {
		return this.waveServices;
	}
	
	public void removeTopWave() {
		this.waves.remove(0);
	}
	
	public GameEnvironment getGameEnvironment() {
		return this.gameEnv;
	}
	
	public void aTurn() {
		// Ajout d'un ennemi a la vague lorsqu'ils n'ont pas tous ete ajoutes
		if (!this.waves.get(0).isEmpty())
			this.waveServices.fillEnnemyList(this.waves.get(0));
			
		this.gameEnv.gameEnvironmentHandler(this.bfs);
	}
}
