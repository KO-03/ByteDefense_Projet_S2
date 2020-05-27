/*
 * Tower.java
 * Cette classe represente un objet Tower. ses responsabilites sont de :
 * - identifier la tourelle par un identifiant recuperable
 * - recuperer les coordonnees xy d'une tourelle
 * - recuperer et de decrementer les points de vie d'une tourelle
 * - verifier que la tourelle est mort ou non
 * - recuperer les caracteristiques de la tourelle (points d'attaque, point 
 *   de defense, vitesse d'attaque, portee d'attaque et le cout)
 */

package byteDefense.model.towers;

import byteDefense.model.GameObject;

public abstract class Tower extends GameObject {
	
	public Tower(int x, int y) {
		super(x, y);
	}
	
	public abstract void act();
}
