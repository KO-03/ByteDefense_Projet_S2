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

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Tower {
	
	private static int counterId = 0;
	
	private int id;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private int hp;
	private int towerType;
	
	public Tower(int x, int y, int towerType) {
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.hp = 0;
		this.towerType = towerType;
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
