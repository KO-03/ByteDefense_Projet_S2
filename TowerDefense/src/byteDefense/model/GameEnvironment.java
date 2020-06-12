/*
 * GameEnvironment.java
 * Cette classe represente l'environnement de jeu ou les objets de jeu et tirs evoluent, ses responsabilites sont de :
 * - stocker, recuperer et fixer la progression de l'infection (points de vie de l'ordinateur decremente au fil de 
 * 	ennemis qui atteignent le point d'arrive)
 * - stocker, recuperer la liste des ennemis
 * - stocker, recuperer la liste des tourelles
 * - stocker, recuperer la liste des tirs
 * - gerer les actions d'un tour (deplacement, mise a jour des effets infliges et de la progression de l'infection)
 * - gerer les attaques des tourelles et des ennemis de l'environnement
 * - gerer les tirs et leur actions
 * - verifier qu'une tuile est occupee ou non pour y placer une tourelle
 * - ajouter et supprimer un objet de jeu de chacune des listes de l'environnement
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

	private void removeEnemy(Enemy enemy) {
		this.enemies.remove(enemy);
	}
	
	// Fonction qui verifie si la liste des ennemis est vide ou non
	public boolean enemiesIsEmpty() {
		return this.enemies.size() == 0;
	}
	
	public void addTower(Tower tower) {
		this.towers.add(tower);
	}
	
	public void removeTower(Tower tower) {
		this.towers.remove(tower);
	}
	
	public void addBullet(Bullet bullet) {
		this.bullets.add(bullet);
	}
	
	private void removeBullet(Bullet bullet) {
		this.bullets.remove(bullet);
	}
	
	// Methode qui gere l'attaque des tirs
	private void bulletsAttack() {
		Bullet bullet;
		
		for (int i = this.bullets.size() - 1; i >= 0; i--) {
			bullet = this.bullets.get(i);
			
			// Retirer les points de vie de la cible si elle est toujours vivante
			if (bullet.targetIsAlive())
				bullet.reachTarget();
		}
	}
	
	// Methode qui gere la suppression des tourelles, ennemis et des tirs dans l'environnement 
	private void removeGameObject() {
		Enemy enemy;
		Tower tower;
		
		for (int i = this.bullets.size() - 1; i >= 0; i--)
			this.removeBullet(this.bullets.get(i));
		
		for (int i = this.enemies.size() - 1; i >= 0; i--) {
			enemy = this.enemies.get(i);

			// Si l'ennemi est mort, ou qu'il est arrive au bout du chemin, il est supprime
			if(!enemy.isAlive() || enemy.isArrived())
				this.removeEnemy(enemy);
		}
		
		for (int i = this.towers.size() - 1; i >= 0; i--) {
			tower = this.towers.get(i);

			// Si la tourelle est morte, elle est supprimee
			if(!tower.isAlive())
				this.removeTower(tower);
		}
	}
	
	// Methode qui gere les attaques des tourelles et des ennemis
	private void livingObjectAttack() {
		Enemy enemy;
		Tower tower;
		
		for (int i = this.enemies.size() - 1; i >= 0; i--) {
			enemy = this.enemies.get(i);
			
			// seul les ennemi "offensif" peuvent attaquer des tourelles
			if (enemy instanceof OffensiveEnemy)
				((OffensiveEnemy)enemy).attackTower();
		}
		
		for (int i = this.towers.size() - 1; i >= 0; i--) {
			tower = towers.get(i);
			
			// la tourelle peut attaquer lorsqu'elle n'est pas gelee
			if (!tower.getFrozen()) 
				tower.attackEnemy();
		}
	}
	
	// Methode qui regroupe les actions des gameObjects de l'environnement (
	public void gameEnvironmentAction() {
		this.bulletsAttack();
		this.removeGameObject();
		this.livingObjectAttack();
	}
	
	/* Methode qui gere les actions des ennemis durant un tour (mise a jour des effets ingliges, 
	 * mouvement des ennemis, mise a jour de la progression de l'infection) 
	 */
	private void enemiesTurn() {
		Enemy enemy;
		for (int i = 0; i < this.enemies.size(); i++) {
			enemy = this.enemies.get(i);
			enemy.moveEnnemy();
			enemy.updateInflictedEffects(); // Mise a jour des effets infliges a l'ennemi
			
			// l'ennemi a subit un enflamment
			if (enemy.getIgnited())
				enemy.receiveDamage(3);
			// la progression de l'infection est mise a jour lorsqu'un ennemi arrive au bout du chemin
			if (enemy.isArrived())
				this.setInfectingProgress(this.infectingProgress + enemy.getAttack());
		}
	}
	
	// Methode qui gere les actions des tourelles durant un tour (mise a jour des effets ingliges) 
	public void towersTurn() {
		for (int i = 0; i < this.towers.size(); i++)
			// Mise a jour des effets infliges a la tourelle
			this.towers.get(i).updateInflictedEffects(); 
	}
	
	/* Methode qui realise les actions des ennemis et des tourelles durant un tour
	 * (deplacemant, mise a jour des effets infliges et de la progression de l'infection)
	 */
	public void turnActions() {
		this.enemiesTurn();
		this.towersTurn();
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
