/*
 * Antivirus.java
 * Cette classe represente un objet Antivirus, ses responsabilites sont de :
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
import byteDefense.model.enemies.Enemy;
import byteDefense.utilities.ShootUtilities;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Antivirus extends Tower {

	private static final int ATTACK = 35;
	private static final int INITIAL_DEFENSE = 30;
	private static final int ATTACK_RANGE = 2; // correspond a la portee d'attaque en nombre de tuile du plateau de jeu
	private static final IntegerProperty COST_PROPERTY = new SimpleIntegerProperty(15);

	public Antivirus(int x, int y, GameEnvironment gameEnv) {
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
	
	public void useSpecialEffect(LivingObject livingObject) {
		GameEnvironment gameEnv = super.getGameEnvironment();
		Enemy target = null, enemy;
		int i = 0;
		
		while (i < gameEnv.getEnemies().size() && target == null) {
			enemy = gameEnv.getEnemies().get(i);
			if (enemy != livingObject)
				target = (Enemy) ShootUtilities.checkTargetPosition(enemy, this);
			i++;
		}
		if (target != null)
			ShootUtilities.shoot(this, target, gameEnv);
	}
}
