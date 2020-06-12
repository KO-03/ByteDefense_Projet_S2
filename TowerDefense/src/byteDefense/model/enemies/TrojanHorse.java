/*
 * TrojanHorse.java
 * Cette classe represente un objet TrojanHorse, ses responsabilites sont de :
 * - stocker et recuperer son attaque (points de vie de l'ordinateur)
 * - stocker et recuperer sa defense de base
 * - stocker et recuperer sa portee d'attaque de base
 * - stocker et recuperer le montant de son butin
 */

package byteDefense.model.enemies;

import byteDefense.model.GameEnvironment;
import byteDefense.model.GameMaster;
import byteDefense.model.LivingObject;
import byteDefense.utilities.BFS;

public class TrojanHorse extends Enemy {

	private static final int INITIAL_DEFENSE = 800;
	public static final int INITIAL_ATTACK_RANGE = 0;
	private static final int LOOT = 60;

	private int attack;

	public TrojanHorse(BFS bfs, GameEnvironment gameEnv) {
		super(INITIAL_DEFENSE, bfs, gameEnv);
		attack = GameMaster.getLaptopHp();
	}

	public int getAttack() {
		return this.attack;
	}

	public int getAttackRange() {
		return INITIAL_ATTACK_RANGE;
	}
	
	public int getLoot() {
		return LOOT;
	}
	
	public void useSpecialEffect(LivingObject livingObject) {

	}
}
