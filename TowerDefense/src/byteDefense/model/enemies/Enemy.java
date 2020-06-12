/*
 * Enemy.java
 * Cette classe represente un objet Enemy (un ennemi), ses responsabilites sont de :
 * - stocker et recuperer l'indice de la tile courante de l'ennemi dans le BFS 
 * - stocker le BFS dans lequel l'ennemi se deplace
 * - faire se deplacer un ennemi selon le BFS et mettre a jour l'indice de la tile courante
 * - verifier si un ennemi est arrive au bout du chemin
 */

package byteDefense.model.enemies;

import byteDefense.model.GameArea;
import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import byteDefense.utilities.BFS;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Enemy extends LivingObject {

	private static BFS bfs;
	private IntegerProperty xProperty;
	private IntegerProperty yProperty;
	private int currentTile; // la tuile ou l'ennemi se trouve
	
	public Enemy(BFS bfsMap, GameEnvironment gameEnv) {
		super(50, gameEnv);
		bfs = bfsMap;
		this.xProperty = new SimpleIntegerProperty(GameArea.tilePosX(GameArea.randomSpawnpoint()));
		this.yProperty = new SimpleIntegerProperty(GameArea.tilePosY(GameArea.randomSpawnpoint()));
		this.currentTile = GameArea.tileIndex(this.getX(), this.getY());
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
	
	// Fonction qui verifie si un ennemi est arrive au bout du chemin du BFS
	public boolean isArrived() {
		return this.currentTile == BFS.STOP_TILE;
	}
	
	// Methode qui fait se deplacer un ennemi en fonction du BFS
	public void moveEnnemy() {
		int tileSize = GameArea.TILE_SIZE; // taille d'une tuile dans le plateau de jeu
		
		// Fixage de la position de l'ennemi en fonction du chemin du BFS
		this.setX(GameArea.tilePosX(this.currentTile) * tileSize);
		this.setY(GameArea.tilePosY(this.currentTile) * tileSize);

		// Refixage de la tuile courante
		if(!this.isArrived()) 
			this.currentTile = bfs.cameFrom[this.currentTile];
	}
	
	public abstract int getLoot();
 }
