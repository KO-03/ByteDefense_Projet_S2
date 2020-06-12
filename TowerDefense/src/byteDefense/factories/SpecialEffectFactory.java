/*
 * EnemyFactory.java
 * Cette classe gere la fabrication d'instance d'SpecialEffect (un effet), sa responsabilite est de :
 * - Creer un effet en fonction d'un livingObject (tourelle ou ennemi) donne
 */

package byteDefense.factories;

import byteDefense.model.LivingObject;
import byteDefense.model.effects.AuthenticationLinkEffect;
import byteDefense.model.effects.FrozenEffect;
import byteDefense.model.effects.IgniteEffect;
import byteDefense.model.effects.IncreasingAttackEffect;
import byteDefense.model.effects.PiercingDefenseEffect;
import byteDefense.model.effects.SniperEffect;
import byteDefense.model.effects.SpecialEffect;
import byteDefense.model.effects.StealingStatsEffect;
import byteDefense.model.enemies.Bot;
import byteDefense.model.enemies.Ransomware;
import byteDefense.model.enemies.Rootkit;
import byteDefense.model.enemies.Spyware;
import byteDefense.model.towers.AuthenticationPoint;
import byteDefense.model.towers.Firewall;
import byteDefense.model.towers.SudVPN;

public class SpecialEffectFactory {

	public static SpecialEffect getInstance(LivingObject livingObject) {
		SpecialEffect specialEffect;
		
		if (livingObject instanceof Rootkit) 
			specialEffect = new PiercingDefenseEffect(3);
		else if (livingObject instanceof Bot)
			specialEffect = new SniperEffect(4);
		else if (livingObject instanceof Ransomware)
			specialEffect = new FrozenEffect(2);
		else if (livingObject instanceof Spyware)
			specialEffect = new StealingStatsEffect(3);
		else if (livingObject instanceof AuthenticationPoint)
			specialEffect = new AuthenticationLinkEffect(2);
		else if (livingObject instanceof Firewall)
			specialEffect = new IgniteEffect(5);
		else if (livingObject instanceof SudVPN)
			specialEffect = new IncreasingAttackEffect(2);
		else 
			specialEffect  = null;
		
		return specialEffect;
	}
}
