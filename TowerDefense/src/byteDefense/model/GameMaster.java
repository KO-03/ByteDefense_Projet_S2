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
	private BFS bfsMap;
	private int wallet;

	public GameMaster(int initialWallet) {
		this.gameArea = new GameArea();
		this.bfsMap = new BFS(this.gameArea);	
		this.waves = WaveReader.generateWaves("./resources/waves_informations.txt");
		this.waveServices = new WaveServices(this.bfsMap); 
		this.wallet=initialWallet;
	}
	
	public GameArea getGameArea() {
		return this.gameArea;
	}
	
	public BFS getBfs() {
		return this.bfsMap;
	}
	
	public WaveServices getWaveServices() {
		return this.waveServices;
	}
	
	public void removeTopWave() {
		this.waves.remove(0);
	}
	
	public void aTurn() {
		this.waveServices.waveHandler(this.waves.get(0));
	}
	
	public int getWallet() {
		return wallet;
	}

	public boolean addMoney(int amount) {
		if(amount<0 || this.wallet+amount>Integer.MAX_VALUE)
			return false;
		else {
			this.wallet += amount;
			return true;
		}
	}
	
	public boolean debitMoney(int amount) {
		if(amount<0 || this.wallet-amount<0)
			return false;
		else {
			this.wallet -= amount;
			return true;
		}
	}

}
