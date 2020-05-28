/*
 * Wave.java
 * Cette classe represente un objet Wave, ses responsabilites sont de : 
 * - stocker et recuperer la liste d'ennemis
 * - stocker le BFS que la vague d'ennemi doit suivre
 * - stocker l'indice du derniere ennemi qui est ajoute a la vague
 * - stocker le numero de la vague dans un niveau du jeu
 * - remplir la liste d'ennemi en fonction des ennemis deja ajoutes
 * - calculer et stocker le nombre d'ennemis selon le numero de la vague
 * - gerer les suppression d'ennemis
 * - recuperer un ennemi selon son identifiant
 * - verifier si la liste d'ennemis est pas vide
 * - recuperer la taille de la liste d'ennemis
 * - supprimer un ennemi de la vague en verifiant son etat et sa position
 * - gerer toutes les actions faites sur la vague d'ennemis.
 */

package byteDefense.model.ennemies;

import byteDefense.utilities.BFS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Wave {
	
	private static int[][] waveInfo = {{5, 5}, {5, 3, 2}, {5, 3, 2, 3}, {4, 3, 2, 3, 2}, {4, 3, 3, 3, 2, 1}};
	
	private ObservableList<Ennemy> ennemies;
	private BFS bfsMap;
	private int indLastEnnemySpawn;
	private final int WAVE_NUMBER;
	private final int WAVE_ENNEMIES_QTY;
	
	public Wave(int waveNumber, BFS bfsMap) {
		this.ennemies = FXCollections.observableArrayList(); 
		this.bfsMap = bfsMap;
		this.indLastEnnemySpawn = 0;
		this.WAVE_NUMBER = waveNumber;
		this.WAVE_ENNEMIES_QTY = waveEnnemyQuantity();
	}
	
	public int getIndLastEnnemySpawn() {
		return this.indLastEnnemySpawn;
	}
	
	public int getWaveEnnemiesQty() {
		return this.WAVE_ENNEMIES_QTY;
	}
	
	public int waveEnnemyQuantity() {
		int ennemyQty = 0;
		
		for (int i = 0; i < waveInfo[WAVE_NUMBER].length; i++)
			ennemyQty += waveInfo[WAVE_NUMBER][i];
		
		return ennemyQty;
	}
	
	private Ennemy createEnnemy() {
		int indEnnemyType = 0, ennemyAddedDifference = indLastEnnemySpawn;
		boolean foundCorrectEnnemyType = false;
		Ennemy ennemy = null;
		
		while (!foundCorrectEnnemyType && indEnnemyType < WAVE_NUMBER) {
			ennemyAddedDifference -= waveInfo[WAVE_NUMBER][indEnnemyType];

			if (ennemyAddedDifference < 0) 
				foundCorrectEnnemyType = true;
			else
				indEnnemyType++;
		}
		
		switch (indEnnemyType + 1) {
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
				break;
		}
		
		return ennemy;
	}
	
	private void addEnnemy(Ennemy ennemy) {
		this.ennemies.add(ennemy);
	}
	
	public void fillEnnemyList() {
		Ennemy ennemy = createEnnemy(); 
		
		if (ennemy != null) {
			addEnnemy(ennemy);
			indLastEnnemySpawn++;
		}
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
	
	public void verifyEnnemyState(Ennemy e) {
		if(! e.isAlive() || e.getcurrentIndTile() == this.bfsMap.ARRIVAL_POINT)
			this.removeEnnemy(e);
	}
	
	public void waveHandler() {
		// Ajout d'un ennemi a la vague lorsqu'ils n'ont pas tous ete ajoute
		if (this.indLastEnnemySpawn < this.WAVE_ENNEMIES_QTY)
			this.fillEnnemyList();
			
		if(! this.isEmpty()) {
			Ennemy e;
			
			for (int i = this.sizeOfEnnemies() - 1; i >= 0; i--) {
				e = this.getEnnemies().get(i);
				// fait agir chaque ennemi tant qu'il n'est pas arrive au bout du chemin
				if (e.getcurrentIndTile() > this.bfsMap.ARRIVAL_POINT)
					e.act();
				
				this.verifyEnnemyState(e);
			}
		}
	}
}
