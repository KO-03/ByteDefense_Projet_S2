/*
 * Rookit.java
 * Cette classe represente un objet Rookit, ses responsabilites sont de :
 * - stocker, recuperer et incrementer son attaque
 * - stocker et recuperer sa defense
 * - stocker et recuperer sa vitesse d'attaque
 * - stocker et recuperer sa portee d'attaque
 * - stocker et recuperer le montant de son butin
 * - stocker taux d'augmentation d'attaque par tour
 * - effectuer toutes les actions d'aggissement durant un tour
 */

package byteDefense.model.enemies;

import byteDefense.model.GameEnvironment;
import byteDefense.utilities.BFS;

public class Rookit extends OffensiveEnnemy {

	public static final float INCREASE_ATTACK_RATE = 1.10F;
	private static final int DEFENSE = 20;
	private static final int ATTACK_RANGE = 4;
	private static final int ATTACK_SPEED = 3;
	private static final int LOOT = 10;
	
	private int attack;

	public Rookit(BFS bfsMap, GameEnvironment gameEnv) {
		super(bfsMap, gameEnv);
		this.attack = 10; 
	}
	
	public int getAttack() {
		return this.attack;
	}

	public void setAttack(int newAttack) {
		this.attack = newAttack;
	}
	
	private void increaseAttack() {
		this.setAttack((int)(this.attack * INCREASE_ATTACK_RATE));
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
		this.increaseAttack();
		super.attackTower();
	}
}
