/*
 * Bullet.java
 * Cette classe represente un objet Bullet (un tir), ses responsabilites sont de :
 * - stocker la cible du tir
 * - stocker et recuperer le tireur 
 * - recuperer les coordonnees de la cible du tir
 * - blesser la cible selon les degats du tireur
 */

package byteDefense.model;

public class Bullet extends GameObject {
	
	private LivingObject targetObject;
	private LivingObject shooterObject;

	public Bullet(int x, int y, LivingObject targetObject, LivingObject shooterObject) {
		super(x, y);
		this.targetObject = targetObject;
		this.shooterObject = shooterObject;
	}
	
	public boolean targetIsAlive() {
		return this.targetObject.isAlive();
	}
	
	public LivingObject getShooterObject() {
		return this.shooterObject;
	}
	
	public int getTargetX() {
		return this.targetObject.getX();
	}

	public int getTargetY() {
		return this.targetObject.getY();
	}

	public void attackTarget() {
		this.shooterObject.useSpecialEffect(targetObject);
		this.targetObject.setHp(this.targetObject.getHp() - this.shooterObject.getAttack());
	}
}
