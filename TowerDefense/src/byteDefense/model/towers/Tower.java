/*
 * Tower.java
 * Cette classe represente un objet Tower, ses responsabilites sont de :
 * - recuperer le cout d'une tourelle
 * - faire agir une tourelle
 */

package byteDefense.model.towers;

import byteDefense.model.GameEnvironment;
import byteDefense.model.GameObject;

public abstract class Tower extends GameObject {

	public Tower(int x, int y, GameEnvironment gameEnv) {
		super(x, y, 100, gameEnv);
	}

	public abstract int getCost();
	
	public void act() {
		super.shoot(super.findTarget());
	}
}
