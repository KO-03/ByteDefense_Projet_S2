/*
 * WaveEntities.java
 * Cette classe gere les informations relatives aux ennemis a ajouter a une vague d'ennemis, ses reponsabilites sont de :
 * - stocker et recuperer le type de l'ennemi a ajouter
 * - stocker et decrementer la quantite d'ennemi du type a ajouter
 * - verifier si la quantite d'ennemis a ajouter est nulle
 */

package byteDefense.model.ennemies;

public class WaveEntities {
	private int ennemyType;
	private int quantity;
	
	public WaveEntities(int ennemyType, int quantity) {
		this.ennemyType = ennemyType;
		this.quantity = quantity;
	}
	
	public int getEnnemyType() {
		return this.ennemyType;
	}
	
	public boolean everyEnnemiesSpawned() {
		return quantity == 0;
	}
	
	public void decrementQuantity() {
		this.quantity--;
	}
}
