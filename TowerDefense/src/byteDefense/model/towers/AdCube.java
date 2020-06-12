/*
 * AdCube.java
 * Cette classe represente un objet AdCube, ses responsabilites sont de :
 * - stocker et recuperer son attaque de base
 * - stocker et recuperer sa defense de base
 * - stocker et recuperer sa portee d'attaque de base
 * - stocker et recuperer le montant de son cout
 * - attaquer un ennemi
 */

package byteDefense.model.towers;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class AdCube extends Tower {

	private static final int INITIAL_ATTACK = 8;
	private static final int INITIAL_DEFENSE = 10;
	private static final int INITIAL_ATTACK_RANGE = 2; // correspond a la portee d'attaque en nombre de tuile du plateau de jeu
	private static final IntegerProperty COST_PROPERTY = new SimpleIntegerProperty(5);

	public AdCube(int x, int y, GameEnvironment gameEnv) {
		super(x, y, INITIAL_DEFENSE, gameEnv);
	}

	public int getAttack() {
		return INITIAL_ATTACK;
	}

	public int getAttackRange() {
		return INITIAL_ATTACK_RANGE;
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
