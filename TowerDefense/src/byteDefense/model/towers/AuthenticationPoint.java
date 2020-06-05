/*
 * AuthenticationPoint.java
 * Cette classe represente un objet AuthenticationPoint, ses responsabilites sont de :
 * - stocker et recuperer son attaque
 * - stocker et recuperer sa defense
 * - stocker et recuperer sa vitesse d'attaque
 * - stocker et recuperer sa portee d'attaque
 * - stocker et recuperer le montant de son cout
 * - stocker le taux reduction de vitesse de ses tirs 
 * - effectuer toutes les actions d'aggissement durant un tour
 */

package byteDefense.model.towers;

import byteDefense.model.GameEnvironment;

public class AuthenticationPoint extends Tower {

	private static final float SPEED_REDUCTION_RATE = 0.50F;
	private static final int ATTACK = 10;
	private static final int DEFENSE = 30;
	private static final int ATTACK_SPEED = 2;
	private static final int ATTACK_RANGE = 3;
	private static final int COST = 20;

	public AuthenticationPoint(int x, int y, GameEnvironment gameEnv) {
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
