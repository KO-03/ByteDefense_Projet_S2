package byteDefense.model;

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
}
