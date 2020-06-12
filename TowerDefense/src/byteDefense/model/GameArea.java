/*
 * GameArea.java
 * Cette classe represente un objet GameArea (plateau de jeu), ses responsabilites sont :
 * - stocker les listes donnees des differents niveaux dans une liste
 * - stocker la dimension d'une tuile (TILE_SIZE)
 * - calculer et stocker la dimension en nombre de tuile du plateau de jeu (gameAreaTilesSize)
 * - construire, stocker la liste des points d'arrivee et de depart du plateau de jeu
 * - recuperer aleatoirement le point d'arrivee et de depart dans la liste
 * - stocker et recuperer la liste de tuiles (tilesList) a partir du niveau
 * - recuperer la liste de tuiles correspondant a un niveau
 * - recuperer une donnee d'une tuile de la tilesList a partir de coordonnees xy
 * - calculer l'indice d'une case dans le plateau de jeu à partir de ses coordonnees xy
 * - calculer les coordonnees xy a partir de l'indice de la case
 * - verifier si la case a un indice precis est un chemin ou pas
 * - verifier si la case a une coordonnee xy precise entre dans les limites du plateau de jeu
 * - verifier si l'on peut placer une tourelle sur une case a une coordonnee xy precise 
 */

package byteDefense.model;

import java.util.ArrayList;
import java.util.Random;

import byteDefense.utilities.GameAreaReader;

public class GameArea {
	
	private static ArrayList<ArrayList<Integer>> levelsTilesList; // liste des listes de tuiles de chaque niveau
	public static final int TILE_SIZE = 48; // taille d'une tuile en pixel
	public static int gameAreaTilesSize; // taille du plateau de jeu en nombre de tuiles
	public static ArrayList<Integer> arrivalPoints = new ArrayList<>(); // liste des points d'arrive
	public static ArrayList<Integer> spawnPoints = new ArrayList<>(); // liste des points de depart
	
	private ArrayList<Integer> tilesList; // liste des tuiles
	
	public GameArea(int level) {
		levelsTilesList = GameAreaReader.generateLevelsTilesList("./resources/tiles.txt");
		this.tilesList = this.fixLevelTilesList(level);
		gameAreaTilesSize = this.gameAreaTilesSize();
		this.referenceSpecialPoints();
	}
	
	// Methoque qui construire la liste de points de depart et celle des points d'arrive
	public void referenceSpecialPoints() {
		for(int i = 0; i < this.tilesList.size(); i++ ) {
			if(tilesList.get(i) == 9)
				spawnPoints.add(i);			
			else if(tilesList.get(i) == 8)
				arrivalPoints.add(i);
		}
	}
	
	// Fonction qui retourne aleatoirement un point de depart de la liste des points de departs
	public static int randomSpawnpoint() {
		return spawnPoints.get(new Random().nextInt(spawnPoints.size()));
	}
	
	public ArrayList<Integer> getTilesList(){
		return this.tilesList;
	}
	
	// Fonction qui retourne la liste de tuiles correspondant au niveau passe en parametre
	private ArrayList<Integer> fixLevelTilesList(int level) {
		return levelsTilesList.get(level - 1);
	}
	
	// Fonction qui retourne la taille du plateau de jeu en nombre de tuile
	private int gameAreaTilesSize() {
		return (int) Math.sqrt(this.tilesList.size());
	}
	
	// Fonction qui retourne la case correspondante a une tuile a une coordonnees xy
	public int gameAreaCase(int x, int y) {
		return this.tilesList.get(tileIndex(x, y));
	}
	
	// Fonction qui retourne l'indice d'une tuile en fonction de coordonnees xy
	public static int tileIndex(int x, int y) {
		return x + gameAreaTilesSize * y;
	}
	
	// Fonction qui retourne la position x d'une tuile a partir de l'indice de la tuile
	public static int tilePosX(int indTilePos) {
		return indTilePos % gameAreaTilesSize;
	}

	// Fonction qui retourne la position y d'une tuile a partir de l'indice de la tuile
	public static int tilePosY(int indTilePos) {
		return indTilePos / gameAreaTilesSize;
	}
	
	// Fonction qui verifie si une tuile du plateau de jeu est un chemin
	public boolean isWalkable(int index) {
		return index == 2 || index == 3 ||  index == 5 || index == 9 || index == 8;
	}
	
	// Fonction qui verifie si des coordonnees xy donnees entrent dans les limites du plateau de jeu
	private boolean onGameArea(int x, int y) {
		return x > 0 && y > 0 && x < gameAreaTilesSize && y < gameAreaTilesSize; 
	}
	
	// Fonction qui verifie si l'on peut placer une tourelle sur une case a une coordonnee xy donnee
	public boolean isPlaceable(int x, int y) {
		x = x / TILE_SIZE; 
		y = y / TILE_SIZE;
		
		// la position est dans les limites du plateau de jeu et la case est une zone de tourelle
		if (this.onGameArea(x, y) && this.gameAreaCase(x, y) == 7)
			return true;
		
		return false;
	}
}