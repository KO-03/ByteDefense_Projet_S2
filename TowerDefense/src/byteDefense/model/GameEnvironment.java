/*
 * GameEnvironment.java
 * Cette classe represente l'environnement de jeu ou les objets de jeu et tirs evoluent, ses responsabilites sont de :
 * - stocker, recuperer la liste des objets de jeu et gerer les actions de ses gameObjects
 * - ajouter et supprimer un objet de jeu a la gameObjectList
 * - recuperer un objet de jeu a la liste de gameObjects a partir de son identifiant
 * - stocker, recuperer la liste des tirs et gerer les actions de ses tirs
 * - ajouter et supprimer un tir de la liste des tirs
 */

package byteDefense.model;

import byteDefense.model.enemies.Enemy;
import byteDefense.model.enemies.OffensiveEnemy;
import byteDefense.model.towers.Tower;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameEnvironment {
	
	private ObservableList<Enemy> enemies;
	private ObservableList<Tower> towers;
	private ObservableList<Bullet> bullets;

	public GameEnvironment() {
		this.enemies = FXCollections.observableArrayList();
		this.towers = FXCollections.observableArrayList();
		this.bullets = FXCollections.observableArrayList();
	}
	
	public ObservableList<Enemy> getEnemies() {
		return this.enemies;
	}
	
	public void addEnemy(Enemy enemy) {
		this.enemies.add(enemy);
	}

	public ObservableList<Tower> getTowers() {
		return this.towers;
	}
	
	public void addTower(Tower tower) {
		this.towers.add(tower);
	}
	
	public void removeLivingObject() {
		Enemy enemy;
		Tower tower;
		
		for (int i = this.enemies.size() - 1; i >= 0; i--) {
			enemy = this.enemies.get(i);

			// Si l'objet de jeu est mort, ou qu'il est arrive au bout du chemin, il est supprime
			if(!enemy.isAlive() || enemy.isArrived())
				this.enemies.remove(enemy);
		}
		
		for (int i = this.towers.size() - 1; i >= 0; i--) {
			tower = this.towers.get(i);

			// Si l'objet de jeu est mort, il est supprime
			if(!tower.isAlive())
				this.towers.remove(tower);
		}
	}
	
	public void livingObjectAttack() {
		for (int i = this.enemies.size() - 1; i >= 0; i--) {
			Enemy enemy = this.enemies.get(i);
			if (enemy instanceof OffensiveEnemy)
			((OffensiveEnemy)enemy).attack();
		}
		
		for (int i = this.towers.size() - 1; i >= 0; i--) {
			this.towers.get(i).attack();
		}
	}
	
	public void enemiesMove() {
		for (int i = this.enemies.size() - 1; i >= 0; i--) {
			this.enemies.get(i).move();
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
	
	public void bulletsAction() {
		Bullet bullet;
		
		for (int i = this.bullets.size() - 1; i >= 0; i--) {
			bullet = this.bullets.get(i);
			
			// Retirer les points de vie a la cible si elle est toujours vivante
			if (bullet.getTargetObject().isAlive())
				bullet.attackTarget();
			this.removeBullet(bullet);
		}
	}
	
	public void gameAction() {
		this.bulletsAction();
    	this.removeLivingObject();
		this.livingObjectAttack();
	}
}
