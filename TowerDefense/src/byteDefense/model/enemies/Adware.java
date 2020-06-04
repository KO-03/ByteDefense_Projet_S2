/*
 * Adware.java
 * Cette classe represente un objet Adware, ses responsabilites sont de :
 * - stocker et recuperer son attaque
 * - stocker et recuperer sa defense
 * - stocker et recuperer sa vitesse d'attaque
 * - stocker et recuperer sa portee d'attaque
 * - stocker et recuperer le montant de son butin
 * - stocker son taux de reproduction
 * - gerer son tour de naissance dans une vague
 * - effectuer toutes les actions d'aggissement durant un tour
 */

package byteDefense.model.enemies;

import byteDefense.model.GameEnvironment;
import byteDefense.utilities.BFS;

public class Adware extends OffensiveEnnemy {

	private static final float REPRODUCTION_RATE = 0;
	private static final int ATTACK = 20;
	private static final int DEFENSE = 10;
	private static final int ATTACK_RANGE = 1;
	private static final int ATTACK_SPEED = 3;
	private static final int LOOT = 20;

	private int bornTurn;

	public Adware(BFS bfsMap, GameEnvironment gameEnv) {
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

	public void act() {
		if (!this.isArrived())
			this.moveEnnemy();
		super.attackTower();
	}
}