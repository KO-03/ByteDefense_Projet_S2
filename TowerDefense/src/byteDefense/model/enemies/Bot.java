/*
 * Bot.java
 * Cette classe represente un objet Bot, ses responsabilites sont de :
 * - stocker et recuperer son attaque
 * - stocker et recuperer sa defense
 * - stocker et recuperer sa vitesse d'attaque
 * - stocker et recuperer sa portee d'attaque
 * - stocker et recuperer le montant de son butin
 * - effectuer toutes les actions d'aggissement durant un tour
 */

package byteDefense.model.enemies;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.model.effects.SpecialEffect;
import byteDefense.utilities.BFS;

public class Bot extends OffensiveEnemy {

	private static final float INCREASE_ATTACK_RANGE_RATE = 1.90F; // taux d'augmentation de la vitesse de deplacement en pourcentage
	private static final int ATTACK = 10;
	private static final int INITIAL_DEFENSE = 10;
	public static final int INITIAL_ATTACK_RANGE = 2; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final int LOOT = 30;
	
	private int attackRange;
	
	public Bot(BFS bfs, GameEnvironment gameEnv) {
		super(INITIAL_DEFENSE, bfs, gameEnv);
		this.attackRange = INITIAL_ATTACK_RANGE;
	}

	public int getAttack() {
		return ATTACK;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public int getLoot() {
		return LOOT;
	}
	
	public void setAttackRange(int newAttackRange) {
		this.attackRange = newAttackRange;
	}
	
	public void resetAttackRange() {
		this.setAttackRange(INITIAL_ATTACK_RANGE);
	}
	
	public void sniperTarget(LivingObject livingObject) {
		this.setAttackRange((int)(this.getAttackRange() * INCREASE_ATTACK_RANGE_RATE));
	}
	
	public void useSpecialEffect(LivingObject livingObject) {
		SpecialEffect specialEffect = super.getSpecialEffect();
		
		if (!specialEffect.getActivated()) {
			this.sniperTarget(livingObject);
			specialEffect.changeActivated();
			super.inflictEffect(specialEffect);
		}
	}
}
