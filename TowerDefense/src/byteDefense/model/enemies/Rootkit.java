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

	public static final float INCREASE_ATTACK_RATE = 0.90F; // taux d'aumgmentation d'attaque en pourcentage
	public static final int INTIAL_ATTACK = 2;
	private static final int DEFENSE = 20;
	private static final int ATTACK_SPEED = 3; // vitesse d'attaque en nombre de tour
	private static final int ATTACK_RANGE = 4; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final int LOOT = 10;
	
	private int attack;

	public Rootkit(BFS bfsMap, GameEnvironment gameEnv) {
		super(bfsMap, gameEnv);
		this.attack = INTIAL_ATTACK; 
	}
	
	public int getAttack() {
		return this.attack;
	}

	public void setAttack(int newAttack) {
		this.attack = newAttack;
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
	
	private void increaseAttack(LivingObject livingObject) {
		this.setAttack((this.attack + (int)(livingObject.getDefense() * INCREASE_ATTACK_RATE)));
	}
	
	public void useSpecialEffect(LivingObject livingObject) {
		SpecialEffect specialEffect = super.getSpecialEffect();
		
		if (!specialEffect.getActivated()) {
			this.increaseAttack(livingObject);
			specialEffect.changeActivated();	
		}
		super.inflictEffect(specialEffect);
	}
}
