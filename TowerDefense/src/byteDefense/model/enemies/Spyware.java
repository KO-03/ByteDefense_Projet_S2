/*
 * Spyware.java
 * Cette classe represente un objet Spyware, ses responsabilites sont de :
 * - stocker et recuperer son attaque
 * - stocker et recuperer sa defense
 * - stocker et recuperer sa vitesse d'attaque
 * - stocker et recuperer sa portee d'attaque
 * - stocker et recuperer le montant de son butin
 * - effectuer toutes les actions d'aggissement durant un tour
 */

package byteDefense.model.enemies;

import byteDefense.model.GameEnvironment;
import byteDefense.utilities.BFS;

public class Spyware extends Ennemy {

	private static final int MOVE_SPEED = 2;
	private static final int ATTACK = 30;
	private static final int DEFENSE = 10;
	private static final int ATTACK_RANGE = 0;
	private static final int ATTACK_SPEED = 0;
	private static final int LOOT = 50;


	public Spyware(BFS bfsMap, GameEnvironment gameEnv) {
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

	public float getMoveSpeed() {
		return MOVE_SPEED;
	}

	public void act() {
		if (!this.isArrived())
			this.moveEnnemy();
	}
}

