/*
 * PiercingDefenseEffect.java
 * Cette classe represente l'effet de perce de defense d'une tourelle par un Rootkit
 */

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
