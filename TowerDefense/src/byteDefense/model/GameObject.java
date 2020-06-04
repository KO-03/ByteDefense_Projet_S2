/*
 * GameObject.java
 * Cette classe parent permet de gerer :
 * - identifier un objet de jeu par un identifiant recuperable
 * - recuperer et de fixer les coordonnees xy d'un objet de jeu
 * - recuperer et de decrementer les points de vie d'un objet de jeu
 * - verifier qu'un objet de jeu est mort ou non
 * - recuperer les caracteristiques d'un objet de jeu (points d'attaque, point 
 *   de defense, vitesse d'attaque, portee d'attaque et l'action)
 * - faire agir un objet de jeu
 */

package byteDefense.model;

import byteDefense.model.ennemies.Ennemy;
import byteDefense.model.towers.Tower;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class GameObject {

	protected static int counterId = 0;

	private int id;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private int hp;
	
	protected GameEnvironment gameEnv;

	public GameObject(int x, int y, int hp, GameEnvironment gameEnv) {
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.hp = hp;
		this.gameEnv = gameEnv;
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

	public int getCurrentIndTile() {
		return GameArea.tileIndex(this.getX(), this.getY());
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
	
	public boolean isTower() {
		return this instanceof Tower;
	}
	
	public GameObject findTarget() {
		for(GameObject gameObject : this.gameEnv.getGameObjectsList()) {
			if ((this.isTower() && gameObject instanceof Ennemy) || (!this.isTower() && gameObject instanceof Tower)) {
				if ((this.getY() - this.getAttackRange() * GameArea.TILE_SIZE <= gameObject.getY() && gameObject.getY() <= this.getY() + this.getAttackRange() * GameArea.TILE_SIZE) &&
					(this.getX() - this.getAttackRange() * GameArea.TILE_SIZE <= gameObject.getX() && gameObject.getX() <= this.getX() + this.getAttackRange() * GameArea.TILE_SIZE)) {
					System.out.println("Piou piou !");
					return gameObject;
				}
			}
		}
		return null;
	}
	
	public abstract int getAttack();

	public abstract int getDefense();

	public abstract int getAttackSpeed();

	public abstract int getAttackRange();

	public abstract void actSpecific();
}