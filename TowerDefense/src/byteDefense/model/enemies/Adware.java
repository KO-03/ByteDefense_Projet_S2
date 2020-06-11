/*
 * Adware.java
 * Cette classe represente un objet Adware, ses responsabilites sont de :
 * - stocker et recuperer son attaque
 * - stocker et recuperer sa defense
 * - stocker et recuperer sa vitesse d'attaque
 * - stocker et recuperer sa portee d'attaque
 * - stocker et recuperer le montant de son butin
 * - stocker son taux de reproduction
 * - gerer son tour de naissance dans une vague
 * - effectuer toutes les actions d'aggissement durant un tour
 */

package byteDefense.model.enemies;

import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.utilities.BFS;

public class Adware extends OffensiveEnemy {

	private static final float REPRODUCTION_RATE = 2; // taux de reproduction en pourcentage
	private static final int ATTACK = 20;
	private static final int DEFENSE = 10;
	private static final int ATTACK_SPEED = 3; // vitesse d'attaque en nombre de tour
	private static final int ATTACK_RANGE = 1; // portee d'attaque en nombre de tuile du plateau de jeu
	private static final int LOOT = 20;
	
	private BFS bfs;
	
	public Adware(BFS bfs, GameEnvironment gameEnv) {
		super(bfs, gameEnv);
		this.bfs = bfs;
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

	public void move() {
		if (!super.isArrived())
			super.moveEnnemy();
	}

	//permet de savoir si une action probabiliste se réalise 
	public boolean reussitProba(float pourcent) {
		return (Math.random() <= pourcent / 100);
	}
	
	public void breed(BFS bfs, GameEnvironment gameEnv) {
		gameEnv.addEnemy(new Adware(bfs, gameEnv));
	}
	
	public void useSpecialEffect(LivingObject livingObject) {
		/*SpecialEffect specialEffect = super.getSpecialEffect();
		
		if (!specialEffect.getActivated() && reussitProba(REPRODUCTION_RATE)) {
			this.breed(bfs, super.getGameEnvironment());
			specialEffect.changeActivated();
		}
		this.inflictEffect(specialEffect);*/
	}
}
