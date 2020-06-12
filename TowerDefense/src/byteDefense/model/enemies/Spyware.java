/*
 * Spyware.java
 * Cette classe represente un objet Spyware, ses responsabilites sont de :
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

public class Spyware extends Enemy {

	private static final float INCREASE_ATTACK_RANGE_RATE = 1.10F; // taux d'augmentation de la vitesse de deplacement en pourcentage
	private static final int ATTACK = 30;
	private static final int DEFENSE = 10;
	private static final int ATTACK_SPEED = 0; // vitesse d'attaque en nombre de tour
	public static final int INITIAL_ATTACK_RANGE = 0; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final int LOOT = 50;

	private int attackRange;
	
	public Spyware(BFS bfsMap, GameEnvironment gameEnv) {
		super(bfsMap, gameEnv);
		this.attackRange = INITIAL_ATTACK_RANGE;
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
		return attackRange;
	}

	public void setAttackRange(int newAttackRange) {
		this.attackRange = newAttackRange;
	}
	
	public void resetAttackRange() {
		this.setAttackRange(INITIAL_ATTACK_RANGE);
	}
	
	public int getLoot() {
		return LOOT;
	}
	
	public void increaseAttackRange(LivingObject livingObject) {
		this.setAttackRange((int)(this.getAttackSpeed() * INCREASE_ATTACK_RANGE_RATE));
	}
	
	public void useSpecialEffect(LivingObject livingObject) {
		SpecialEffect specialEffect = super.getSpecialEffect();
		
		if (!specialEffect.getActivated()) {
			this.increaseAttackRange(livingObject);
			specialEffect.changeActivated();	
		}
		super.inflictEffect(specialEffect);
	}
}


