/*
 * Ransomware.java
 * Cette classe represente un objet Ransomware.
 */

package byteDefense.model.ennemies;

public class Ransomware extends OffensiveEnnemy {

	private static final float ATTACK = 0;
	private static final int DEFENSE = 0;
	private static final int ATTACK_SPEED = 0;
	private static final int ATTACK_RANGE = 0;
	private static final int LOOT = 0;
	
	public Ransomware() {
		super(0, 0, 4);
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
		this.moveRandomlyEnnemy(1);
	}
}
