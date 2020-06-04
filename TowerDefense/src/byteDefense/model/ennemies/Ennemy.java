/*
 * Ennemy.java
 * Cette classe represente un objet Ennemy, ses responsabilites sont de :
 * - recuperer le butin d'un ennemi
 * - faire se deplacer un ennemi selon le BFS
 * - stocker le BFS liee l'ennemi ainsi que sa tuile de d�part  
 * - stocker et mettre a jour l'indice de la tile courante du BFS ou l'ennemi est positionne
 */

package byteDefense.model.ennemies;

import byteDefense.model.GameArea;
import byteDefense.model.GameEnvironment;
import byteDefense.model.GameObject;
import byteDefense.utilities.BFS;

public abstract class Ennemy extends GameObject {

	private BFS bfs;

	private int currentIndTile;
	
	public Ennemy(BFS bfsMap, GameEnvironment gameEnv) {
		super(GameArea.tilePosX(GameArea.spawnPoint), GameArea.tilePosY(GameArea.spawnPoint), 50, gameEnv);
		bfs = bfsMap;
		this.currentIndTile = GameArea.tileIndex(super.getX(), super.getY());
	}

	public int getCurrentIndTile() {
		return this.currentIndTile;
	}
	
	public abstract int getLoot();

	public void moveEnnemy() {
		// fixage de la position de l'ennemi en fonction du chemin du BFS
		this.setX(GameArea.tilePosX(this.currentIndTile)* GameArea.TILE_SIZE);
		this.setY(GameArea.tilePosY(this.currentIndTile)* GameArea.TILE_SIZE);

		// decrementation de l'indice de la tile courante du BFS
		if(!this.ennemyArrived())// -1 correspond au point d'arrivée
			this.currentIndTile = this.bfs.cameFrom[this.currentIndTile];
	}
	
	public boolean ennemyArrived() {
		return this.currentIndTile == -1;
	}
	
	public void act() {
		if (!this.ennemyArrived())
			this.moveEnnemy();
	}
}
