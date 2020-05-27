/*
 * GameObject.java
 * Cette classe parent permet de gerer :
 * - identifier un objet de jeu par un identifiant recuperable
 * - recuperer et de fixer les coordonnees xy d'un objet de jeu
 * - recuperer et de decrementer les points de vie d'un objet de jeu
 * - verifier qu'un objet de jeu est mort ou non
 * - recuperer les caracteristiques d'un objet de jeu (points d'attaque, point 
 *   de defense, vitesse d'attaque, portee d'attaque et l'action)
 */

package byteDefense.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class GameObject {

	private static int counterId = 0;
	
	private int id;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private int hp;
	
	public GameObject(int x, int y) {
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.id = counterId;
		counterId++;
	}
	
	public int getId() {
		return this.id;
	}
	
	public IntegerProperty getXProperty() {
		return this.xProperty;
	}
	
	public int getX() {
		return this.xProperty.getValue();
	}
	
	public void setX(int newX) {
		this.xProperty.setValue(newX);
	}
	
	public IntegerProperty getYProperty() {
		return this.yProperty;
	}
	
	public int getY() {
		return this.yProperty.getValue();
	}
	
	public void setY(int newY) {
		this.yProperty.setValue(newY);
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
	
	public abstract float getAttack();

	public abstract int getDefense();

	public abstract int getAttackSpeed();

	public abstract int getAttackRange();
	
	public abstract void act();
}