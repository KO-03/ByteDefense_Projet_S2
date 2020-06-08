/*
 * LivingObject.java
 * Cette classe parent represente un objet "vivant" du jeu (un ennemi ou une tourelle), ses responsabilites sont de :
 * - stocker, recuperer et decrementer ses points de vie 
 * - stocker et recuperer son environnement de jeu
 * - verifier s'il est mort ou non
 * - recuperer ses caracteristiques (points d'attaque, points de defense, vitesse d'attaque, portee d'attaque)
 */

package byteDefense.model;

public abstract class LivingObject extends GameObject {

	private int hp;
	private GameEnvironment gameEnv;

	public LivingObject(int x, int y, int hp, GameEnvironment gameEnv) {
		super(x, y);
		this.hp = hp;
		this.gameEnv = gameEnv;
	}
	
	public int getHp() {
		return this.hp;
	}

	public void decrementHp(int lostHp) {
		this.hp -= lostHp;
	}

	public boolean isAlive() {
		return this.hp > 0;
	}
	
	public GameEnvironment getGameEnvironment() {
		return this.gameEnv;
	}
	
	public abstract int getAttack();

	public abstract int getDefense();

	public abstract int getAttackRange();
	
	public abstract int getAttackSpeed();
}