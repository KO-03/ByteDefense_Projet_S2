/*
 * Tower.java
 * Cette classe represente un objet Tower, ses responsabilites sont de :
 * - recuperer le cout d'une tourelle
 * - faire agir une tourelle
 */

package byteDefense.model.towers;

import byteDefense.model.GameObject;

public abstract class Tower extends GameObject {
	
	public Tower(int x, int y) {
		super(x, y);
	}
	
	public abstract int getCost();
	
	public abstract void act();
}
