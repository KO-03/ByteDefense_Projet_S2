/*
 * OffensiveEnnemy.java
 * Cette classe represente un objet OffensiveEnnemy, c'est-a-dire un ennemi qui attaque des tourelles, ses responsabilites sont de :
 * - attaquer une tourelle
 */

package byteDefense.model.enemies;

import byteDefense.model.GameEnvironment;
import byteDefense.utilities.BFS;

public abstract class OffensiveEnnemy extends Ennemy {

	public OffensiveEnnemy(BFS bfsMap, GameEnvironment gameEnv) {
		super(bfsMap, gameEnv);
	}

	public void attackTower() {
		super.shootHandler();
	}
}
