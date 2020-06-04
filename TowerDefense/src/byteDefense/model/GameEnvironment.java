package byteDefense.model;

import byteDefense.model.ennemies.Ennemy;
import byteDefense.utilities.BFS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameEnvironment {
	
	private ObservableList<GameObject> gameObjectsList;
	private ObservableList<Bullet> bullets;

	public GameEnvironment() {
		this.gameObjectsList = FXCollections.observableArrayList();
		this.bullets = FXCollections.observableArrayList();
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
	
	public void addBullet(Bullet bullet) {
		this.bullets.add(bullet);
	}
	
	public void removeBullet(Bullet bullet) {
		this.bullets.remove(bullet);
	}
	
	public ObservableList<Bullet> getBullets() {
		return this.bullets;
	}
	
	public void gameObjectsHandler(BFS bfs) {
		GameObject go;
		
		for (int i = this.gameObjectsList.size() - 1; i >= 0; i--) {
			go = gameObjectsList.get(i);

			go.actSpecific();
			if(!go.isAlive() || (go instanceof Ennemy && go.getCurrentIndTile() == -1))
				this.removeGameObject(go);
		}
	}
	
	public void bulletsHandler() {
		Bullet bullet;
		
		for (int i = 0; i < this.bullets.size(); i++) {
			bullet = this.bullets.get(i);
			
			if (bullet.isArrived()) {
				bullet.damaged();
				this.removeBullet(bullet);
			} else
				bullet.update();
		}
	}
}
