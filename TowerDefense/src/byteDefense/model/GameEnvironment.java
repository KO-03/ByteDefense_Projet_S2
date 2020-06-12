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
	
	private int infectingProgress; // progression de l'infection
	private ObservableList<Enemy> enemies; // liste des ennemis
	private ObservableList<Tower> towers; // liste des tourelles
	private ObservableList<Bullet> bullets; // liste des tirs des ennemis et tourelles

	public GameEnvironment() {
		this.infectingProgress = 0;
		this.enemies = FXCollections.observableArrayList();
		this.towers = FXCollections.observableArrayList();
		this.bullets = FXCollections.observableArrayList();
	}

	public int getInfectingProgress() {
		return this.infectingProgress;
	}
	
	public ObservableList<Enemy> getEnemies() {
		return this.enemies;
	}
	
	public ObservableList<Tower> getTowers() {
		return this.towers;
	}
	
	public ObservableList<Bullet> getBullets() {
		return this.bullets;
	}
	
	private void setInfectingProgress(int remainingHp) {
		this.infectingProgress = remainingHp;
	}
	
	public void addEnemy(Enemy enemy) {
		this.enemies.add(enemy);
	}

	public boolean enemiesIsEmpty() {
		return this.enemies.size() == 0;
	}
	
	public void addTower(Tower tower) {
		this.towers.add(tower);
	}
	
	public void addBullet(Bullet bullet) {
		this.bullets.add(bullet);
	}
	
	private void removeBullet(Bullet bullet) {
		this.bullets.remove(bullet);
	}
	
	// Methode qui regroupe les actions des tirs (attaque et suppression)
	private void bulletsAction() {
		Bullet bullet;
		
		for (int i = this.bullets.size() - 1; i >= 0; i--) {
			bullet = this.bullets.get(i);
			
			// Retirer les points de vie de la cible si elle est toujours vivante
			if (bullet.targetIsAlive())
				bullet.attackTarget();
			
			this.removeBullet(bullet);
		}
	}
	
	// Methode qui gere la suppression des tourelles et ennemis dans l'environnement 
	private void removeLivingObject() {
		Enemy enemy;
		Tower tower;
		
		for (int i = this.enemies.size() - 1; i >= 0; i--) {
			enemy = this.enemies.get(i);

			// Si l'ennemi est mort, ou qu'il est arrive au bout du chemin, il est supprime
			if(!enemy.isAlive() || enemy.isArrived())
				this.enemies.remove(enemy);
		}
		
		for (int i = this.towers.size() - 1; i >= 0; i--) {
			tower = this.towers.get(i);

			// Si la tourelle est morte, elle est supprimee
			if(!tower.isAlive())
				this.towers.remove(tower);
		}
	}
	
	// Methode qui gere les attaques des tourelles et des ennemis
	private void livingObjectAttack() {
		Enemy enemy;
		Tower tower;
		
		for (int i = this.enemies.size() - 1; i >= 0; i--) {
			enemy = this.enemies.get(i);
			
			if (enemy instanceof OffensiveEnemy)
				((OffensiveEnemy)enemy).attackTower();
		}
		
		for (int i = this.towers.size() - 1; i >= 0; i--) {
			tower = towers.get(i);
			
			if (!tower.getFrozen()) // la tourelle peut attaquer lorsqu'elle n'est pas gelee
				tower.attackEnemy();
		}
	}
	
	// Methode qui regroupe les actions sur les LivingObjects et les tirs
	public void gameEnvironmentAction() {
		this.bulletsAction();
    	this.removeLivingObject();
		this.livingObjectAttack();
	}
	
	/* Methode qui gere les actions des ennemis durant un tour (mise a jour des effets ingliges, 
	 * mouvement des ennemis, mise a jour de la progression de l'infection) 
	 */
	public void enemiesTurn() {
		Enemy enemy;
		for (int i = 0; i < this.enemies.size(); i++) {
			enemy = this.enemies.get(i);
			enemy.moveEnnemy();
			enemy.updateInflictedEffects(); // Mise a jour des effets infliges a l'ennemi
			if (enemy.getIgnited())
				enemy.receiveDamage(3);
			if (enemy.isArrived())
				this.setInfectingProgress(this.infectingProgress + enemy.getAttack());
		}
	}
	
	// Methode qui gere les actions des tourelles durant un tour (mise a jour des effets ingliges) 
	public void towersTurn() {
		for (int i = 0; i < this.towers.size(); i++) {
			this.towers.get(i).updateInflictedEffects(); // Mise a jour des effets infliges a la tourelle
			//System.out.println(towers.get(i));
		}
	}
	
	// Fonction qui verifie si la tuile a des coordonnes xy donnees est occupee par une tourelle ou pas 
	public boolean checkTowerPosition(int x, int y) {
		int tileSize = GameArea.TILE_SIZE; // taille d'une tuile dans le plateau de jeu
		
		for (Tower tower: this.towers)
			if (tower.getX() / tileSize * tileSize == x && tower.getY() / tileSize * tileSize == y)
				return true;

		return false;
	}
}
