package byteDefense.model.effects;

import byteDefense.model.LivingObject;

public abstract class SpecialEffect {

	private final int TIME_EFFECT;  
	private int turnNbr;
	private boolean activated;
	
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
	
	public void reinitializeEffect() {
		this.changeActivated();
		this.turnNbr = TIME_EFFECT;
	}
	
	public abstract void endEffect(LivingObject livingObject);
}
