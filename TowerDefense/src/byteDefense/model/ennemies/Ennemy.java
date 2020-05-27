/*
 * Ennemy.java
 * Cette classe represente un objet Ennemy, ses responsabilites sont de :
 * - faire se deplacer un ennemi selon le BFS
 * - stocker le BFS lié à l'ennemi ainsi que sa tuile de départ  
 * - stocker et mettre a jour l'indice de la tile courante du BFS où l'ennemi est positionne
 */

package byteDefense.model.ennemies;

import byteDefense.model.GameObject;
import byteDefense.utilities.BFS;

public abstract class Ennemy extends GameObject {

	private static BFS bfs;
	private int currentIndTile;
	
	public Ennemy(int x, int y, BFS bfsMap) {
		super(x, y);
		bfs = bfsMap;
		this.currentIndTile = 20;
	}

	public int getcurrentIndTile() {
		return this.currentIndTile;
	}

	public void moveEnnemy() {
		// fixage de la position de l'ennemi en fonction du chemin du BFS
		this.setX((int)bfs.pathList.get(this.currentIndTile).getX() * 48);
		this.setY((int)bfs.pathList.get(this.currentIndTile).getY() * 48);
		
		// decrementation de l'indice de la tile courante du BFS
		if(this.currentIndTile > 0)
			this.currentIndTile--;
	}
}
