/*
 * Tower.java
 * Cette classe represente un objet Tower. ses responsabilites sont de :
 * - identifier l'ennemi par un identifiant recuperable
 * - recuperer et de fixer les coordonnees xy de ennemi
 * - recuperer et de decrementer les points de vie d'une tourelle
 * - verifier que l'ennemi est mort ou non
 * - recuperer les caracteristiques de l'ennemi (points d'attaque, point 
 *   de defense, vitesse d'attaque, portee d'attaque et le cout)
 */

package byteDefense.model.towers;

public abstract class Tower {
	
	private static int counterId = 0;
	
	private int id;
	private int x, y;
	private int hp;
	
	public Tower(int x, int y) {
		this.x = x;
		this.y = y;
		this.hp = 0;
		this.id = counterId;
		counterId++;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
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
	
	public abstract int getAttack();

	public abstract int getDefense();

	public abstract int getAttackSpeed();

	public abstract int getAttackRange();
	
	public abstract int getCost();
}
