/*
 * Wave.java
 * Cette classe represente un objet Wave, ces responsabilites sont de :
 * - stocker et recuperer le numero de la vague
 * - stocker et recuperer la vitesse de deplacement des ennemis de la vague
 * - stocker et recuperer la cadence d'ajout des ennemis de la vague
 * - stocker une liste d'ennemis a ajouter a l'environnement
 * - verifier si tous les ennemis ont ete ajoutees (si la liste est vide) 
 * - supprimer un ennemi de la liste
 * - decrementer la quantite de l'ennemi ajoute
 * - verifie si tous les ennemis d'un type ont ete ajoutes a l'environnement ou non
 * - mettre a jour la quantite d'un ennemi ajoute et de la vague si tous les ennemis de ce type ont ete ajoutes
 * - recuperer un ennemi de la vague aleatoirement
 * - recuperer le type d'un ennemi a ajoute
 */

package byteDefense.model.enemies;

import java.util.ArrayList;

public class Wave {

	private int waveNumber;
	private int moveSpeed;
	private int spawnRate;
	private ArrayList<WaveEnemy> waveEnemies;
	
	public Wave(int waveNumber, int moveSpeed, int spawnRate, ArrayList<WaveEnemy> waveEnemies) {
		this.waveNumber = waveNumber;
		this.moveSpeed = moveSpeed;
		this.spawnRate = spawnRate;
		this.waveEnemies = waveEnemies;
	}
	
	public int getWaveNumber() {
		return this.waveNumber;
	}
	
	public int getMoveSpeed() {
		return this.moveSpeed;
	}
	
	public int getSpawnRate() {
		return this.spawnRate;
	}
	
	// Fonction qui verifie si la liste d'ennemis a ajouter est vide ou non
	public boolean isEmpty() {
		return this.waveEnemies.size() == 0;
	}
	
	private void removeWaveEnemy(WaveEnemy waveEnemy) {
		this.waveEnemies.remove(waveEnemy);
	}
	
	private void decrementEnemyQty(WaveEnemy waveEnemy) {
		waveEnemy.decrementQuantity();
	}
	
	// Fonction qui verifie si tous les ennemis d'un type ont ete ajoutes a l'environnement ou non
	private boolean checkSpawnedEnemiesType(WaveEnemy waveEnemy) {
		return waveEnemy.everyEnemiesSpawned();
	}
	
	// Methode qui met a jour la quantite d'un ennemi ajoute et de la vague si tous les ennemis de ce type ont ete ajoutes
	public void updateWave(WaveEnemy waveEnemy) {
		this.decrementEnemyQty(waveEnemy);
		// Tous les ennemis du meme type ont ete ajoutes
		if (this.checkSpawnedEnemiesType(waveEnemy))
			this.removeWaveEnemy(waveEnemy);
	}
	
	public WaveEnemy getWaveEnemyRandomly() {
		return this.waveEnemies.get((int)(Math.random() * waveEnemies.size()));
	}
	
	public int getEnemyType(WaveEnemy waveEnemy) {
		return waveEnemy.getEnemyType();
	}

	public String toString() {
		return "Wave [waveNumber=" + waveNumber + ", moveSpeed=" + moveSpeed + ", spawnRate=" + spawnRate
				+ ", waveEnemies=" + waveEnemies + "]";
	}
}
