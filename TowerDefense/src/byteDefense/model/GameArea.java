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

	public final int TILE_SIZE = 48;
	
	public static int tilesSize;
	
	private ArrayList<Integer> tilesList;
	
	public GameArea() {
		this.tilesList = new ArrayList<>();
		this.tilesList = new GameAreaReader("./resources/tiles.txt").readFile();
		tilesSize = gameAreaSize();
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
		return x + tilesSize * y;
	}
	
	public int tilePosX(int indTilePos) {
		return indTilePos % tilesSize;
	}

	public int tilePosY(int indTilePos) {
		return indTilePos / tilesSize;
	}
	
	public boolean onGameArea(int x, int y) {
		return x > 0 && y > 0 && x < tilesSize && y < tilesSize; 
	}
	
}