/*
 * TrojanHorse.java
 * Cette classe represente un objet TrojanHorse.
 */

package byteDefense.model.ennemies;

import byteDefense.utilities.BFS;

public class TrojanHorse extends Ennemy {
	
	private static final float ATTACK = 0;
	private static final int DEFENSE = 0;
	private static final int ATTACK_SPEED = 0;
	private static final int ATTACK_RANGE = 0;
	private static final int LOOT = 0;
	
	public TrojanHorse(BFS bfsMap) {
		super(624, 432, 6, bfsMap);
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
