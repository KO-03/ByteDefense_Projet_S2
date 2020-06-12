/*
 * AuthenticationPoint.java
 * Cette classe represente un objet AuthenticationPoint, ses responsabilites sont de :
 * - stocker son attaque de base
 * - stocker et recuperer son attaque
 * - stocker et recuperer sa defense de base
 * - stocker et recuperer sa portee d'attaque de base
 * - stocker et recuperer le montant de son cout
 * - attaquer un ennemi
 * - faire un lien avec une cible en ajoutant ses stats aux siennes
 * - utiliser son effet special (creer un lien)
 */

package byteDefense.model.towers;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.model.effects.SpecialEffect;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class AuthenticationPoint extends Tower {

	private static final int INITIAL_ATTACK = 10;
	private static final int INITIAL_DEFENSE = 30;
	private static final int INITIAL_ATTACK_RANGE = 4; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final IntegerProperty COST_PROPERTY = new SimpleIntegerProperty(30);

	public int attack;
	
	public AuthenticationPoint(int x, int y, GameEnvironment gameEnv) {
		super(x, y, INITIAL_DEFENSE, gameEnv);
		this.attack = INITIAL_ATTACK;
	}

	public int getAttack() {
		return attack;
	}

	public int getAttackRange() {
		return INITIAL_ATTACK_RANGE;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public void resetAttack() {
		this.setAttack(INITIAL_ATTACK);
	}
	
	public static final IntegerProperty getCostProperty() {
		return COST_PROPERTY;
	}

	public final int getCost() {
		return COST_PROPERTY.getValue();
	}
	
	public void authenticationLink(LivingObject livingObject) {
		this.setAttack(livingObject.getAttack() + this.attack);
	}
	
	public void useSpecialEffect(LivingObject livingObject) {
		SpecialEffect specialEffect = super.getSpecialEffect();
		
		if (!specialEffect.getActivated()) {
			this.authenticationLink(livingObject);
			specialEffect.changeActivated();
			super.inflictEffect(specialEffect);
		}
	}
}
