/*
 * Rookit.java
 * Cette classe représente un objet Rookit.
 */

package byteDefense.model.ennemies;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Rookit extends Ennemy {
	
	private static IntegerProperty attack = new SimpleIntegerProperty(0);
	private static final int DEFENSE = 0;
	private static final int ATTACK_SPEED = 0;
	private static final int ATTACK_RANGE = 0;
	private static final int LOOT = 0;
	
	public Rookit() {
		super(0, 0);
	}
	
	public int getAttack() {
		return this.attack.getValue();
	}
	
	public IntegerProperty getAttackProperty() {
		return this.attack;
	}
	
	public void incrementAttack() {
		this.attack.setValue(this.getAttack() + 1);
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
