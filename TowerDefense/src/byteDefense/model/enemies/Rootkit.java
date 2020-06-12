/*
 * Rootkit.java
 * Cette classe represente un objet Rookit, ses responsabilites sont de :
 * - stocker son attaque de base
 * - stocker et recuperer sa portee d'attaque de base
 * - stocker et recuperer sa defense de base
 * - stocker et recuperer son attaque
 * - stocker et recuperer le montant de son butin 
 * - stocker taux d'augmentation d'attaque par tour
 * - attaquer une tourelle
 * - percer la defense d'un tourelle
 * - utiliser son effet special (perce de defense)
 */

package byteDefense.model.enemies;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.model.effects.SpecialEffect;
import byteDefense.utilities.BFS;

public class Rootkit extends OffensiveEnemy {

	private static final float PIERCING_DEFENSE_RATE = 1.10F; 
	private static final int INITIAL_ATTACK = 15;
	private static final int INITIAL_DEFENSE = 5;
	private static final int INITIAL_ATTACK_RANGE = 2; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final int LOOT = 4;
	
	private int attack;

	public Rootkit(BFS bfsMap, GameEnvironment gameEnv) {
		super(INITIAL_DEFENSE, bfsMap, gameEnv);
		this.attack = INITIAL_ATTACK; 
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
	
	public void setAttack(int newAttack) {
		this.attack = newAttack;
	}

	public void resetAttack() {
		this.setAttack(INITIAL_ATTACK);
	}
	
	public void attack() {
		super.attackTower();
	}
	
	private void piercingDefense(LivingObject livingObject) {
		this.setAttack((int)(this.attack + livingObject.getDefense() * PIERCING_DEFENSE_RATE));
	}
	
	public void useSpecialEffect(LivingObject livingObject) {
		SpecialEffect specialEffect = super.getSpecialEffect();
		
		if (!specialEffect.getActivated()) {
			this.piercingDefense(livingObject);
			specialEffect.changeActivated();
			super.inflictEffect(specialEffect);
		}
	}
}
