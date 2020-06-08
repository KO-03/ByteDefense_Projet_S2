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

	private static final int LOOSE_LIMIT = 60000000;
	private IntegerProperty waveNumber;
	private boolean waveStatus;
	
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
		this.walletProperty = new SimpleIntegerProperty(100);
		this.waveNumber = new SimpleIntegerProperty(-1);
		this.waveStatus = false;
	}
	
	public GameArea getGameArea() {
		return this.gameArea;
	}
	
	private Wave getTopWave() {
		return this.waves.get(0);
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

	//Renvoie true si la transaction est réussie
		private boolean addMoney(int amount) {
			if(amount < 0 || this.getWallet() + amount > Integer.MAX_VALUE)
				return false;
			else {
				this.setWallet(this.getWallet() + amount);
				return true;
			}
		}
		
		//Renvoie true si la transaction est réussie et false si ressources insuiffisante
		public boolean debitMoney(int amount) {
			if(amount < 0 || this.getWallet() - amount < 0)
				return false;
			else {
				this.setWallet(this.getWallet() - amount);
				return true;
			}
		}
	
	// Fonction qui verifie si tous les ennemies de la vagues ont ete ajoutes a l'environnement ou non
	private boolean allEnemiesWaveSpawned(Wave wave) {
		return wave.isEmpty();
	}
	
	public boolean tileIsReserved(int x, int y) {
		return this.gameEnv.checkTowerPosition(x, y);
	}
	
	public void aTurn() {
		if (this.waves.size() != 0 && this.waveStatus == true) {
			Wave wave = this.getTopWave(); // vague en cours
			
			if (this.getWaveNumber()+1 == wave.getWaveNumber()) {
				// Ajout d'un ennemi a la vague lorsqu'ils n'ont pas tous ete ajoutes
				if (!this.allEnemiesWaveSpawned(wave)) 
					this.waveServices.addNewEnemy(wave);
				else
					removeTopWave();
			} else if (gameEnv.getEnemies().size() == 0)
				this.waveStatus = false;		
		}
			
		this.gameEnv.enemiesMove();
		this.addMoney(1);
	}
	
	public void incrementWaveNumber() {
		this.waveStatus = true;
		this.waveNumber.setValue(this.getWaveNumber()+1);
	}
	
	public int getWaveNumber() {
		return waveNumber.getValue();
	}
		
	public IntegerProperty getWaveNumberProperty() {
		return waveNumber;
	}

	public void setWaveNumber(int value) {
		this.waveNumber.setValue(value);
	}

	public boolean isWaveRunning() {		
		return waveStatus;
	}
	
	/*
	 * 0 : continuer
	 * 1 : win
	 * 2 : loose
	 */
	public int winConditions() {
		if (this.gameEnv.getEnemyPassed() >= LOOSE_LIMIT)
			return 2;
		else if (waves.size() == 0 && gameEnv.getEnemies().size() == 0)
			return 1;
		else
			return 0;
	}
}
