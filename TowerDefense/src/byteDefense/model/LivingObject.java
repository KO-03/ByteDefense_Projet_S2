/*
 * LivingObject.java
 * Cette classe parent represente un objet "vivant" du jeu (un ennemi ou une tourelle), ses responsabilites sont de :
 * - stocker, recuperer et decrementer ses points de vie 
 * - stocker et recuperer son environnement de jeu
 * - verifier s'il est mort ou non
 * - recuperer ses caracteristiques (points d'attaque, points de defense, vitesse d'attaque, portee d'attaque)
 */

package byteDefense.model;

import java.util.ArrayList;

import byteDefense.factories.SpecialEffectFactory;
import byteDefense.model.effects.SpecialEffect;

public abstract class LivingObject extends GameObject {

	private int hp;
	private GameEnvironment gameEnv;
	private SpecialEffect specialEffect;
	private ArrayList<SpecialEffect> inflictedEffects;

	public LivingObject(int x, int y, int hp, GameEnvironment gameEnv) {
		super(x, y);
		this.hp = hp;
		this.gameEnv = gameEnv;
		this.specialEffect = SpecialEffectFactory.getInstance(this);
		this.inflictedEffects = new ArrayList<>(); 
	}
	
	public int getHp() {
		return this.hp;
	}

	public void setHp(int newHp) {
		this.hp = newHp;
	}

	public boolean isAlive() {
		return this.hp > 0;
	}
	
	public GameEnvironment getGameEnvironment() {
		return this.gameEnv;
	}
	
	public SpecialEffect getSpecialEffect() {
		return this.specialEffect;
	}
	
	public ArrayList<SpecialEffect> getInflictedEffects() {
		return this.inflictedEffects;
	}
	
	public void inflictEffect(SpecialEffect newInflictedEffect) {
		this.inflictedEffects.add(newInflictedEffect);	
	}
	
	public void updateInflictedEffects() {
		SpecialEffect inflictedEffect;
		
		for (int i = 0; i < this.inflictedEffects.size() -1; i++) {
			inflictedEffect = this.inflictedEffects.get(i); 
			
			if (inflictedEffect.getTurnNbr() == 0) {
				inflictedEffect.endEffect(this);
				inflictedEffect.reinitializeEffect();
				this.inflictedEffects.remove(inflictedEffect);
			} else
				inflictedEffect.decrementTurnNbr();
		}
	}
	
	public abstract int getAttack();

	public abstract int getDefense();

	public abstract int getAttackRange();
	
	public abstract int getAttackSpeed();
	
	public abstract void useSpecialEffect(LivingObject livingObject);
}