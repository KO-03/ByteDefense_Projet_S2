/*
 * Ransomware.java
 * Cette classe represente un objet Ransomware, ses responsabilites sont de :
 * - stocker et recuperer son attaque
 * - stocker et recuperer sa defense
 * - stocker et recuperer sa vitesse d'attaque
 * - stocker et recuperer sa portee d'attaque
 * - stocker et recuperer le montant de son butin
 * - effectuer toutes les actions d'aggissement durant un tour
 */

package byteDefense.model.enemies;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.model.effects.SpecialEffect;
import byteDefense.model.towers.Tower;
import byteDefense.utilities.BFS;

public class Ransomware extends OffensiveEnemy {

	private static final int ATTACK = 10;
	private static final int INITIAL_DEFENSE = 30;
	private static final int ATTACK_RANGE = 3; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final int LOOT = 8;

	public Ransomware(BFS bfs, GameEnvironment gameEnv) {
		super(INITIAL_DEFENSE, bfs, gameEnv);
	}

	public int getAttack() {
		return ATTACK;
	}

	public int getAttackRange() {
		return ATTACK_RANGE;
	}

	public int getLoot() {
		return LOOT;
	}

	public void attack() {
		super.attackTower();
	}
	
	public void frozeTower(Tower tower) {
		tower.changeFrozen();
	}
	
	public void useSpecialEffect(LivingObject livingObject) {
		SpecialEffect specialEffect = super.getSpecialEffect();
		
		if (!specialEffect.getActivated() && !((Tower)livingObject).getFrozen()) {
			this.frozeTower(((Tower)livingObject));
			specialEffect.changeActivated();
			livingObject.inflictEffect(specialEffect);
		}
	}
}
