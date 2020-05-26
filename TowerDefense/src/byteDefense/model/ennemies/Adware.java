/*
 * Adware.java
 * Cette classe represente un objet Adware
 */

package byteDefense.model.ennemies;

import byteDefense.utilities.BFS;

public class Adware extends OffensiveEnnemy {

	private static final float REPRODUCTION_RATE = 0;
	private static final float ATTACK = 0;
	private static final int DEFENSE = 0;
	private static final int ATTACK_SPEED = 0;
	private static final int ATTACK_RANGE = 0;
	private static final int LOOT = 0;
	
	private int bornRound;
	
	public Adware(BFS bfsMap) {
		super(624, 432, 2, bfsMap);
	}

	public float getAttack() {
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
		this.moveEnnemy();
	}
}
