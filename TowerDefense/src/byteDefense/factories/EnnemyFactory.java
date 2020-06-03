/*
 * EnnemyFactory.java
 * Cette classe gere la fabrication d'instance d'Ennemy, sa responsabilite est de :
 * - Creer un ennemi en fonction du type recupere
 */

package byteDefense.factories;

import byteDefense.model.ennemies.Adware;
import byteDefense.model.ennemies.Bot;
import byteDefense.model.ennemies.Ennemy;
import byteDefense.model.ennemies.Ransomware;
import byteDefense.model.ennemies.Rookit;
import byteDefense.model.ennemies.Spyware;
import byteDefense.model.ennemies.TrojanHorse;
import byteDefense.utilities.BFS;

public class EnnemyFactory {

	public static Ennemy getInstance(int ennemyType, BFS bfsMap) {
		Ennemy ennemy = null;
		
		switch (ennemyType) {
		case 1:
			ennemy = new Rookit(bfsMap);
			break;
		case 2:
			ennemy = new Adware(bfsMap);
			break;
		case 3:
			ennemy = new Bot(bfsMap);
			break;
		case 4:
			ennemy = new Ransomware(bfsMap);
			break;
		case 5:
			ennemy = new Spyware(bfsMap);
		case 6:
			ennemy = new TrojanHorse(bfsMap);
		}
		return ennemy;
	}
}
