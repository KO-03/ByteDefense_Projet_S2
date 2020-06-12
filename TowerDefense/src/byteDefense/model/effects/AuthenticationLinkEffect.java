package byteDefense.model.effects;

import byteDefense.model.LivingObject;
import byteDefense.model.towers.AuthenticationPoint;

public class AuthenticationLinkEffect extends SpecialEffect {
	
	public AuthenticationLinkEffect(int timeEffect) {
		super(timeEffect);
	}
	
	public void endEffect(LivingObject livingObject) {
		((AuthenticationPoint)livingObject).resetAttack();
	}
}
