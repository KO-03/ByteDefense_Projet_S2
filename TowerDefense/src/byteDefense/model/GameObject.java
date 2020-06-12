/*
 * GameObject.java
 * Cette classe parent represente un objet de jeu (un ennemi, une tourelle ou un tir), ses responsabilites sont de :
 * - l'identifier par un identifiant recuperable
 * - stocker, recuperer et fixer ses coordonnees xy 
 */

package byteDefense.model;

public abstract class GameObject {
	
	protected static int counterId = 0;
	private int id;

	public GameObject() {
		this.id = counterId;
		counterId++;
	}
	
	public int getId() {
		return this.id;
	}
	
	public abstract int getX();
	
	public abstract int getY();
}
