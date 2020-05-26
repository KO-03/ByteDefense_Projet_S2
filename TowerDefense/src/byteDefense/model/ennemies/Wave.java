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
	
	public Wave(int waveNumber, BFS bfsMap) {
		this.ennemies = FXCollections.observableArrayList(); 
		this.bfsMap = bfsMap;
		this.indLastEnnemySpawn = 0;
		this.WAVE_NUMBER = waveNumber;
	}
	
	public int getIndLastEnnemySpawn() {
		return this.indLastEnnemySpawn;
	}
	
	public int waveEnnemyQuantity() {
		int ennemyQty = 0;
		
		for (int i = 0; i < waveInfo[WAVE_NUMBER].length; i++)
			ennemyQty += waveInfo[WAVE_NUMBER][i];
		
		return ennemyQty;
	}
	
	private Ennemy createEnnemy() {
		int indEnnemyType = 0, nbrAddedEnnemy = indLastEnnemySpawn;
		boolean t = false;
		Ennemy ennemy = null;
		
		while (!t && indEnnemyType < WAVE_NUMBER) {
			nbrAddedEnnemy -= waveInfo[WAVE_NUMBER][indEnnemyType];

			if (nbrAddedEnnemy < 0) 
				t = true;
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
	
	public void verifyEnnemi() {
		if(! this.isEmpty())
			for(int i = this.sizeOfEnnemies() - 1; i > 0; i--) {			
				Ennemy e = this.ennemies.get(i);
				if(! e.isAlive() || e.getX() == BFS.getX(bfsMap.ARRIVAL_POINT) && e.getY() == BFS.getY(bfsMap.ARRIVAL_POINT)) 
					this.ennemies.remove(i);
			}
	}
}
