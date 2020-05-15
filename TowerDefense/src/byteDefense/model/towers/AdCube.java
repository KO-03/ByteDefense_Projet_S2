/*
 * AdCube.java
 * Cette classe represente un objet AdCube.
 */

package byteDefense.model.towers;

public class AdCube extends Tower {
	
	private static final int ATTACK = 0;
	private static final int DEFENSE = 0;
	private static final int ATTACK_SPEED = 0;
	private static final int ATTACK_RANGE = 0;
	private static final int COST = 0;

	public AdCube() {
		super(0, 0);
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
}
