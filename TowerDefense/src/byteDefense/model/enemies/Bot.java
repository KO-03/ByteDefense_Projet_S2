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
import byteDefense.utilities.BFS;

public class Bot extends Enemy {

	private static final float HEALING_RATE = 1.10F;
	private static final int ATTACK = 10;
	private static final int DEFENSE = 10;
	private static final int ATTACK_SPEED = 0; // vitesse d'attaque en nombre de tour
	private static final int ATTACK_RANGE = 0; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final int LOOT = 30;
	
	public Bot(BFS bfsMap, GameEnvironment gameEnv) {
		super(bfsMap, gameEnv);
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

	public int getLoot() {
		return LOOT;
	}
	
	public void useSpecialEffect(LivingObject livingObject) {
		
	}
}
