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

	private static final int ATTACK = 1;
	private static final int DEFENSE = 20;
	private static final int ATTACK_SPEED = 1; // correspond a la vitesse d'attaque en nombre de tour
	private static final int ATTACK_RANGE = 3; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final int LOOT = 40;

	public Ransomware(BFS bfsMap, GameEnvironment gameEnv) {
		super(bfsMap, gameEnv);
	}

	public int getAttack() {
		return ATTACK;
	}

	public int getDefense() {
		return DEFENSE;
	}

	public int getAttackSpeed() {
		return ATTACK_SPEED;
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
		
		if (!specialEffect.getActivated() && ((Tower)livingObject).getFrozen()) {
			this.frozeTower(((Tower)livingObject));
			specialEffect.changeActivated();
		}
		livingObject.inflictEffect(specialEffect);
	}
}
