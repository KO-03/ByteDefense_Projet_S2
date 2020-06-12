/*
 * GameAreaView.java
 * Cette classe gere la partie visuelle de la GameArea (plateau de jeu), ses responsabilites sont de :
 * - stocker la GameArea
 * - stocker la grille de tuile de la vue
 * - charger et stocker les textures des tiles
 * - stocker les donnees des types de tiles
 * - faire la correspondance entre les types de tiles et les donnees de la GameArea a ajouter 
 * - ajouter la gameArea tile par tile a la grille 
 */

package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.GameArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class GameAreaView {

	// Donnees correspondant aux composantes visuelles (tiles) de la tilemap 
	public static final int CORNER = 1;
	public static final int HORIZONTAL_PATH = 2;
	public static final int VERTICAL_PATH = 3;
	public static final int VERTICAL_WALL = 4;
	public static final int INTERSECTION_PATH = 5;
	public static final int HORIZONTAL_WALL = 6;
	public static final int TOWER_ZONE = 7;
	public static final int LAPTOP_ARRIVAL = 8;
	public static final int SPAWN_POINT = 9;

	// Textures des tuiles du plateau de jeu
	private static Image CORNER_SRC_IMG; 
	private static Image HORIZONTAL_PATH_SRC_IMG;
	private static Image VERTICAL_PATH_SRC_IMG;
	private static Image VERTICAL_WALL_SRC_IMG;
	private static Image INTERSECTION_PATH_SRC_IMG;
	private static Image HORIZONTAL_WALL_SRC_IMG;
	private static Image TOWER_ZONE_SRC_IMG;
	private static Image LAPTOP_ARRIVAL_SRC_IMG;
	private static Image SPAWN_POINT_SRC_IMG;

	private GameArea gameArea; // plateau de jeu
	private TilePane gameBoard; // grille de tuiles ou le plateau de jeu est affiches

	public GameAreaView(GameArea gameArea, TilePane gameBoard) {
		this.gameArea = gameArea;
		this.gameBoard = gameBoard;
		this.imageLoader();
		this.generateMapView();
	}

	// Methode qui recupere et charge les textures du plateau de jeu
	private void imageLoader() {
		try {
			CORNER_SRC_IMG  = new Image(new File("./resources/tilemapTextures/corner.png").toURI().toURL().toString()); 
			HORIZONTAL_PATH_SRC_IMG  = new Image(new File("./resources/tilemapTextures/horizontal_path.png").toURI().toURL().toString()); 
			VERTICAL_PATH_SRC_IMG = new Image(new File("./resources/tilemapTextures/vertical_path.png").toURI().toURL().toString()); 
			VERTICAL_WALL_SRC_IMG  = new Image(new File("./resources/tilemapTextures/vertical_wall.png").toURI().toURL().toString()); 
			INTERSECTION_PATH_SRC_IMG  = new Image(new File("./resources/tilemapTextures/intersection_path.png").toURI().toURL().toString()); 
			HORIZONTAL_WALL_SRC_IMG  = new Image(new File("./resources/tilemapTextures/horizontal_wall.png").toURI().toURL().toString()); 
			TOWER_ZONE_SRC_IMG  = new Image(new File("./resources/tilemapTextures/tower_zone.png").toURI().toURL().toString());
			LAPTOP_ARRIVAL_SRC_IMG = new Image(new File("./resources/tilemapTextures/laptop.png").toURI().toURL().toString());
			SPAWN_POINT_SRC_IMG = new Image(new File("./resources/tilemapTextures/spawn.png").toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	// Fonction qui retourne la texture correspondante a tuile donne en parametre
	private Image tileImageGetter(int mapTile) {
		Image tileImg = null;

		switch (mapTile) {
		case CORNER:
			tileImg = CORNER_SRC_IMG;
			break;
		case HORIZONTAL_PATH:
			tileImg = HORIZONTAL_PATH_SRC_IMG;
			break;
		case VERTICAL_PATH:
			tileImg = VERTICAL_PATH_SRC_IMG;
			break;
		case VERTICAL_WALL:
			tileImg = VERTICAL_WALL_SRC_IMG;
			break;
		case INTERSECTION_PATH:
			tileImg = INTERSECTION_PATH_SRC_IMG;
			break;
		case HORIZONTAL_WALL:
			tileImg = HORIZONTAL_WALL_SRC_IMG;
			break;
		case TOWER_ZONE:
			tileImg = TOWER_ZONE_SRC_IMG;
			break;
		case LAPTOP_ARRIVAL:
			tileImg = LAPTOP_ARRIVAL_SRC_IMG;
			break;
		case SPAWN_POINT:
			tileImg = SPAWN_POINT_SRC_IMG;
			break;
		}
		return tileImg;
	}

	// Methode qui construit la vue du plateau de jeu tuile par tuile en initialisant leurs donnees
	private void generateMapView() {
		Image tileImg;
		int tilesSize = GameArea.gameAreaTilesSize; // taille du plateau de jeu en nombre de tuile

		for (int y = 0; y < tilesSize; y++) {
			for (int x = 0; x < tilesSize; x++) {
				tileImg = this.tileImageGetter(this.gameArea.gameAreaCase(x, y));

				if(tileImg != null) {
					ImageView tile = new ImageView();
					tile.setImage(tileImg);
					tile.setX(x);
					tile.setY(y);
					this.gameBoard.getChildren().add(tile);
				}		
			}
		}
	}
}