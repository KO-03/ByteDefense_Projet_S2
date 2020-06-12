package byteDefense.model.effects;

import byteDefense.model.LivingObject;
import byteDefense.model.enemies.Enemy;

public class IgniteEffect extends SpecialEffect {
	
	public IgniteEffect(int timeEffect) {
		super(timeEffect);
	}
	
	public void endEffect(LivingObject livingObject) {
		((Enemy)livingObject).changeIgnited();
	}
}
