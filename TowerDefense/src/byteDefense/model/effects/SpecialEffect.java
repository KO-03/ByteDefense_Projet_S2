/*
 * SpecialEffect.java
 * Cette classe represente un effet special d'une tourelle ou d'un ennemi, ses responsabilites sont de :
 * - stocker la duree d'un effet
 * - stocker, recuperer et decrementer le nombre de tour restants avant la dissipation de l'effet
 * - stocker, recuperer et faire evoluer l'etat de l'effet (active ou non)
 * - reinitialiser un effet (desactiver l'effet et remettre son nombre de tour restants a la duree de l'effet initiale)
 * - mettre fin a l'effet sur un livingObject
 */

package byteDefense.model.effects;

import byteDefense.model.LivingObject;

public abstract class SpecialEffect {

	private final int TIME_EFFECT; // duree d'un effet
	private int turnNbr; // nombre de tour restants avant la dissipation de l'effet
	private boolean activated; // indique si l'effet est activer
	
	public SpecialEffect(int timeEffect) {
		this.TIME_EFFECT = timeEffect;
		this.turnNbr = this.TIME_EFFECT;
		this.activated = false;
	}
	
	public boolean getActivated() {
		return this.activated;
	}
	
	public int getTurnNbr() {
		return this.turnNbr;
	}
	
	public void changeActivated() {
		if (this.activated)
			this.activated = false;
		else
			this.activated = true;
	}
	
	public void decrementTurnNbr() {
		this.turnNbr--;
	}
	
	// Methode qui reinitialise un effet (desactiver l'effet et remettre son nombre de tour restants a la duree de l'effet initiale)
	public void reinitializeEffect() {
		this.changeActivated();
		this.turnNbr = TIME_EFFECT;
	}
	
	public abstract void endEffect(LivingObject livingObject);
}
