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
import byteDefense.model.towers.Tower;
import byteDefense.utilities.BFS;

public class Spyware extends OffensiveEnemy {

	private static final int INITIAL_ATTACK = 15;
	private static final int INITIAL_DEFENSE = 25;
	public static final int INITIAL_ATTACK_RANGE = 2; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final int LOOT = 10;

	public int attack;
	public int attackRange;
	
	public Spyware(BFS bfsMap, GameEnvironment gameEnv) {
		super(INITIAL_DEFENSE, bfsMap, gameEnv);
		this.attack = INITIAL_ATTACK;
		this.attackRange = INITIAL_ATTACK_RANGE;
	}

	public int getAttack() {
		return attack;
	}

	public int getAttackRange() {
		return attackRange;
	}
	
	public int getLoot() {
		return LOOT;
	}

	private void setAttack(int attack) {
		this.attack = attack;
	}

	public void resetAttack() {
		this.setAttack(INITIAL_ATTACK);
	}
	
	public void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}

	public void resetAttackRange() {
		this.setAttackRange(INITIAL_ATTACK_RANGE);
	}
	
	public void stealStats(Tower tower) {
		if (tower.getAttack() > this.attack)
			this.setAttack(tower.getAttack());
		if (tower.getAttackRange() > this.attackRange)
			this.setAttackRange(tower.getAttackRange());
	}
	
	public void useSpecialEffect(LivingObject livingObject) {
		SpecialEffect specialEffect = super.getSpecialEffect();
		
		if (!specialEffect.getActivated()) {
			this.stealStats((Tower)livingObject);
			specialEffect.changeActivated();
			super.inflictEffect(specialEffect);
		}
	}
}


