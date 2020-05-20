/*
 * Spyware.java
 * Cette classe represente un objet Spyware.
 */

package byteDefense.model.ennemies;

public class Spyware extends Ennemy {

	private static final int MOVE_SPEED = 2;
	private static final float ATTACK = 0;
	private static final int DEFENSE = 0;
	private static final int ATTACK_SPEED = 0;
	private static final int ATTACK_RANGE = 0;
	private static final int LOOT = 0;
	
	public Spyware() {
		super(0, 0, 5);
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

	public float getMoveSpeed() {
		return MOVE_SPEED;
	}
	
	public void act() {
		this.moveRandomlyEnnemy(MOVE_SPEED);
	}
}
