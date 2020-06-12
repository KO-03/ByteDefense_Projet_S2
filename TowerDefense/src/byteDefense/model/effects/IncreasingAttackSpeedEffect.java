package byteDefense.model.effects;

import byteDefense.model.LivingObject;
import byteDefense.model.enemies.Spyware;

public class IncreasingAttackSpeedEffect extends SpecialEffect {
	
	public IncreasingAttackSpeedEffect(int timeEffect) {
		super(timeEffect);
	}
	
	public void endEffect(LivingObject livingObject) {
		((Spyware)livingObject).resetAttackRange();
	}
}
