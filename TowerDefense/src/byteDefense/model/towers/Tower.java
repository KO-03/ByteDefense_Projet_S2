/*
 * Tower.java
 * Cette classe represente un objet Tower.
 */

package byteDefense.model.towers;

import byteDefense.model.GameObject;

public abstract class Tower extends GameObject {
	
	public Tower(int x, int y) {
		super(x, y);
	}
	
	public abstract void act();
}
