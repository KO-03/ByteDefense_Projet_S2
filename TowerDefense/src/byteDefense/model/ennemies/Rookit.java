/*
 * Rookit.java
 * Cette classe represente un objet Rookit, ses responsabilites sont de :
 * - stocker et recuperer son attaque
 * - stocker et recuperer sa defense
 * - stocker et recuperer sa vitesse d'attaque
 * - stocker et recuperer sa portee d'attaque
 * - stocker et recuperer le montant de son butin
 * - stocker et gerer son taux d'augmentation d'attaque par tour
 * - effectuer toutes les actions d'aggissement durant un tour
 */

package byteDefense.model.ennemies;

import byteDefense.utilities.BFS;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;

public class Rookit extends OffensiveEnnemy {
	
	public static final float INCREASE_ATTACK_RATE = 1.20F;
	private static FloatProperty attack = new SimpleFloatProperty(1);
	private static final int DEFENSE = 0;
	private static final int ATTACK_SPEED = 0;
	private static final int ATTACK_RANGE = 0;
	private static final int LOOT = 0;
	
	public Rookit(BFS bfsMap) {
		super(624, 432, 1, bfsMap);
	}
	
	public float getAttack() {
		return attack.getValue();
	}
	
	public FloatProperty getAttackProperty() {
		return attack;
	}
	
	private void increaseAttack() {
		attack.setValue(this.getAttack() * INCREASE_ATTACK_RATE);
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
		this.increaseAttack();
		super.moveEnnemy();
	}
}
