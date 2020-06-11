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
import javafx.collections.ObservableList;

public abstract class OffensiveEnemy extends Enemy {

	public OffensiveEnemy(BFS bfsMap, GameEnvironment gameEnv) {
		super(bfsMap, gameEnv);
	}
	
	public Tower findTowerTarget() {
		ObservableList<Tower> towers = super.getGameEnvironment().getTowers();
		Tower target = null;
		int i = 0;
		
		while (i < towers.size() && target == null) {
			target = (Tower)ShootUtilities.checkTargetPosition(towers.get(i), this);
			i++;
		}
		return target;
	}
	
	public void attackTower() {
		Tower target = findTowerTarget();
		// une cible a ete trouvee aux alentours de l'ennemi
		if (target != null)
			ShootUtilities.shoot(this, target, super.getGameEnvironment());
	}
}
