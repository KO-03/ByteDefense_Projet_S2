/*
 * Wave.java
 * Cette classe represente un objet Wave, ces responsabilites sont de :
 * - stocker le numero de la vague
 * - stocker et recuperer la vitesse de deplacement des ennemis de la vague
 * - stocker et recuperer la cadence d'ajout des ennemis de la vague
 * - stocker, recuperer, supprimer et empiler les ennemies a ajouter (WaveEntities) dans une pile d'ennemis 
 * - verifier si tous les ennemis ont ete ajoutees (si la pile est vide)
 */

package byteDefense.model.ennemies;


import java.util.Stack;

public class Wave {

	private int waveNumber;
	private int moveSpeed;
	private int spawnRate;
	private Stack<WaveEntities> waveEntities;
	
	public Wave(int waveNumber, int moveSpeed, int spawnRate, Stack<WaveEntities> waveEntities) {
		this.moveSpeed = moveSpeed;
		this.spawnRate = spawnRate;
		this.waveEntities = waveEntities;
	}
	
	public int getWaveNumber() {
		return this.waveNumber;
	}
	
	public Stack<WaveEntities> getEnnemiesInfos() {
		return this.waveEntities;
	}
	
	public int getMoveSpeed() {
		return this.moveSpeed;
	}

	
	public int getSpawnRate() {
		return this.spawnRate;
	}
	
	public void getWaveEntity() {
		this.waveEntities.peek();
	}
	
	public int getEnnemyType() {
		return this.waveEntities.peek().getEnnemyType();
	}
	
	public void removeWaveEntity() {
		this.waveEntities.pop();
	}
	
	public void decrementEnnemyQty() {
		this.waveEntities.peek().decrementQuantity();
	}
	
	public boolean isEmpty() {
		return this.waveEntities.empty();
	}
}
