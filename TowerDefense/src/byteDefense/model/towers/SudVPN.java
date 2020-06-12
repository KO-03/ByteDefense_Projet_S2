/*
 * SudVPN.java
 * Cette classe represente un objet SudVPN, ses responsabilites sont de :
 * - stocker son attaque de base
 * - stocker et recuperer son attaque
 * - stocker et recuperer sa defense de base
 * - stocker et recuperer sa portee d'attaque de base
 * - stocker et recuperer le montant de son cout
 * - attaquer un ennemi
 * - augmenter ses degats
 * - utiliser son effet special (augmentation de ses degats)
 */

package byteDefense.model.towers;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.model.effects.SpecialEffect;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SudVPN extends Tower {

	private static final float INCREASING_ATTACK_RATE = 3.50F; // taux d'aumgmentation d'attaque en pourcentage
	private static final int INITIAL_ATTACK = 15;
	private static final int INITIAL_DEFENSE = 30;
	private static final int ATTACK_RANGE = 2; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final IntegerProperty COST_PROPERTY = new SimpleIntegerProperty(65);

	private int attack;
	
	public SudVPN(int x, int y, GameEnvironment gameEnv) {
		super(x, y, INITIAL_DEFENSE, gameEnv);
		this.attack = INITIAL_ATTACK;
	}

	public int getAttack() {
		return this.attack;
	}

	private void setAttack(int newAttack) {
		this.attack = newAttack;
	}
	
	public void resetAttack() {
		this.setAttack(INITIAL_ATTACK);
	}
	
	public int getAttackRange() {
		return ATTACK_RANGE;
	}
	
	public static final IntegerProperty getCostProperty() {
		return COST_PROPERTY;
	}

	public final int getCost() {
		return COST_PROPERTY.getValue();
	}

	public void increaseAttack() {
		this.setAttack((int)(this.attack * INCREASING_ATTACK_RATE));
	}
	
	public void useSpecialEffect(LivingObject livingObject) {
		SpecialEffect specialEffect = super.getSpecialEffect();
		
		if (!specialEffect.getActivated()) {
			this.increaseAttack();
			specialEffect.changeActivated();
			super.inflictEffect(specialEffect);
		}
	}
}
