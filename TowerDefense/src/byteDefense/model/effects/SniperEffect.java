/*
 * SniperEffect.java
 * Cette classe represent l'effet d'augmentation de la portee d'attaque d'un Bot
 */

package byteDefense.model.effects;

import byteDefense.model.LivingObject;
import byteDefense.model.enemies.Bot;

public class SniperEffect extends SpecialEffect {
	
	public SniperEffect(int timeEffect) {
		super(timeEffect);
	}
	
	public void endEffect(LivingObject livingObject) {
		((Bot)livingObject).resetAttackRange();
	}
}
