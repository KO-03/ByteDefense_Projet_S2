package byteDefense.model.effects;

import byteDefense.model.LivingObject;
import byteDefense.model.enemies.Spyware;

public class StealingStatsEffect extends SpecialEffect {
	
	public StealingStatsEffect(int timeEffect) {
		super(timeEffect);
	}
	
	public void endEffect(LivingObject livingObject) {
		((Spyware)livingObject).resetAttack();
		((Spyware)livingObject).resetAttackRange();
	}
}
