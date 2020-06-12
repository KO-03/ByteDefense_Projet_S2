/*
 * AdCube.java
 * Cette classe represente un objet Adware, ses responsabilites sont de :
 * - stocker et recuperer son attaque de base
 * - stocker et recuperer sa defense de base
 * - stocker et recuperer sa portee d'attaque de base
 * - stocker et recuperer le montant de son cout
 * - attaquer un ennemi
 * - utiliser son effet special (attaquer deux ennemis en meme temps)
 */

package byteDefense.model.towers;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.model.enemies.Enemy;
import byteDefense.utilities.ShootUtilities;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Antivirus extends Tower {

	private static final int ATTACK = 15;
	private static final int INITIAL_DEFENSE = 0;
	private static final int ATTACK_RANGE = 3; // correspond a la portee d'attaque en nombre de tuile du plateau de jeu
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
		if (target != null && super.isAlive())
			ShootUtilities.shoot(this, target, gameEnv);
	}
}
