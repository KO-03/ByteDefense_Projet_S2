/*
 * Bullet.java
 * Cette classe represente un objet Bullet (un tir), ses responsabilites sont de :
 * - stocker, recuperer et fixer les coordonnees xy du tir
 * - stocker et recuperer la cible du tir
 * - stocker le tireur 
 * - identifier un tir par un identifiant recuperable
 * - stocker et recuperer les coordonnees de la cible du tir
 * - blesser la cible selon les degats du tireur
 */

package byteDefense.model;

public class Bullet extends GameObject {
	
	private LivingObject targetObject;
	private LivingObject shooterObject;
	private int targetX;
	private int targetY;

	public Bullet(int x, int y, LivingObject targetObject, LivingObject shooterObject) {
		super(x, y);
		this.targetObject = targetObject;
		this.shooterObject = shooterObject;
		this.targetX = this.targetObject.getX();
		this.targetY = this.targetObject.getY();
	}

	public LivingObject getTargetObject() {
		return this.targetObject;
	}
	
	public LivingObject getShooterObject() {
		return this.shooterObject;
	}
	
	public int getTargetX() {
		return this.targetX;
	}

	public int getTargetY() {
		return this.targetY;
	}
	
	public void attackTarget() {
		this.targetObject.decrementHp((int) this.shooterObject.getAttack());
	}

}
