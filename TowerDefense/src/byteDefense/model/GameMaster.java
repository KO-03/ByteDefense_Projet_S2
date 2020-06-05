/*
 * GameMaster.java
 * Cette classe represente le GameMaster (maitre de jeu), ses responsabilites sont de:
 * - stocker, initialiser et recuperer les donnees necessaires au fonctionnement du jeu 
 *   (classe des services des Wave, la liste des waves, BFS, le plateau du jeu, l'environnement du jeu...)
 * - supprimer la Wave fini (celle au-dessus de la liste de vague) 
 * - fixer l'argent du jeu 
 * - gerer le gain et le debit d'argent, ainsi que leurs exceptions
 * - rassembler les differentes actions qui ont lieu lors d'un tour (ajout d'ennemis a la vague, actions dans l'environnement, gain d'argent...) 
 */

package byteDefense.model;

import java.util.ArrayList;

import byteDefense.model.enemies.Wave;
import byteDefense.model.enemies.WaveServices;
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
	private IntegerProperty walletProperty;

	public GameMaster() {
		this.gameArea = new GameArea();
		this.bfs = new BFS(this.gameArea);
		this.gameEnv = new GameEnvironment();
		this.waves = WaveReader.generateWaves("./resources/waves_informations.txt");
		this.waveServices = new WaveServices(this.bfs, this.gameEnv); 
		this.walletProperty = new SimpleIntegerProperty(30);
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
	
	private void removeTopWave() {
		this.waves.remove(0);
	}
	
	public GameEnvironment getGameEnvironment() {
		return this.gameEnv;
	}
	
	public IntegerProperty getWalletProperty() {
		return this.walletProperty;
	}
	
	private final int getWallet() {
		return this.walletProperty.getValue();
	}
	
	private void setWallet(int amount) {
		this.walletProperty.set(amount);
	}

	private boolean addMoney(int amount) {
		if(amount < 0 || this.getWallet() + amount > Integer.MAX_VALUE)
			return false;
		else {
			this.setWallet(this.getWallet()+amount);
			return true;
		}
	}
	
	public boolean debitMoney(int amount) {
		if(amount < 0 || this.getWallet() - amount < 0)
			return false;
		else {
			this.setWallet(this.getWallet()-amount);
			return true;
		}
	}
	
	public void aTurn() {
		// Ajout d'un ennemi a la vague lorsqu'ils n'ont pas tous ete ajoutes
		if (!this.waves.get(0).isEmpty())
			this.waveServices.addEnnemy(this.waves.get(0));
		this.getGameEnvironment().enemiesMove();
		this.addMoney(1);
	}
}
