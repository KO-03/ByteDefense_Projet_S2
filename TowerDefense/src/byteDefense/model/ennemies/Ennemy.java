/*
 * Ennemy.java
 * Cette classe represente un objet Ennemy, ses responsabilites sont de:
 * - identifier l'ennemi par un identifiant recuperable
 * - recuperer et de decrementer les points de vie de ennemi
 * - recuperer et de fixer les coordonnees xy de ennemi
 * - verifier que l'ennemi est mort ou non
 * - recuperer le type d'ennemi de l'objet
 * - faire se deplacer un ennemi selon le BFS
 * - recuperer les caracteristiques de l'ennemi (points d'attaque, point 
 *   de defense, vitesse d'attaque, portee d'attaque et le butin)
 * - stocker le BFS lié à l'ennemi ainsi que sa tuile de départ  
 *  
 */

package byteDefense.model.ennemies;

import byteDefense.utilities.BFS;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Ennemy {

	private static int counterId = 0;
	
	private int id;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private int hp;
	private int ennemyType;
	private static BFS bfs;
	private int startTile = 20;
	
	public Ennemy(int x, int y, int ennemyType, BFS bfsMap) {
		this.xProperty = new SimpleIntegerProperty(x);
		this.yProperty = new SimpleIntegerProperty(y);
		this.hp = 0;
		this.ennemyType = ennemyType;
		bfs = bfsMap;
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

	public int getEnnemyType() {
		return this.ennemyType;
	}
	
	public void moveEnnemy() {
		int xPos = (int) bfs.pathList.get(startTile).getX();//recupere le X et Y du Point2D selon l'indice
		int yPos = (int) bfs.pathList.get(startTile).getY();
		System.out.println(" x : " + xPos + " y : " + yPos);
		this.setX(xPos * 48);
		this.setY(yPos * 48);
		
		if(startTile > 0)
			startTile--;
    }
	
	public abstract void act();
	
	public abstract float getAttack();

	public abstract int getDefense();

	public abstract int getAttackSpeed();

	public abstract int getAttackRange();
	
	public abstract int getLoot();
}
