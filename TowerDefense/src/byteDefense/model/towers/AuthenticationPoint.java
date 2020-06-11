/*
 * AuthenticationPoint.java
 * Cette classe represente un objet AuthenticationPoint, ses responsabilites sont de :
 * - stocker et recuperer son attaque
 * - stocker et recuperer sa defense
 * - stocker et recuperer sa vitesse d'attaque
 * - stocker et recuperer sa portee d'attaque
 * - stocker et recuperer le montant de son cout
 * - stocker le taux reduction de vitesse de ses tirs 
 * - effectuer toutes les actions d'aggissement durant un tour
 */

package byteDefense.model.towers;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class AuthenticationPoint extends Tower {

	private static final float SPEED_REDUCTION_RATE = 0.50F; // taux de reduction de vitesse de deplacement des ennemis en pourcentage
	private static final int ATTACK = 10;
	private static final int DEFENSE = 30;
	private static final int ATTACK_SPEED = 2; // vitesse d'attaque en nombre de tour
	private static final int ATTACK_RANGE = 3; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final IntegerProperty COST_PROPERTY = new SimpleIntegerProperty(20);

	public AuthenticationPoint(int x, int y, GameEnvironment gameEnv) {
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

	public void attack() {
		super.attackEnemy();
	}
	
	public void useSpecialEffect(LivingObject livingObject) {
		
	}
}
