/*
 * WaveServices.java
 * Cette classe gere les services/actions lies a la Wave (vague d'ennemis) a ajouter a l'environnement, ses responsabilites sont de : 
 * - stocker le BFS que la vague d'ennemis doit suivre
 * - stocker l'environnement de jeu de la vague
 * - creer un ennemi en fonction des ennemis deja ajoutes 
 * - ajouter un ennemi cree a l'environnement
 */

package byteDefense.model.enemies;

import byteDefense.factories.EnemyFactory;
import byteDefense.model.GameEnvironment;
import byteDefense.utilities.BFS;

public class WaveServices {

	private BFS bfsMap;
	private GameEnvironment gameEnv;
	
	public WaveServices(BFS bfsMap, GameEnvironment gameEnvironment) {
		this.bfsMap = bfsMap;
		this.gameEnv = gameEnvironment;
	}
	
	private Enemy createEnemy(Wave wave) throws Exception {
		WaveEnemy waveEnnemy = wave.getWaveEnemyRandomly();
		int enemyType = wave.getEnemyType(waveEnnemy);

		wave.updateWave(waveEnnemy);
		
		return EnemyFactory.getInstance(enemyType, this.bfsMap, this.gameEnv);
	}

	public void addNewEnemy(Wave wave) throws Exception {
		this.gameEnv.addEnemy(createEnemy(wave));
	}
}
