/*
 * Bot.java
 * Cette classe represente un objet Bot, ses responsabilites sont de :
 * - stocker et recuperer son attaque de base
 * - stocker et recuperer sa defense de base
 * - stocker sa portee d'attaque de base
 * - stocker et recuperer sa portee d'attaque 
 * - stocker et recuperer le montant de son butin
 * - augmenter sa portee d'attaque
 * - utiliser son effet special (augmenter sa portee d'attaque)
 */

package byteDefense.model.enemies;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.model.effects.SpecialEffect;
import byteDefense.utilities.BFS;

public class Bot extends OffensiveEnemy {

	private static final float INCREASE_ATTACK_RANGE_RATE = 2F; // taux d'augmentation de la vitesse de deplacement en pourcentage
	private static final int INITIAL_ATTACK = 15;
	private static final int INITIAL_DEFENSE = 15;
	public static final int INITIAL_ATTACK_RANGE = 2; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final int LOOT = 6;
	
	private int attackRange;
	
	public Bot(BFS bfs, GameEnvironment gameEnv) {
		super(INITIAL_DEFENSE, bfs, gameEnv);
		this.attackRange = INITIAL_ATTACK_RANGE;
	}

	public int getAttack() {
		return INITIAL_ATTACK;
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
