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
	
	public static int tilesSize;
	
	private ArrayList<Integer> tilesList;
	
	public GameArea() {
		this.tilesList = new ArrayList<>();
		this.tilesList = new GameAreaReader("./resources/tiles.txt").readFile();
		tilesSize = mapSize();
	}
	
	public ArrayList<Integer> getTilesList(){
		return this.tilesList;
	}
	
	public int mapSize() {
		return (int) Math.sqrt(this.tilesList.size());
	}
	
	public int tileMapCase(int x, int y) {
		return this.tilesList.get(tileIndex(x, y));
	}
	
	public int tileIndex(int x, int y) {
		return x + GameArea.tilesSize * y;
	}
	
	public int tilePosX(int pos) {
		return pos%GameArea.tilesSize;
	}

	public int tilePosY(int pos) {
		return pos/GameArea.tilesSize;
	}
}