/*
 * GameArea.java
 * Cette classe represente un objet GameArea, ses responsabilites sont :
 * - creer la liste de tiles a partir des donnees lues dans un fichier
 * - recuperer la taille de la map en tiles 
 * - calculer l'indice d'une case de la GameArea avec ses coordonnees
 * - calculer  les coordonnees xy a partir de l'indice de la case
 */

package byteDefense.model;

import java.util.ArrayList;

import byteDefense.utilities.GameAreaReader;

public class GameArea {

	public static final int TILE_SIZE = 48;
	public static int gameAreaSize;
	
	private ArrayList<Integer> tilesList;
	
	public GameArea() {
		this.tilesList = new ArrayList<>();
		this.tilesList = new GameAreaReader("./resources/tiles.txt").readFile();
		gameAreaSize = gameAreaSize();
	}
	
	public ArrayList<Integer> getTilesList(){
		return this.tilesList;
	}
	
	public int gameAreaSize() {
		return (int) Math.sqrt(this.tilesList.size());
	}
	
	public int gameAreaCase(int x, int y) {
		return this.tilesList.get(tileIndex(x, y));
	}
	
	public int tileIndex(int x, int y) {
		return x + gameAreaSize * y;
	}
	
	public int tilePosX(int indTilePos) {
		return indTilePos % gameAreaSize;
	}

	public int tilePosY(int indTilePos) {
		return indTilePos / gameAreaSize;
	}
	
	public boolean isWalkable(int index) {
		return index == 2 || index == 3 ||  index == 5;
	}
	
	private boolean onGameArea(int x, int y) {
		return x > 0 && y > 0 && x < gameAreaSize && y < gameAreaSize; 
	}
	
	public boolean isPlaceable(int x, int y) {
		x = x / TILE_SIZE; 
		y = y / TILE_SIZE;
		
		if (this.onGameArea(x, y) && this.gameAreaCase(x, y) == 7)
			return true;
		
		return false;
	}
	
}