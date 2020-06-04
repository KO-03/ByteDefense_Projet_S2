/*
 * EnnemyFactory.java
 * Cette classe gere la fabrication d'instance d'Ennemy, sa responsabilite est de :
 * - Creer un ennemi en fonction du type recupere
 */

package byteDefense.factories;

import byteDefense.model.GameEnvironment;
import byteDefense.model.enemies.Adware;
import byteDefense.model.enemies.Bot;
import byteDefense.model.enemies.Ennemy;
import byteDefense.model.enemies.Ransomware;
import byteDefense.model.enemies.Rookit;
import byteDefense.model.enemies.Spyware;
import byteDefense.model.enemies.TrojanHorse;
import byteDefense.utilities.BFS;

public class EnnemyFactory {

	public static Ennemy getInstance(int ennemyType, BFS bfsMap, GameEnvironment gameEnv) {
		Ennemy ennemy = null;
		
		switch (ennemyType) {
		case 1:
			ennemy = new Rookit(bfsMap, gameEnv);
			break;
		case 2:
			ennemy = new Adware(bfsMap, gameEnv);
			break;
		case 3:
			ennemy = new Bot(bfsMap, gameEnv);
			break;
		case 4:
			ennemy = new Ransomware(bfsMap, gameEnv);
			break;
		case 5:
			ennemy = new Spyware(bfsMap, gameEnv);
		case 6:
			ennemy = new TrojanHorse(bfsMap, gameEnv);
		}
		return ennemy;
	}
}
