/*
 * Tower.java
 * Cette classe represente un objet Tower. ses responsabilites sont de :
 * - identifier la tourelle par un identifiant recuperable
 * - recuperer les coordonnees xy d'une tourelle
 * - recuperer et de decrementer les points de vie d'une tourelle
 * - verifier que la tourelle est mort ou non
 * - recuperer les caracteristiques de la tourelle (points d'attaque, point 
 *   de defense, vitesse d'attaque, portee d'attaque et le cout)
 */

package byteDefense.model.towers;

public abstract class Tower {
	
	private static int counterId = 0;
	
	private int id;
	private int x, y;
	private int hp;
	private int towerType;
	
	public Tower(int x, int y, int towerType) {
		this.x = x;
		this.y = y;
		this.hp = 0;
		this.towerType = towerType;
		this.id = counterId;
		counterId++;
	}
	
	public int getId() {
		return this.id;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
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
	
	public int getTowerType() {
		return this.towerType;
	}
	
	public abstract int getAttack();

	public abstract int getDefense();

	public abstract int getAttackSpeed();

	public abstract int getAttackRange();
	
	public abstract int getCost();
	
	public abstract void act();
}
