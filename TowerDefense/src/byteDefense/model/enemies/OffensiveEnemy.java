/*
 * OffensiveEnemy.java
 * Cette classe represente un objet OffensiveEnemy, c'est-a-dire un ennemi qui attaque des tourelles, ses responsabilites sont de :
 * - attaquer une tourelle
 */

package byteDefense.model.enemies;

import byteDefense.model.GameEnvironment;
import byteDefense.model.towers.Tower;
import byteDefense.utilities.BFS;
import byteDefense.utilities.ShootUtilities;

public abstract class OffensiveEnemy extends Enemy {

	public OffensiveEnemy(int defense, BFS bfsMap, GameEnvironment gameEnv) {
		super(defense, bfsMap, gameEnv);
	}
	
	// Methode qui fait attaquer un tourelle a la portee de la ennemi
	public void attackTower() { 
		GameEnvironment gameEnv = super.getGameEnvironment();
		Tower target = (Tower)ShootUtilities.findTarget(gameEnv.getTowers(), this);
		
		// une cible a ete trouvee aux alentours de l'ennemi
		if (target != null)
			ShootUtilities.shoot(this, target, gameEnv);
	}
}
