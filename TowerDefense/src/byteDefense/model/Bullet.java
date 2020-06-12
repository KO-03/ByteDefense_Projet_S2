/*
 * Bullet.java
 * Cette classe represente un objet Bullet (un tir), ses responsabilites sont de :
 * - stocker la cible du tir
 * - stocker et recuperer le tireur 
 * - recuperer les coordonnees de la cible du tir
 * - recuperer les coordonnees du tireur
 * - verifier si la cible est en vie
 * - appliquer les actions lorsque le tir atteint la cible 
 *   (application de l'effet du tireur et blesser la cible)
 */

package byteDefense.model;

public class Bullet extends GameObject {
	
	private LivingObject target; // cible du tir
	private LivingObject shooter; // tireur

	public Bullet(LivingObject target, LivingObject shooter) {
		this.target = target;
		this.shooter = shooter;
	}
	
	public LivingObject getShooterObject() {
		return this.shooter;
	}
	
	public int getTargetX() {
		return this.target.getX();
	}

	public int getTargetY() {
		return this.target.getY();
	}
	
	public int getShooterX() {
		return this.shooter.getX();
	}
	
	public int getShooterY() {
		return this.shooter.getY();
	}
	
	// Fonction qui verifie si la cible est en vie ou non
	public boolean targetIsAlive() {
		return this.target.isAlive();
	}

	// Methode qui permet d'appliquer l'effet du tireur et blesser la cible
	public void reachTarget() {
		this.shooter.useSpecialEffect(this.target);
		this.target.receiveDamage(this.shooter.getAttack());
	}
}
