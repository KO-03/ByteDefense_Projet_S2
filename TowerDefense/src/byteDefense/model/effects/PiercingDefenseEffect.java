package byteDefense.model.effects;

import byteDefense.model.LivingObject;
import byteDefense.model.enemies.Rootkit;

public class PiercingDefenseEffect extends SpecialEffect {
	
	public PiercingDefenseEffect(int timeEffect) {
		super(timeEffect);
	}
	
	public void endEffect(LivingObject livingObject) {
		((Rootkit)livingObject).resetAttack();
	}
}
