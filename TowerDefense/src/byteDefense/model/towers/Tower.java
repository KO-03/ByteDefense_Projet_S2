/*
 * Tower.java
 * Cette classe represente un objet Tower, ses responsabilites sont de :
 * - recuperer le cout d'une tourelle
 * - faire agir une tourelle
 */

package byteDefense.model.towers;

import byteDefense.model.GameArea;
import byteDefense.model.GameEnvironment;
import byteDefense.model.GameObject;
import byteDefense.model.ennemies.Ennemy;

public abstract class Tower extends GameObject {

	public Tower(int x, int y, GameEnvironment gameEnv) {
		super(x, y, 100, gameEnv);
	}

	public abstract int getCost();

	public abstract void act();
	
	public GameObject findEnnemy() {
		for(GameObject gameObject : this.gameEnv.getGameObjectsList()){
			if(gameObject instanceof Ennemy){
				if((this.getY() - this.getAttackRange() * GameArea.TILE_SIZE <= gameObject.getY() && gameObject.getY() <= this.getY() + this.getAttackRange() * GameArea.TILE_SIZE) &&
				(this.getX() - this.getAttackRange() * GameArea.TILE_SIZE <= gameObject.getX() && gameObject.getX() <= this.getX() + this.getAttackRange() * GameArea.TILE_SIZE)) {
					System.out.println("Piou piou !");
					return gameObject;
				}
			}
		}
		return null;
	}
	
	public void shoot(GameObject ennemy) {
		if(ennemy != null) {
			ennemy.decrementHp((int) this.getAttack());
		}
	}
}
