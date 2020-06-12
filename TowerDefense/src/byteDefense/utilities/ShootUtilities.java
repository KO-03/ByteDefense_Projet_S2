/*
 * ShootUtilites.java
 * Cette classe utilitaire permet de realiser les actions necessaires pour faire tirer un tireur (ennemi ou tourelle), 
 * ses responsabilites sont de :
 * - faire tirer un tireur sur une cible
 * - verifier si une cible est a la portee du tir d'un tireur
 * - recuperer la cible autour du tireur s'il y en a une a sa portee
 */

package byteDefense.utilities;

import byteDefense.model.Bullet;
import byteDefense.model.GameArea;
import byteDefense.model.GameEnvironment;
import byteDefense.model.LivingObject;
import javafx.collections.ObservableList;

public class ShootUtilities {
	
	// Methode qui fait tirer un tireur sur une cible, c'est-a-dire qui ajoute un tir a son environnement de jeu
	public static void shoot(LivingObject shooter, LivingObject target, GameEnvironment gameEnv) {
		gameEnv.addBullet(new Bullet(target, shooter));
	}
	
	// Fonction qui retourne ou non la cible du tireur en fonction de leur position et portee d'attaque
	public static LivingObject checkTargetPosition(LivingObject target, LivingObject shooter) {
		int tileSize = GameArea.TILE_SIZE; // taille d'une tuile du plateau de jeu
		int attackRange = shooter.getAttackRange(); // portee d'attaque du tireur
		
		// La cible est a la portee de l'attaque du tireur
		if ((shooter.getY() - attackRange * tileSize <= target.getY() 
			&& target.getY() <= shooter.getY() + attackRange * tileSize) 
			&& (shooter.getX() - attackRange * tileSize <= target.getX() 
			&& target.getX() <= shooter.getX() + attackRange * tileSize)) {
			return target;
		}
		return null;
	}
	
	// Fonction qui retourne une cible autour du tireur 
	public static LivingObject findTarget(ObservableList<? extends LivingObject> livingObjectList, LivingObject shooter) {
		LivingObject target = null;
		int i = 0;
		
		while (i < livingObjectList.size() && target == null) {
			target = ShootUtilities.checkTargetPosition(livingObjectList.get(i), shooter);
			i++;
		}
		return target;
	}
}