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
import byteDefense.utilities.BFS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WaveServices {

	private ObservableList<Ennemy> ennemies;
	private BFS bfsMap;

	public WaveServices(BFS bfsMap) {
		this.ennemies = FXCollections.observableArrayList();
		this.bfsMap = bfsMap;
	}
	
	private void addEnnemy(Ennemy ennemy) {
		this.ennemies.add(ennemy);
	}
	
	private Ennemy createEnnemy(Wave wave) {
		int ennemyType = wave.getEnnemyType();
		
		wave.decrementEnnemyQty();
		if (wave.getEnnemiesInfos().peek().everyEnnemiesSpawned())
			wave.removeWaveEntity();

		return EnnemyFactory.getInstance(ennemyType, bfsMap);
	}

	public void fillEnnemyList(Wave wave) {
		Ennemy ennemy = createEnnemy(wave); 

		if (ennemy != null) 
			addEnnemy(ennemy);
	}

	public void removeEnnemy(Ennemy e) {
		this.ennemies.remove(e);
	}

	public ObservableList<Ennemy> getEnnemies() {
		return this.ennemies;
	}

	public Ennemy getEnnemy(int id) {
		for(Ennemy e : this.ennemies)
			if(e.getId() == id)
				return e;

		return null;
	}

	public boolean isEmpty() {
		return this.ennemies.size() <= 0;
	}

	public int sizeOfEnnemies() {
		return this.ennemies.size();
	}

	public void waveHandler(Wave wave) {
		// Ajout d'un ennemi a la vague lorsqu'ils n'ont pas tous ete ajoutes
		if (!wave.isEmpty())
			this.fillEnnemyList(wave);

		if(! this.isEmpty()) {
			Ennemy e;
			
			for (int i = this.sizeOfEnnemies() - 1; i >= 0; i--) {
				e = this.getEnnemies().get(i);
				// fait agir chaque ennemi tant qu'il n'est pas arrive au bout du chemin
				if (e.getCurrentIndTile() > this.bfsMap.ARRIVAL_POINT)
					e.act();
				else if (e.getCurrentIndTile() == this.bfsMap.ARRIVAL_POINT || !e.isAlive())
					this.removeEnnemy(e);
			}
		}
	}
}
