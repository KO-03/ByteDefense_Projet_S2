/*
 * Firewall.java
 * Cette classe represente un objet Firewall, ses responsabilites sont de :
 * - stocker et recuperer son attaque de base
 * - stocker et recuperer sa defense de base
 * - stocker et recuperer sa portee d'attaque de base
 * - stocker et recuperer le montant de son cout
 * - attaquer un ennemi
 * - enflammer un ennemi
 * - utiliser son effet special (enflamment d'un ennemi)
 */

package byteDefense.model.towers;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.model.effects.SpecialEffect;
import byteDefense.model.enemies.Enemy;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Firewall extends Tower {

	private static final int ATTACK = 25;
	private static final int INITIAL_DEFENSE = 15;
	private static final int ATTACK_RANGE = 3; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final IntegerProperty COST_PROPERTY = new SimpleIntegerProperty(45);

	public Firewall(int x, int y, GameEnvironment gameEnv) {
		super(x, y, INITIAL_DEFENSE, gameEnv);
	}

	public int getAttack() {
		return ATTACK;
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
	
	public void igniteEnemy(Enemy enemy) {
		enemy.changeIgnited();
	}
	
	public void useSpecialEffect(LivingObject livingObject) {
		SpecialEffect specialEffect = super.getSpecialEffect();
		
		if (!specialEffect.getActivated() && !((Enemy)livingObject).getIgnited()) {
			this.igniteEnemy(((Enemy)livingObject));
			specialEffect.changeActivated();
			livingObject.inflictEffect(specialEffect);
		}
	}
}
