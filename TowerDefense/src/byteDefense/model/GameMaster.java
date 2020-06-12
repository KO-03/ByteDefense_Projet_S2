/*
 * GameMaster.java
 * Cette classe represente le GameMaster (maitre de jeu), ses responsabilites sont de:
 * - stocker, initialiser et recuperer les donnees necessaires au fonctionnement du jeu (classe des services
 *   des vagues, la liste des waves, BFS, le plateau du jeu, l'environnement du jeu, le numero de la vague en 
 *   cours, l'argent en byteCoin du joueur)
 * - recuperer, incrementer supprimer la vague dont tous les ennemis ont ete ajoutes (celle au-dessus de la liste des vagues) 
 * - verifier si une tourelle est placeable sur une tuile du plateau de jeu
 * - verifier si une tuile est occupee ou non par une tourelle
 * - verifier si tous les ennemis de toutes vagues ont ete ajoutees
 * - realiser les actions de l'environnement
 * - gerer le gain et le debit de byteCoin
 * - recuperer le numero d'une vague
 * - verifie si une vague est en cours, c'est-a-dire si le numero de la vague est egale a celui de la vague a ajouter 
 *   (la premier de la liste) 
 * - realiser les actions qui ont lieu pendant un tour de la vague (ajout d'un ennemi dans l'environnement, suppression 
 *   de la vague ajoute, deplacement des ennemis, gain d'argent chaque tour)
 * - verifier si les ennemis ont reussit a infecte l'ordinateur,
	 * c'est-a-dire si la progression de l'infection est nulle
 * - verifier si le joueur a gagne ou pas la partie (les ennemis de toutes les vagues ont ete ajoutes, tous les ennemis 
 *   ont ete tues, les ennemis n'ont pas reussit a infecte l'ordinateur)
 * - verifier si le joueur a perdu ou pas la partie, c'est-a-dire si les ennemis ont reussit a infecte l'ordinateur
 */

package byteDefense.model;

import java.util.ArrayList;

import byteDefense.model.enemies.Wave;
import byteDefense.model.enemies.WaveServices;
import byteDefense.model.towers.Tower;
import byteDefense.utilities.BFS;
import byteDefense.utilities.WaveReader;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameMaster {

	private static final int LAPTOP_HP = 645; // Point de vie de l'ordinateur a proteger
	
	private GameArea gameArea; // Plateau de jeu
	private BFS bfs;
	private GameEnvironment gameEnv; // Environnement de jeu
	private WaveServices waveServices; // regroupe les actions appliquees aux vagues
	private ArrayList<Wave> waves; // Vagues d'ennemis a ajouter
	private IntegerProperty waveInProgressNbrProperty; // Numero de la vague en cours
	private IntegerProperty byteCoinProperty; // byteCoin du joueur

	public GameMaster() {
		this.gameArea = new GameArea(1);
		this.bfs = new BFS(this.gameArea);
		this.gameEnv = new GameEnvironment();
		this.waves = WaveReader.generateWaves("./resources/waves_informations.txt");
		this.waveServices = new WaveServices(this.bfs, this.gameEnv); 
		this.waveInProgressNbrProperty = new SimpleIntegerProperty(0);
		this.byteCoinProperty = new SimpleIntegerProperty(15);
	}
	
	public GameArea getGameArea() {
		return this.gameArea;
	}

	public GameEnvironment getGameEnvironment() {
		return this.gameEnv;
	}
	
	public void removeEnvironmentTower(Tower tower) {
		this.gameEnv.removeTower(tower);
	}
	
	// Fonction qui retourne la premiere vague de la liste des vagues a ajouter
	private Wave getTopWave() {
		return this.waves.get(0);
	}
	
	public IntegerProperty getWaveInProgressNbrProperty() {
		return this.waveInProgressNbrProperty;
	}
	
	public int getWaveInProgressNbr() {
		return this.waveInProgressNbrProperty.getValue();
	}
	
	public IntegerProperty getByteCoinProperty() {
		return this.byteCoinProperty;
	}
	
	public final int getByteCoin() {
		return this.byteCoinProperty.getValue();
	}
	
	public int getInfectionProgress() {
		return this.gameEnv.getInfectingProgress();
	}
	
	public static int getLaptopHp() {
		return LAPTOP_HP;
	}
	
	// Fonction qui verifie si une tourelle est plaçable sur une tuile du plateau de jeu
	public boolean towerIsPlaceable(int x, int y) {
		return this.gameArea.isPlaceable(x, y);
	}
	
	// Fonction qui verifie si une tuile du plateau de jeu est deja prise par une tourelle ou non
	public boolean tileIsReserved(int x, int y) {
		return this.gameEnv.checkTowerPosition(x, y);
	}
	
	// Methode qui supprime la premiere vague de la liste des vagues a ajouter
	private void removeTopWave() {
		this.waves.remove(0);
	}
	
	// Fonction qui verifie si les ennemis de toutes les vagues ont ete ajoutes a l'environnnement ou non  
	private boolean allWavesAdded() {
		return this.waves.size() == 0;
	}
	
	public void incrementWaveInProgressNumber() {
		this.setWaveInProgressNbr(this.getWaveInProgressNbr() + 1);
	}
	
	private void setWaveInProgressNbr(int value) {
		this.waveInProgressNbrProperty.setValue(value);
	}
	
	// Methode qui realise les actions de l'environnement de jeu
	public void actionGameEnvironment(){
		this.gameEnv.gameEnvironmentAction();
	}
	
	private void setByteCoin(int amount) {
		this.byteCoinProperty.set(amount);
	}

	/* Fonction qui ajoute des byteCoin au joueur en verifiant la transaction s'est deroulee
	 * normalement (le montant a debiter n'est pas negatif et ne depasse pas la limite d'un entier)
	 */
	public boolean addMoney(int amount) {
		if(amount < 0 || this.getByteCoin() + amount > Integer.MAX_VALUE)
			return false;
		else {
			this.setByteCoin(this.getByteCoin() + amount);
			return true;
		}
	}
	/* Fonction qui debite des bytCoin au joueur en verifiant si la transaction s'est deroulee  
	 * normalement (le joueur possede assez de byteCoin et le montant a debiter n'est pas negatif)
	 */
	public boolean debitMoney(int amount) {
		if(amount < 0 || this.getByteCoin() - amount < 0)
			return false;
		else {
			this.setByteCoin(this.getByteCoin() - amount);
			return true;
		}
	}
	
	// Fonction qui verifie si tous les ennemies de la vagues ont ete ajoutes a l'environnement ou non
	private boolean allEnemiesWaveSpawned(Wave wave) {
		return wave.isEmpty();
	}
	
	// Fonction qui retourne le numero de la vague donnee en parametre
	private int numberOfWave(Wave wave) {
		return wave.getWaveNumber();
	}
	
	/* Fonction qui verifie si une vague est en cours, c'est-a-dire si le numero de la vague
	 * est egale a celui de la vague a ajouter (la premier de la liste) 
	 */
	public boolean isWaveRunning() {
		try {
            return this.getWaveInProgressNbr() == this.numberOfWave(this.getTopWave());
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
	}
	
	/* Methode qui realise les actions qui ont lieu pendant un tour de la vague
	 * (ajout d'un ennemi dans l'environnement, suppression de la vague ajoute,
	 * deplacement des ennemis, gain d'argent chaque tour)  
	 */
	public void aTurn() throws Exception {
		// Toutes les vagues n'ont pas ete ajoutes a l'environnement et une vague est en cours
		if (!this.allWavesAdded() && this.isWaveRunning()) { 
			Wave wave = this.getTopWave();
			// Tous les ennemis de la vague en cours n'ont pas tous ete ajoutes
			if (!this.allEnemiesWaveSpawned(wave))  
				this.waveServices.addNewEnemy(wave);
			// Les ennemis sont soit morts soit arrivés
			if (this.gameEnv.enemiesIsEmpty()) 
				this.removeTopWave();
		}
		this.gameEnv.turnActions();
		this.addMoney(1);
	}
	
	/* Fonction qui verifie si les ennemis ont reussit a infecte l'ordinateur,
	 * c'est-a-dire si la progression de l'infection est nulle
	 */
	private boolean infectionSucceed() {
		return this.gameEnv.getInfectingProgress() >= LAPTOP_HP;
	}
		
	/* Fonction qui verifie si le joueur a gagne ou pas la partie, c'est-a-dire si : 
	 * - les ennemis de toutes les vagues ont ete ajoutes,
	 * - tous les ennemis ont ete tues,
	 * - les ennemis n'ont pas reussit a infecte l'ordinateur 
	 */
	public boolean playerWins() {
		return this.allWavesAdded() && this.gameEnv.enemiesIsEmpty() && ! this.infectionSucceed();
	}
	
	/* Fonction qui verifie si le joueur a perdu ou pas la partie, c'est-a-dire si 
	 * les ennemis ont reussit a infecte l'ordinateur 
	 */
	public boolean playerLooses() {
		return this.infectionSucceed();
	}
}
