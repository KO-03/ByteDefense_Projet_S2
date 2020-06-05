/*
 * Tower.java
 * Cette classe represente un objet Tower (une tourelle), ses responsabilites sont de :
 * - attaquer un ennemi
 */

package byteDefense.model.towers;

import byteDefense.model.Bullet;
import byteDefense.model.GameArea;
import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.model.enemies.Enemy;

public abstract class Tower extends LivingObject {
	
	public Tower(int x, int y, GameEnvironment gameEnv) {
		super(x, y, 100, gameEnv);
	}

	public abstract int getCost();
	
	public void shoot(Enemy target) {
		super.getGameEnvironment().addBullet(new Bullet(getX(), getY(), target, this));
	}
	
	public void attackEnnemy() {
		Enemy target = this.findTarget();
		
		if (target != null)
			this.shoot(target);
	}
	
	public Enemy findTarget() {
		for(Enemy enemy : super.getGameEnvironment().getEnemies()) {
			if ((this.getY() - this.getAttackRange() * GameArea.TILE_SIZE <= enemy.getY() && enemy.getY() <= this.getY() + this.getAttackRange() * GameArea.TILE_SIZE) &&
				(this.getX() - this.getAttackRange() * GameArea.TILE_SIZE <= enemy.getX() && enemy.getX() <= this.getX() + this.getAttackRange() * GameArea.TILE_SIZE)) {
				return enemy;
			}
		}
		return null;
	}
	
	public abstract void attack();
}
