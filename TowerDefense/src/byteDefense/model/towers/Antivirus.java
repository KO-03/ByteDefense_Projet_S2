/*
 * Antivirus.java
 * Cette classe represente un objet Antivirus, ses responsabilites sont de :
 * - stocker et recuperer son attaque
 * - stocker et recuperer sa defense
 * - stocker et recuperer sa vitesse d'attaque
 * - stocker et recuperer sa portee d'attaque
 * - stocker et recuperer le montant de son cout
 * - effectuer toutes les actions d'aggissement durant un tour
 */

package byteDefense.model.towers;

import byteDefense.model.GameEnvironment;

public class Antivirus extends Tower {

	private static final int ATTACK = 35;
	private static final int DEFENSE = 30;
	private static final int ATTACK_SPEED = 2;
	private static final int ATTACK_RANGE = 2;
	private static final int COST = 15;

	public Antivirus(int x, int y, GameEnvironment gameEnv) {
		super(x, y, gameEnv);
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

	public int getCost() {
		return COST;
	}

	public void attack() {
		super.attackEnemy();
	}
}
