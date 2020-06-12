/*
 * Adware.java
 * Cette classe represente un objet Adware, ses responsabilites sont de :
 * - stocker et recuperer son attaque de base
 * - stocker et recuperer sa defense de base
 * - stocker et recuperer sa portee d'attaque de base
 * - stocker et recuperer le montant de son butin
 * - attaquer une tourelle
 */

package byteDefense.model.enemies;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.utilities.BFS;

public class Adware extends Enemy {

	private static final int INITIAL_ATTACK = 10;
	public static final int INITIAL_ATTACK_RANGE = 0;
	private static final int INITIAL_DEFENSE = 5;
	private static final int LOOT = 1;
	
	public Adware(BFS bfs, GameEnvironment gameEnv) {
		super(INITIAL_DEFENSE, bfs, gameEnv);
	}
	
	public int getAttack() {
		return INITIAL_ATTACK;
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
