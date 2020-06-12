/*
 * FrozenEffect.java
 * Cette classe represente l'effet de gele d'une tourelle engendre par un Ransomware
 */

package byteDefense.model.effects;

import byteDefense.model.LivingObject;
import byteDefense.model.towers.Tower;

public class FrozenEffect extends SpecialEffect {
	
	public FrozenEffect(int timeEffect) {
		super(timeEffect);
	}
	
	public void endEffect(LivingObject livingObject) {
		((Tower)livingObject).changeFrozen();
	}
}
