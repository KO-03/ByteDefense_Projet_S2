/*
 * Tower.java
 * Cette classe represente un objet Tower (une tourelle), ses responsabilites sont de :
 * - attaquer un ennemi
 */

package byteDefense.model.towers;

import byteDefense.model.GameEnvironment;
import byteDefense.model.GameObject;

public abstract class Tower extends GameObject {
	
	public Tower(int x, int y, GameEnvironment gameEnv) {
		super(x, y, 100, gameEnv);
	}

	public abstract int getCost();
	
	public void attackEnnemy() {
		super.shootHandler();
	}
}
