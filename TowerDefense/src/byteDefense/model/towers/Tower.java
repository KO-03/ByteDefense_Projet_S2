/*
 * Tower.java
 * Cette classe represente un objet Tower, ses responsabilites sont de :
 * - faire agir une tourelle
 */

package byteDefense.model.towers;

import byteDefense.model.GameObject;

public abstract class Tower extends GameObject {
	
	public Tower(int x, int y) {
		super(x, y);
	}
	
	public abstract void act();
}
