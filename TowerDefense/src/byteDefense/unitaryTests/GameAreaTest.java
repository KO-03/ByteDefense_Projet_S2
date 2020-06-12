package byteDefense.unitaryTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import byteDefense.model.GameArea;

/*
 * Tests unitaire de la classe du modele GameArea.java
 */
class GameAreaTest {
	
	private GameArea gameMap;
	private int TilesListSize = (int) Math.pow(GameArea.gameAreaTilesSize, 2);// 14 au carré soit 196

	@BeforeEach
	void setUp() throws Exception {
		gameMap = new GameArea(1);
	}	

	@Test
	void isPlacableTest() {
		// Sur la carte chargée il s'agit du coin haut gauche
		assertFalse(gameMap.isWalkable(0));
		int aWalkableTileIndex =  GameArea.spawnPoints.get(1);
		int tileValue = gameMap.getTilesList().get(aWalkableTileIndex);
		assertTrue(gameMap.isWalkable(tileValue));
		//Tiles hors map
		assertFalse(gameMap.isWalkable(-1));
		assertFalse(gameMap.isWalkable(200));
	}
	
	@Test
	void tileIndexTest() {
		// Sur la carte chargée il s'agit du coin haut gauche
		assertEquals(-15, GameArea.tileIndex(-1, -1));
		assertEquals(86, GameArea.tileIndex(100, -1));
		//Tiles hors map le calcul reste bon
		assertEquals(192, GameArea.tileIndex(-4, 14));
		assertEquals(74, GameArea.tileIndex(4, 5));
	}
	
	@Test
	void tilePosXTest() {
		// La methode tilePosX permet d'avoir le X corespondant à l'indice sur la liste des tiles
		assertEquals(-1, GameArea.tilePosX(-1));
		assertEquals(1, GameArea.tilePosX(1));
		assertEquals(0, GameArea.tilePosX(TilesListSize)); //TilesListSize == 196
		assertEquals(1, GameArea.tilePosX(TilesListSize+1)); 
	}
	
	@Test
	void tilePosYTest() {
		// La methode tilePosY permet d'avoir le Y corespondant à l'indice sur la liste des tiles
		assertEquals(0, GameArea.tilePosY(-1));
		assertEquals(0, GameArea.tilePosY(1));
		assertEquals(14, GameArea.tilePosY(TilesListSize));
		assertEquals(14, GameArea.tilePosY(TilesListSize+1)); 
	}

}
