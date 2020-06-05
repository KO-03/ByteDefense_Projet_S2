/*
 * OffensiveEnemy.java
 * Cette classe represente un objet OffensiveEnemy, c'est-a-dire un ennemi qui attaque des tourelles, ses responsabilites sont de :
 * - attaquer une tourelle
 */

package byteDefense.model.enemies;

import byteDefense.model.Bullet;
import byteDefense.model.GameArea;
import byteDefense.model.GameEnvironment;
import byteDefense.model.towers.Tower;
import byteDefense.utilities.BFS;

public abstract class OffensiveEnemy extends Enemy {

	public OffensiveEnemy(BFS bfsMap, GameEnvironment gameEnv) {
		super(bfsMap, gameEnv);
	}
	
	public void shoot(Tower target) {
		super.getGameEnvironment().addBullet(new Bullet(getX(), getY(), target, this));
	}
	
	public void attackTower() {
		Tower target = this.findTarget();
		
		if (target != null)
			this.shoot(target);
	}
	
	public Tower findTarget() {
		for(Tower tower : super.getGameEnvironment().getTowers()) {
			if ((this.getY() - this.getAttackRange() * GameArea.TILE_SIZE <= tower.getY() && tower.getY() <= this.getY() + this.getAttackRange() * GameArea.TILE_SIZE) &&
				(this.getX() - this.getAttackRange() * GameArea.TILE_SIZE <= tower.getX() && tower.getX() <= this.getX() + this.getAttackRange() * GameArea.TILE_SIZE)) {
				return tower;
			}
		}
		return null;
	}
	
	public abstract void attack();
}
