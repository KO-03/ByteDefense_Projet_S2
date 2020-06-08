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
import byteDefense.utilities.BFS;

public class Spyware extends Enemy {

	private static final float MOVE_SPEED_RATE = 2; // taux d'augmentation de la vitesse de deplacement en pourcentage
	private static final int ATTACK = 30;
	private static final int DEFENSE = 10;
	private static final int ATTACK_SPEED = 0; // vitesse d'attaque en nombre de tour
	private static final int ATTACK_RANGE = 0; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final int LOOT = 50;


	public Spyware(BFS bfsMap, GameEnvironment gameEnv) {
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

	public float getMoveSpeed() {
		return MOVE_SPEED_RATE;
	}

	public void move() {
		if (!super.isArrived())
			super.moveEnnemy();
	}
}


