/*
 * GameArea.java
 * Cette classe represente un objet GameArea (plateau de jeu), ses responsabilites sont :
 * - stocker les listes donnees des differents niveaux dans une liste
 * - stocker la dimension d'une tuile (TILE_SIZE)
 * - calculer et stocker la dimension en nombre de tuile du plateau de jeu (gameAreaTilesSize)
 * - stocker le point d'arrivee et de depart des ennemis sur le plateau de jeu
 * - stocker et recuperer la liste de tuiles (tilesList) a partir du niveau
 * - recuperer une donnee d'une tuile de la tilesList a partir de coordonnees xy
 * - calculer l'indice d'une case dans le plateau de jeu à partir de ses coordonnees xy
 * - calculer  les coordonnees xy a partir de l'indice de la case
 * - verifier si la case a un indice precis est un chemin ou pas
 * - verifier si la case a une coordonnee xy precise entre dans les limites de la GameArea
 * - verifier si l'on peut placer une tourelle sur une case a une coordonnee xy precise 
 */

package byteDefense.model;

import java.util.ArrayList;
import java.util.Random;

import byteDefense.utilities.GameAreaReader;

public class GameArea {
	
	private static ArrayList<ArrayList<Integer>> levelsTilesList;
	public static final int TILE_SIZE = 48;
	public static int gameAreaTilesSize;
	public static ArrayList<Integer> arrivalPoints = new ArrayList<>();
	public static ArrayList<Integer> spawnPoints = new ArrayList<>();
	
	private ArrayList<Integer> tilesList;
	
	public GameArea() {
		levelsTilesList = GameAreaReader.generateLevelsTilesList("./resources/tiles.txt");
		this.tilesList = this.fixLevelTilesList(1);
		gameAreaTilesSize = this.gameAreaTilesSize();
		this.referenceSpecialPoints();
	}
	
	public void referenceSpecialPoints() {
		for(int i = 0; i < this.tilesList.size(); i++ ) {
			if(tilesList.get(i) == 9)
				spawnPoints.add(i);			
			else if(tilesList.get(i) == 8)
				arrivalPoints.add(i);
		}
	}
	
	public static int randomSpawnpoint() {
		return spawnPoints.get(new Random().nextInt(spawnPoints.size()));
	}
	
	public ArrayList<Integer> getTilesList(){
		return this.tilesList;
	}
	
	private ArrayList<Integer> fixLevelTilesList(int level) {
		return levelsTilesList.get(level - 1);
	}
	
	private int gameAreaTilesSize() {
		return (int) Math.sqrt(this.tilesList.size());
	}
	
	public int gameAreaCase(int x, int y) {
		return this.tilesList.get(tileIndex(x, y));
	}
	
	public static int tileIndex(int x, int y) {
		return x + gameAreaTilesSize * y;
	}
	
	public static int tilePosX(int indTilePos) {
		return indTilePos % gameAreaTilesSize;
	}

	public static int tilePosY(int indTilePos) {
		return indTilePos / gameAreaTilesSize;
	}
	
	public boolean isWalkable(int index) {
		return index == 2 || index == 3 ||  index == 5 || index == 9;
	}
	
	private boolean onGameArea(int x, int y) {
		return x > 0 && y > 0 && x < gameAreaTilesSize && y < gameAreaTilesSize; 
	}
	
	public boolean isPlaceable(int x, int y) {
		x = x / TILE_SIZE; 
		y = y / TILE_SIZE;
		
		if (this.onGameArea(x, y) && this.gameAreaCase(x, y) == 7)
			return true;
		
		return false;
	}
}