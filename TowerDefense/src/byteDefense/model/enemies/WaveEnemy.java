/*
 * WaveEnemy.java
 * Cette classe gere les informations relatives aux ennemis a ajouter a une vague d'ennemis, ses reponsabilites sont de :
 * - stocker et recuperer le type de l'ennemi a ajouter
 * - stocker et decrementer la quantite d'ennemi a ajouter
 * - verifier si la quantite d'ennemis a ajouter est nulle
 */

package byteDefense.model.enemies;

public class WaveEnemy {
	private int enemyType;
	private int quantity;
	
	public WaveEnemy(int enemyType, int quantity) {
		this.enemyType = enemyType;
		this.quantity = quantity;
	}
	
	public int getEnemyType() {
		return this.enemyType;
	}
	
	public boolean everyEnemiesSpawned() {
		return this.quantity == 0;
	}
	
	public void decrementQuantity() {
		this.quantity--;
	}

	public String toString() {
		return "WaveEnemy [enemyType=" + enemyType + ", quantity=" + quantity + "]";
	}
}
