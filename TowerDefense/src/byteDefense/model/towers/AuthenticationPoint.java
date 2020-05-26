/*
 * AuthenticationPoint.java
 * Cette classe represente un objet AuthenticationPoint.
 */

package byteDefense.model.towers;

public class AuthenticationPoint extends Tower {
	
	private static final float ATTACK = 0;
	private static final int DEFENSE = 0;
	private static final int ATTACK_SPEED = 0;
	private static final int ATTACK_RANGE = 0;
	private static final int COST = 0;

	public AuthenticationPoint(int x, int y) {
		super(x, y, 9);
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

	public int getCost() {
		return COST;
	}
	
	public void act() {
		
	}
}
