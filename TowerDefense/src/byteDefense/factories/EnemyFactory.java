/*
 * EnemyFactory.java
 * Cette classe gere la fabrication d'instance d'Enemy (un ennemi), sa responsabilite est de :
 * - Creer un ennemi en fonction du type recupere
 */

package byteDefense.factories;

import byteDefense.model.GameEnvironment;
import byteDefense.model.enemies.Adware;
import byteDefense.model.enemies.Bot;
import byteDefense.model.enemies.Enemy;
import byteDefense.model.enemies.Ransomware;
import byteDefense.model.enemies.Rookit;
import byteDefense.model.enemies.Spyware;
import byteDefense.model.enemies.TrojanHorse;
import byteDefense.utilities.BFS;

public class EnemyFactory {

	public static Enemy getInstance(int enemyType, BFS bfsMap, GameEnvironment gameEnv) {
		Enemy enemy = null;
		
		switch (enemyType) {
		case 1:
			enemy = new Rookit(bfsMap, gameEnv);
			break;
		case 2:
			enemy = new Adware(bfsMap, gameEnv);
			break;
		case 3:
			enemy = new Bot(bfsMap, gameEnv);
			break;
		case 4:
			enemy = new Ransomware(bfsMap, gameEnv);
			break;
		case 5:
			enemy = new Spyware(bfsMap, gameEnv);
		case 6:
			enemy = new TrojanHorse(bfsMap, gameEnv);
		}
		return enemy;
	}
}
