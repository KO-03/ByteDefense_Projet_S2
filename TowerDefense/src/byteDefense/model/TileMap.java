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
	
	public int tileMapCase(int x, int y) {
		return this.tilesList.get(tileIndex(x, y));
	}
	
	public int tileIndex(int x, int y) {
		return x + TileMap.tilesSize * y;
	}
	
	public int tilePosX(int pos) {
		return pos%TileMap.tilesSize;
	}

	public int tilePosY(int pos) {
		return pos/TileMap.tilesSize;
	}
	
	public ArrayList<Integer> getTilesList(){
		return this.tilesList;
	}
}