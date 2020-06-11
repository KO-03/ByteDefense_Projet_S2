/*
 * AdCube.java
 * Cette classe represente un objet AdCube, ses responsabilites sont de :
 * - stocker et recuperer son attaque
 * - stocker et recuperer sa defense
 * - stocker et recuperer sa vitesse d'attaque
 * - stocker et recuperer sa portee d'attaque
 * - stocker et recuperer le montant de son cout
 * - effectuer toutes les actions d'aggissement durant un tour
 */

package byteDefense.model.towers;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class AdCube extends Tower {

	private static final int ATTACK = 10;
	private static final int DEFENSE = 20;
	private static final int ATTACK_SPEED = 4; // correspond a la vitesse d'attaque en nombre de tour
	private static final int ATTACK_RANGE = 3; // correspond a la portee d'attaque en nombre de tuile du plateau de jeu
	private static final IntegerProperty COST_PROPERTY = new SimpleIntegerProperty(5);

	public AdCube(int x, int y, GameEnvironment gameEnv) {
		super(x, y, gameEnv);
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
	
	public static final IntegerProperty getCostProperty() {
		return COST_PROPERTY;
	}

	public final int getCost() {
		return COST_PROPERTY.getValue();
	}
	
	public void useSpecialEffect(LivingObject livingObject) {
		
	}
}
