package byteDefense.model;

import byteDefense.model.ennemies.Ennemy;
import byteDefense.model.towers.Tower;
import byteDefense.utilities.BFS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameEnvironment {
	
	private ObservableList<GameObject> gameObjectsList;

	public GameEnvironment() {
		this.gameObjectsList = FXCollections.observableArrayList();
	}
	
	public void addGameObject(GameObject gameObject) {
		this.gameObjectsList.add(gameObject);
	}
	
	public void removeGameObject(GameObject gameObject) {
		this.gameObjectsList.remove(gameObject);
	}

	public ObservableList<GameObject> getGameObjectsList() {
		return this.gameObjectsList;
	}
	
	public GameObject getGameObject(int id) {
		for (GameObject go : this.gameObjectsList)
			if (go.getId() == id)
				return go;
		
		return null;
	}
	
	public void gameEnvironmentHandler(BFS bfs) {
		GameObject go;
		
		for (int i = this.gameObjectsList.size() - 1; i >= 0; i--) {
			go = gameObjectsList.get(i);

			go.act();
			if(!go.isAlive() || (go instanceof Ennemy && go.getCurrentIndTile() == bfs.ARRIVAL_POINT))
				this.removeGameObject(go);
		}
	}
}
