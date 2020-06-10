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
import javafx.collections.ObservableList;

public abstract class Tower extends LivingObject {
	
	public boolean frozen;
	
	public Tower(int x, int y, GameEnvironment gameEnv) {
		super(x, y, 100, gameEnv);
		this.frozen = false;
	}

	public void effectDisperse() {
		this.changeFrozen();
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
		ObservableList<Enemy> enemies = super.getGameEnvironment().getEnemies();
		Enemy target = null;
		int i = 0;
		
		while (target == null && i < enemies.size()) {
			target = (Enemy)ShootUtilities.checkTargetPosition(enemies.get(i), this);
			i++;
		}
		// une cible a ete trouvee aux alentours de la tourelle
		if (target != null)
			ShootUtilities.shoot(this, target, super.getGameEnvironment());
	}
	
	public abstract void attack();
	
	public abstract int getCost();
}
