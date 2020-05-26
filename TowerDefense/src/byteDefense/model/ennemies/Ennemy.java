/*
 * Ennemy.java
 * Cette classe represente un objet Ennemy, ses responsabilites sont de:
 * - recuperer et de decrementer les points de vie de ennemi
 * - identifier l'ennemi par un identifiant recuperable
 * - recuperer et de fixer les coordonnees xy de ennemi
 * - verifier que l'ennemi est mort ou non
 * - recuperer le type d'ennemi de l'objet
 * - faire se deplacer un ennemi selon le BFS
 * - faire se deplacer aleatoirement un ennemi
 * - recuperer les caracteristiques de l'ennemi (points d'attaque, point 
 *   de defense, vitesse d'attaque, portee d'attaque et le butin)
 * - stocker le BFS lié à l'ennemi ainsi que sa tuile de départ  
 *  
 */

package byteDefense.model.ennemies;

import byteDefense.model.GameObject;
import byteDefense.utilities.BFS;

public abstract class Ennemy extends GameObject {

	private static BFS bfs;
	private int currentIndTile;
	
	public Ennemy(int x, int y, int ennemyType, BFS bfsMap) {
		super(x, y, ennemyType);
		bfs = bfsMap;
		this.currentIndTile = 20;
	}

	public int getcurrentIndTile() {
		return this.currentIndTile;
	}

	public void moveEnnemy() {
		this.setX((int)bfs.pathList.get(this.currentIndTile).getX() * 48);
		this.setY((int)bfs.pathList.get(this.currentIndTile).getY() * 48);
		
		if(this.currentIndTile > 0)
			this.currentIndTile--;
	}
}
