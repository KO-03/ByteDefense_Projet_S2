/*
 * Tower.java
 * Cette classe represente un objet Tower (une tourelle), ses responsabilites sont de :
 * - attaquer un ennemi
 */

package byteDefense.model.towers;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.model.enemies.Enemy;
import byteDefense.utilities.ShootUtilities;

public abstract class Tower extends LivingObject {
	
	public boolean frozen;
	
	public Tower(int x, int y, GameEnvironment gameEnv) {
		super(x, y, 100, gameEnv);
		this.frozen = false;
	}
	
	public boolean getFrozen() {
		return this.frozen;
	}
	
	public void changeFrozen() {
		if (this.frozen)
			this.frozen = false;
		else 
			this.frozen = true;
	}
	
	public void attackEnemy() {
		GameEnvironment gameEnv = super.getGameEnvironment();
		Enemy target = (Enemy)ShootUtilities.findTarget(gameEnv.getEnemies(), this);
		
		// une cible a ete trouvee aux alentours de l'ennemi
		if (target != null)
			ShootUtilities.shoot(this, target, gameEnv);
	}
	
	public abstract int getCost();
}
