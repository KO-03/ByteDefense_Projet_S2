/*
 * SudVPN.java
 * Cette classe represente un objet SudVPN, ses responsabilites sont de :
 * - stocker et recuperer son attaque
 * - stocker et recuperer sa defense
 * - stocker et recuperer sa vitesse d'attaque
 * - stocker et recuperer sa portee d'attaque
 * - stocker et recuperer le montant de son cout
 * - effectuer toutes les actions d'aggissement durant un tour
 */

package byteDefense.model.towers;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;

public class SudVPN extends Tower {

	private static final int ATTACK = 40;
	private static final int DEFENSE = 25;
	private static final int ATTACK_SPEED = 2; // vitesse d'attaque en nombre de tour
	private static final int ATTACK_RANGE = 2; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final int COST = 40;

	public SudVPN(int x, int y, GameEnvironment gameEnv) {
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
	
	public void useSpecialEffect(LivingObject livingObject) {
		
	}
}
