/*
 * Rootkit.java
 * Cette classe represente un objet Rookit, ses responsabilites sont de :
 * - stocker, recuperer et incrementer son attaque
 * - stocker et recuperer sa defense
 * - stocker et recuperer sa vitesse d'attaque
 * - stocker et recuperer sa portee d'attaque
 * - stocker et recuperer le montant de son butin
 * - stocker taux d'augmentation d'attaque par tour
 * - effectuer toutes les actions d'aggissement durant un tour
 */

package byteDefense.model.enemies;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.model.effects.SpecialEffect;
import byteDefense.utilities.BFS;

public class Rootkit extends OffensiveEnemy {

	private static final float PIERCING_DEFENSE_RATE = 1.10F; 
	private static final int INITIAL_ATTACK = 2;
	private static final int INITIAL_DEFENSE = 20;
	private static final int ATTACK_RANGE = 4; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final int LOOT = 10;
	
	private int attack;

	public Rootkit(BFS bfsMap, GameEnvironment gameEnv) {
		super(INITIAL_DEFENSE, bfsMap, gameEnv);
		this.attack = INITIAL_ATTACK; 
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
