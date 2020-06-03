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
import byteDefense.model.GameObject;
import byteDefense.utilities.BFS;

public abstract class Ennemy extends GameObject {

	private BFS bfs;

	private int currentIndTile;

	public Ennemy(int x, int y, BFS bfsMap) {
		super(x, y, 50);
		bfs = bfsMap;
		this.currentIndTile = 139;
	}

	public int getCurrentIndTile() {
		return this.currentIndTile;
	}

	public abstract int getLoot();

	public void moveEnnemy() {
		// fixage de la position de l'ennemi en fonction du chemin du BFS
		this.setX(this.bfs.gameArea.tilePosX(this.currentIndTile)* GameArea.TILE_SIZE);
		this.setY(this.bfs.gameArea.tilePosY(this.currentIndTile)* GameArea.TILE_SIZE);

		// decrementation de l'indice de la tile courante du BFS
		if(this.currentIndTile !=-1)// -1 correspond au point d'arrivée
			this.currentIndTile = this.bfs.cameFrom[this.currentIndTile];
	}
	
}
