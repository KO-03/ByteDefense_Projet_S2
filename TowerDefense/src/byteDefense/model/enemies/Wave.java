/*
 * Wave.java
 * Cette classe represente un objet Wave, ces responsabilites sont de :
 * - stocker et recuperer le numero de la vague
 * - stocker et recuperer une pile d'ennemis
 * - recuperer l'ennemi du haut de la pile
 * - recuperer le type de l'ennemi du haut de la pile
 * - decrementer la quantite de l'ennemi du haut de la pile
 * - verifier si tous les ennemis ont ete ajoutees (si la pile est vide) 
 * - stocker et recuperer la vitesse de deplacement des ennemis de la vague
 * - stocker et recuperer la cadence d'ajout des ennemis de la vague
 * - 
 */

package byteDefense.model.enemies;

import java.util.Stack;

public class Wave {

	private int waveNumber;
	private int moveSpeed;
	private int spawnRate;
	private Stack<WaveEnemy> waveEnemies;
	
	public Wave(int waveNumber, int moveSpeed, int spawnRate, Stack<WaveEnemy> waveEnemies) {
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
	
	private WaveEnemy getTopWaveEnemy() {
		return this.waveEnemies.peek();
	}
	
	public int getEnemyType() {
		return this.getTopWaveEnemy().getEnemyType();
	}
	
	public boolean isEmpty() {
		return this.waveEnemies.empty();
	}
	
	public void removeWaveEnemy() {
		this.waveEnemies.pop();
	}
	
	public void decrementEnemyQty() {
		this.getTopWaveEnemy().decrementQuantity();
	}
	
	public boolean checkSpawnedEnemies() {
		return this.getTopWaveEnemy().everyEnemiesSpawned();
	}
}
