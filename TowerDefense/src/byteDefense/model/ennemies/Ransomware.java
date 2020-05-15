/*
 * Ransomware.java
 * Cette classe represente un objet Ransomware.
 */

package byteDefense.model.ennemies;

public class Ransomware extends Ennemy {

	private static final int ATTACK = 0;
	private static final int DEFENSE = 0;
	private static final int ATTACK_SPEED = 0;
	private static final int ATTACK_RANGE = 0;
	private static final int LOOT = 0;
	
	public Ransomware() {
		super(0, 0, 3);
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
}
