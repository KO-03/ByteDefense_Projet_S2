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
	private Stack<WaveEntities> waveEnemies;
	
	public Wave(int waveNumber, int moveSpeed, int spawnRate, Stack<WaveEntities> waveEnemies) {
		this.moveSpeed = moveSpeed;
		this.spawnRate = spawnRate;
		this.waveEnemies = waveEnemies;
	}
	
	public int getWaveNumber() {
		return this.waveNumber;
	}
	
	public Stack<WaveEntities> getEnemiesInfos() {
		return this.waveEnemies;
	}
	
	public int getEnnemyType() {
		return this.waveEnemies.peek().getEnnemyType();
	}
	
	public void removeWaveEnnemy() {
		this.waveEnemies.pop();
	}
	
	public void decrementEnnemyQty() {
		this.waveEnemies.peek().decrementQuantity();
	}
	
	public boolean isEmpty() {
		return this.waveEnemies.empty();
	}
	
	public int getMoveSpeed() {
		return this.moveSpeed;
	}
	
	public int getSpawnRate() {
		return this.spawnRate;
	}
}
