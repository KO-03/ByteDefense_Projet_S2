package byteDefense.factories;

import byteDefense.model.LivingObject;
import byteDefense.model.effects.FrozenEffect;
import byteDefense.model.effects.IncreasingAttackEffect;
import byteDefense.model.effects.SpecialEffect;
import byteDefense.model.enemies.Ransomware;
import byteDefense.model.enemies.Rootkit;

public class SpecialEffectFactory {

	public static SpecialEffect getInstance(LivingObject livingObject) {
		SpecialEffect specialEffect;
		
		if (livingObject instanceof Rootkit) 
			specialEffect = new IncreasingAttackEffect(2);
		/*else if (livingObject instanceof Adware)
			specialEffect = new SpecialEffect(2);
		else if (livingObject instanceof Bot)
			specialEffect = new SpecialEffect(2);*/
		else if (livingObject instanceof Ransomware)
			specialEffect = new FrozenEffect(2);
		/*else if (livingObject instanceof Spyware)
			specialEffect = new SpecialEffect(2);
		else if (livingObject instanceof TrojanHorse)
			specialEffect = new SpecialEffect(2);
		else if (livingObject instanceof AdCube)
			specialEffect = new SpecialEffect(2);
		else if (livingObject instanceof Antivirus)
			specialEffect = new SpecialEffect(2);
		else if (livingObject instanceof AuthenticationPoint)
			specialEffect = new SpecialEffect(2);
		else if (livingObject instanceof Firewall)
			specialEffect = new SpecialEffect(2);
		else if (livingObject instanceof SudVPN)
			specialEffect = new SpecialEffect(2);*/
		else 
			specialEffect  = null;
		
		return specialEffect;
	}
}
