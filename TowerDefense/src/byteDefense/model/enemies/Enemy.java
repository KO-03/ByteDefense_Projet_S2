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

public abstract class Enemy extends LivingObject {

	private static BFS bfs;
	private int currentTile;
	
	public Enemy(BFS bfsMap, GameEnvironment gameEnv) {
		super(GameArea.tilePosX(GameArea.spawnPoint), GameArea.tilePosY(GameArea.spawnPoint), 50, gameEnv);
		bfs = bfsMap;
		this.currentTile = GameArea.tileIndex(super.getX(), super.getY());
	}
	
	public abstract int getLoot();
	
	public void moveEnnemy() {
		// fixage de la position de l'ennemi en fonction du chemin du BFS
		this.setX(GameArea.tilePosX(this.currentTile) * GameArea.TILE_SIZE);
		this.setY(GameArea.tilePosY(this.currentTile) * GameArea.TILE_SIZE);

		if(!this.isArrived()) 
			this.currentTile = bfs.cameFrom[this.currentTile];
	}
	
	public boolean isArrived() {
		return this.currentTile == BFS.STOP_TILE; // -1 correspond au point d'arrivée
	}
 }
