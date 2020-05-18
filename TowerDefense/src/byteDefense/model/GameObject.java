/*
 * GameObject.java
 * Cette classe parent permet de gérer :
 * - la génération des identifiants des objets du jeu (Tourelles et ennemies)
 * - la gestion
 * 
 */

package byteDefense.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameObject {

	private static int counterId =0;
	
	private int id;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private int hp;
	
	public GameObject(int x, int y, int hp) {
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
	
}