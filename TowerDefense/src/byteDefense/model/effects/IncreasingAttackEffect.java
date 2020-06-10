package byteDefense.model.effects;

import byteDefense.model.LivingObject;
import byteDefense.model.enemies.Rootkit;

public class IncreasingAttackEffect extends SpecialEffect {

	public IncreasingAttackEffect(int timeEffect) {
		super(timeEffect);
	}
	
	public void endEffect(LivingObject livingObject) {
		((Rootkit)livingObject).setAttack(Rootkit.INTIAL_ATTACK);
	}
}
