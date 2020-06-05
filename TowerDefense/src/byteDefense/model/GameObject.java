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

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GameObject {
	
	protected static int counterId = 0;
	private int id;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;

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
}
