/*
 * Tower.java
 * Cette classe represente un objet Tower (une tourelle), ses responsabilites sont de :
 * - attaquer un ennemi
 * - stocker, recuperer et changer l'etat de gele de la tourelle
 * - stocker, recuperer et fixer les coordonnees xy de la tourelle
 */

package byteDefense.model.towers;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.model.enemies.Enemy;
import byteDefense.utilities.ShootUtilities;

public abstract class Tower extends LivingObject {
	
	private int x;
	private int y;
	public boolean frozen; // indique si la tourelle est gelee ou non
	
	public Tower(int x, int y, int defense, GameEnvironment gameEnv) {
		super(100, defense, gameEnv);
		this.x = x;
		this.y = y;
		this.frozen = false;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean getFrozen() {
		return this.frozen;
	}
	
	// Methode qui gele ou degele un enemi selon son etat
	public void changeFrozen() {
		if (this.frozen)
			this.frozen = false;
		else 
			this.frozen = true;
	}
	
	// Methode qui fait attaquer un ennemi a la portee de la tourelle
	public void attackEnemy() {
		GameEnvironment gameEnv = super.getGameEnvironment();
		Enemy target = (Enemy)ShootUtilities.findTarget(gameEnv.getEnemies(), this);
		
		// une cible a ete trouvee aux alentours de l'ennemi
		if (target != null)
			ShootUtilities.shoot(this, target, gameEnv);
	}
	
	public abstract int getCost();
}
