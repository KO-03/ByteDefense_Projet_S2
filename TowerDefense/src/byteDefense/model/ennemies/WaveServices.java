/*
 * WaveServices.java
 * Cette classe gere les services/actions lies a la vague d'ennemis, ses responsabilites sont de : 
 * - stocker et recuperer la liste d'ennemis
 * - stocker le BFS que la vague d'ennemi doit suivre
 * - remplir la liste d'ennemi en fonction des ennemis deja ajoutes
 * - gerer les suppression d'ennemis
 * - recuperer un ennemi selon son identifiant
 * - verifier si la liste d'ennemis est pas vide
 * - recuperer la taille de la liste d'ennemis
 * - gerer toutes les actions faites sur la vague d'ennemis (ajout d'ennemis 
 *   a la liste d'ennemis, agissement des ennemis, suppression d'ennemis...)
 */

package byteDefense.model.ennemies;

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
	
	private void addEnnemy(Ennemy ennemy) {
		this.gameEnv.addGameObject(ennemy);
	}
	
	private Ennemy createEnnemy(Wave wave) {
		int ennemyType = wave.getEnnemyType();
		
		wave.decrementEnnemyQty();
		if (wave.getEnnemiesInfos().peek().everyEnnemiesSpawned())
			wave.removeWaveEntity();

		return EnnemyFactory.getInstance(ennemyType, this.bfsMap, this.gameEnv);
	}

	public void fillEnnemyList(Wave wave) {
		Ennemy ennemy = createEnnemy(wave); 

		if (ennemy != null)
			addEnnemy(ennemy);
	}

	public void removeEnnemy(Ennemy e) {
		this.gameEnv.removeGameObject(e);
	}
	/*
	public void waveHandler(Wave wave) {
		// Ajout d'un ennemi a la vague lorsqu'ils n'ont pas tous ete ajoutes
		if (!wave.isEmpty())
			this.fillEnnemyList(wave);

		Ennemy e;
		
		for (int i = this.gameEnv.getGameObjectsList().size() - 1; i >= 0; i--) {
			e = this.gameEnv.getGameObjectsList().get(i);
			// fait agir chaque ennemi tant qu'il n'est pas arrive au bout du chemin
			if (e instanceof Ennemy && e.getCurrentIndTile() > this.bfsMap.ARRIVAL_POINT)
				e.act();
			else if (e.getCurrentIndTile() == this.bfsMap.ARRIVAL_POINT || !e.isAlive())
				this.removeEnnemy(e);
		}
	}*/
}
