/*
 * OffensiveEnnemy.java
 * Cette classe represente un objet OffensiveEnnemy, c'est-a-dire un ennemi qui attaque des tourelles, ses responsabilites sont de :
 * - d'attaquer une tourelle
 */

package byteDefense.model.ennemies;

import byteDefense.model.Bullet;
import byteDefense.model.GameEnvironment;
import byteDefense.model.GameObject;
import byteDefense.utilities.BFS;

public abstract class OffensiveEnnemy extends Ennemy {

	public OffensiveEnnemy(BFS bfsMap, GameEnvironment gameEnv) {
		super(bfsMap, gameEnv);
	}

	public void attackTower() {
		GameObject target = super.findTarget();
		
		if (target != null)
			super.gameEnv.addBullet(new Bullet(super.getX(), super.getY(), target, this));
	}
}
