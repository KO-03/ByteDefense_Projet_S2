/*
 * IncreasingAttackEffect
 * Cette classe represente l'effet d'augmentation de l'attaque d'un SudVPN
 */

package byteDefense.model.effects;

import byteDefense.model.LivingObject;
import byteDefense.model.towers.SudVPN;

public class IncreasingAttackEffect extends SpecialEffect {
	
	public IncreasingAttackEffect(int timeEffect) {
		super(timeEffect);
	}
	
	public void endEffect(LivingObject livingObject) {
		((SudVPN)livingObject).resetAttack();
	}
}
