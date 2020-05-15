/*
 * TileMap.java
 * Cette classe represente un objet TileMap, ses responsabilites sont :
 * - creer la liste de tiles
 * - recuperer la taille de la map en tiles 
 * - recuperer une case avec ses coordonnees
 */

package byteDefense.model;

import java.util.ArrayList;

import byteDefense.utilities.TileMapReader;

public class TileMap {
	
	public static int tilesSize;
	
	private ArrayList<Integer> tilesList;
	
	public TileMap() {
		this.tilesList = new ArrayList<>();
		this.tilesList = new TileMapReader("./resources/tiles.txt").readFile();
		tilesSize = mapSize();
	}
	
	public int mapSize() {
		return (int) Math.sqrt(this.tilesList.size());
	}
	
	public int getCase(int x, int y) {
		return this.tilesList.get(x + tilesSize * y);
	}
}