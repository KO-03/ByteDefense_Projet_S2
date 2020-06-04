/*
 * WaveServices.java
 * Cette classe gere les services/actions lies a la Wave (vague d'ennemis), ses responsabilites sont de : 
 * - stocker le BFS que la vague d'ennemis doit suivre
 * - stocker l'environnement de jeu de la vague
 * - creer un ennemi en fonction des ennemis deja ajoutes 
 * - ajouter un ennemi cree a la Wave
 */

package byteDefense.model.enemies;

import byteDefense.factories.EnnemyFactory;
import byteDefense.model.GameEnvironment;
import byteDefense.utilities.BFS;

public class WaveServices {

	private BFS bfsMap;
	private GameEnvironment gameEnv;
	
	public WaveServices(BFS bfsMap, GameEnvironment gameEnvironment) {
		this.bfsMap = bfsMap;
		this.gameEnv = gameEnvironment;
	}
	
	private Ennemy createEnnemy(Wave wave) {
		int ennemyType = wave.getEnnemyType();
		
		wave.decrementEnnemyQty();
		if (wave.getEnemiesInfos().peek().everyEnnemiesSpawned())
			wave.removeWaveEnnemy();

		return EnnemyFactory.getInstance(ennemyType, this.bfsMap, this.gameEnv);
	}

	public void addEnnemy(Wave wave) {
		Ennemy ennemy = createEnnemy(wave); 

		if (ennemy != null)
			this.gameEnv.addGameObject(ennemy);
	}
}