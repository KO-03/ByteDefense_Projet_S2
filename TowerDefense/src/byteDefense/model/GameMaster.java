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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameMaster {

	private WaveServices waveServices;
	private ArrayList<Wave> waves;
	private GameArea gameArea;
	private BFS bfs;
	private GameEnvironment gameEnv;
	private final IntegerProperty walletProperty;

	public GameMaster(int initialWallet) {
		this.gameArea = new GameArea();
		this.bfs = new BFS(this.gameArea);
		this.gameEnv = new GameEnvironment();
		this.waves = WaveReader.generateWaves("./resources/waves_informations.txt");
		this.waveServices = new WaveServices(this.bfs, this.gameEnv); 
		this.walletProperty = new SimpleIntegerProperty(initialWallet);
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
	
	public final int getWallet() {
		return this.walletProperty.getValue();
	}
	
	public IntegerProperty getWalletProperty() {
		return this.walletProperty;
	}
	
	public void setWallet(int amount) {
		this.walletProperty.set(amount);
	}

	public boolean addMoney(int amount) {
		if(amount<0 || this.getWallet()+amount>Integer.MAX_VALUE)
			return false;
		else {
			this.setWallet(this.getWallet()+amount);
			return true;
		}
	}
	
	public boolean debitMoney(int amount) {
		if(amount<0 || this.getWallet()-amount<0)
			return false;
		else {
			this.setWallet(this.getWallet()-amount);
			return true;
		}
	}
	
	public void aTurn() {
		// Ajout d'un ennemi a la vague lorsqu'ils n'ont pas tous ete ajoutes
		if (!this.waves.get(0).isEmpty())
			this.waveServices.fillEnnemyList(this.waves.get(0));
		
		this.gameEnv.gameObjectsHandler(this.bfs);
	}
}
