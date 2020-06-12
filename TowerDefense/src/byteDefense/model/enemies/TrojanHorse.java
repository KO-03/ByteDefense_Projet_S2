/*
 * TrojanHorse.java
 * Cette classe represente un objet TrojanHorse, ses responsabilites sont de :
 * - stocker et recuperer son attaque
 * - stocker et recuperer sa defense
 * - stocker et recuperer sa vitesse d'attaque
 * - stocker et recuperer sa portee d'attaque
 * - stocker et recuperer le montant de son butin
 * - effectuer toutes les actions d'aggissement durant un tour
 */

package byteDefense.model.enemies;

import byteDefense.model.GameEnvironment;
import byteDefense.model.GameMaster;
import byteDefense.model.LivingObject;
import byteDefense.utilities.BFS;

public class TrojanHorse extends Enemy {

	private static final int INITIAL_DEFENSE = 10000;
	private static final int ATTACK_RANGE = 0; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final int LOOT = 80;

	private int attack;

	public TrojanHorse(BFS bfs, GameEnvironment gameEnv) {
		super(INITIAL_DEFENSE, bfs, gameEnv);
		attack = GameMaster.getLaptopHp();
	}

	public int getAttack() {
		return this.attack;
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
