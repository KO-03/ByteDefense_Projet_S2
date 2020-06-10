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

	private static final int PC_HP = 645; // Point de vie de l'ordinateur a proteger
	
	private GameArea gameArea; // Plateau de jeu
	private BFS bfs;
	private GameEnvironment gameEnv; // Environnement de jeu
	private WaveServices waveServices;
	private ArrayList<Wave> waves; // Vagues d'ennemis a ajouter
	private IntegerProperty waveInProgressNbrProperty; // Numero de la vague en cours
	private boolean waveStatus;
	private IntegerProperty walletProperty; // porte-monnaie du joueur

	public GameMaster() {
		this.gameArea = new GameArea();
		this.bfs = new BFS(this.gameArea);
		this.gameEnv = new GameEnvironment();
		this.waves = WaveReader.generateWaves("./resources/waves_informations.txt");
		this.waveServices = new WaveServices(this.bfs, this.gameEnv); 
		this.waveInProgressNbrProperty = new SimpleIntegerProperty(-1);
		this.walletProperty = new SimpleIntegerProperty(100);
		this.waveStatus = false;
	}
	
	public GameArea getGameArea() {
		return this.gameArea;
	}

	// Fonction qui verifie si une tourelle est plaçable sur une tuile du plateau de jeu
	public boolean towerIsPlaceable(int x, int y) {
		return this.gameArea.isPlaceable(x, y);
	}
	
	private Wave getTopWave() {
		return this.waves.get(0);
	}
	
	private void removeTopWave() {
		this.waves.remove(0);
	}
	
	// Fonction qui verifie si les ennemis de toutes les vagues ont ete ajoutes a l'environnnement ou non  
	private boolean allWavesAdded() {
		return this.waves.size() == 0;
	}
	
	public GameEnvironment getGameEnvironment() {
		return this.gameEnv;
	}
	
	// Fonction qui verifie si une tuile du plateau de jeu est deja prise par une tourelle ou non
	public boolean tileIsReserved(int x, int y) {
		return this.gameEnv.checkTowerPosition(x, y);
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

	/* Fonction qui ajoute de l'argent au porte-monnaie du joueur en verifiant la transaction s'est deroulee
	 * normalement (le montant a debiter n'est pas negatif et ne depasse pas la limite d'un entier)
	 */
	public boolean addMoney(int amount) {
		if(amount < 0 || this.getWallet() + amount > Integer.MAX_VALUE)
			return false;
		else {
			this.setWallet(this.getWallet() + amount);
			return true;
		}
	}
	/* Fonction qui debite de l'argent au porte-monnaie du joueur en verifiant si la transaction s'est deroulee  
	 * normalement (le joueur possede assez d'argent et le montant a debiter n'est pas negatif)
	 */
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
	
	/* Methode qui realise les actions qui ont lieu pendant un tour de la vague
	 * (ajout d'un ennemi dans l'environnement, suppression de la vague ajoute,
	 * deplacement des ennemis, gain d'argent chaque tour)  
	 */
	public void aTurn() {
		// Toutes les vagues n'ont pas ete ajoutes a l'environnement
		if (!this.allWavesAdded() && this.waveStatus == true) {
			Wave wave = this.getTopWave();
			
			// La vague n'est pas terminee
			if (this.getWaveInProgressNbr() + 1 == wave.getWaveNumber()) { 
				// Tous les ennemis de la vague en cours n'ont pas tous ete ajoutes
				if (!this.allEnemiesWaveSpawned(wave)) 
					this.waveServices.addNewEnemy(wave);
				else
					removeTopWave();
			} else if (this.gameEnv.enemisIsEmpty()) 
				this.waveStatus = false;
		}
		this.gameEnv.enemiesMove();
	}
	
	public int getWaveInProgressNbr() {
		return this.waveInProgressNbrProperty.getValue();
	}
	
	public void incrementWaveNumber() {
		this.waveStatus = true;
		this.waveInProgressNbrProperty.setValue(this.getWaveInProgressNbr() + 1);
	}
	
	public IntegerProperty getWaveInProgressNbrProperty() {
		return this.waveInProgressNbrProperty;
	}

	
	public void setWaveInProgressNbr(int value) {
		this.waveInProgressNbrProperty.setValue(value);
	}
	
	public boolean isWaveRunning() {		
		return waveStatus;
	}
	
	/* Fonction qui verifie si les ennemis ont reussit a infecte l'ordinateur,
	 * c'est-a-dire si la somme des points d'attaque des ennemis qui ont atteint 
	 * le point d'arrivee est superieure aux points de vie de l'ordinateur
	 */
	private boolean infectionSucceed() {
		return this.gameEnv.getInfectingProgress() >= PC_HP;
	}
	
	/* Fonction qui verifie si le joueur a gagne ou pas la partie, c'est-a-dire si : 
	 * - tous les ennemis de la vague ont ete ajoutes,
	 * - tous les ennemis sont morts
	 * - et les ennemis n'ont pas reussit a infecte l'ordinateur 
	 */
	public boolean playerWins() {
		return this.allWavesAdded() && this.gameEnv.enemisIsEmpty() && ! this.infectionSucceed();
	}
	
	/* Fonction qui verifie si le joueur a perdu ou pas la partie, c'est-a-dire si 
	 * les ennemis ont reussit a infecte l'ordinateur 
	 */
	public boolean playerLooses() {
		return this.infectionSucceed();
	}
}
