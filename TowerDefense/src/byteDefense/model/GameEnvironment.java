/*
 * GameEnvironment.java
 * Cette classe represente l'environnemnt de jeu ou les objets de jeu et tirs evoluent, ses responsabilites sont de :
 * - stocker, recuperer la liste des objets de jeu et gerer les actions de ses gameObjects
 * - ajouter et supprimer un objet de jeu a la gameObjectList
 * - recuperer un objet de jeu a la liste de gameObjects a partir de son identifiant
 * - stocker, recuperer la liste des tirs et gerer les actions de ses tirs
 * - ajouter et supprimer un tir de la liste des tirs
 */

package byteDefense.model;

import byteDefense.model.enemies.Ennemy;
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
	
	public ObservableList<GameObject> getGameObjectsList() {
		return this.gameObjectsList;
	}
	
	public void addGameObject(GameObject gameObject) {
		this.gameObjectsList.add(gameObject);
	}
	
	private void removeGameObject(GameObject gameObject) {
		this.gameObjectsList.remove(gameObject);
	}
	
	public GameObject getGameObject(int id) {
		for (GameObject go : this.gameObjectsList)
			if (go.getId() == id)
				return go;
		
		return null;
	}
	
	public void gameObjectsHandler(BFS bfs) {
		GameObject go;
		
		for (int i = this.gameObjectsList.size() - 1; i >= 0; i--) {
			go = gameObjectsList.get(i);

			go.act();
			// Si l'objet de jeu est mort, ou qu'il est un ennemi et qu'il est arrive au bout du chemin, il est supprime
			if(!go.isAlive() || (go instanceof Ennemy && go.getCurrentIndTile() == -1))
				this.removeGameObject(go);
		}
	}
	
	public ObservableList<Bullet> getBullets() {
		return this.bullets;
	}
	
	public void addBullet(Bullet bullet) {
		this.bullets.add(bullet);
	}
	
	private void removeBullet(Bullet bullet) {
		this.bullets.remove(bullet);
	}
	
	public void bulletsHandler() {
		Bullet bullet;
		
		for (int i = this.bullets.size() - 1; i >= 0; i--) {
			bullet = this.bullets.get(i);
			
			this.removeBullet(bullet);
			// Retirer les points de vie a la cible si elle est toujours vivante
			if (bullet.getTargetObject().isAlive())
				bullet.woundTarget();
		}
	}
}
