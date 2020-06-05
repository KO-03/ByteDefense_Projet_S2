/*
 * GameObject.java
 * Cette classe parent represente un objet de jeu (un ennemi ou une tourelle), ses responsabilites sont de :
 * - identifier un objet de jeu par un identifiant recuperable
 * - recuperer et fixer les coordonnees xy d'un objet de jeu
 * - recuperer l'indice courant correpondant aux coordonnees xy de l'objet de jeu 
 * - recuperer et decrementer les points de vie d'un objet de jeu
 * - verifier qu'un objet de jeu est mort ou non
 * - verifier si le gameObject est une tourelle ou un ennemi
 * - trouver une cible aux alentours de l'objet de jeu
 * - faire tirer un objet de jeu sur une cible
 * - gerer les actions de tirs d'un objet de jeu
 * - recuperer les caracteristiques d'un objet de jeu (points d'attaque, point 
 *   de defense, vitesse d'attaque, portee d'attaque et l'action)
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