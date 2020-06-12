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
	private int defense;
	private GameEnvironment gameEnv;
	private SpecialEffect specialEffect; // effet/capacite propre 
	private ArrayList<SpecialEffect> inflictedEffects; // liste des effets infliges

	public LivingObject(int hp, int defense, GameEnvironment gameEnv) {
		this.hp = hp;
		this.defense = defense;
		this.gameEnv = gameEnv;
		this.specialEffect = SpecialEffectFactory.getInstance(this);
		this.inflictedEffects = new ArrayList<>(); 
	}
	
	public int getHp() {
		return this.hp;
	}

	private void setHp(int newHp) {
		this.hp = newHp;
	}

	public int getDefense() {
		return this.defense;
	}
	
	public void setDefense(int newDefense) {
		this.defense = newDefense;
	}
	
	public void incrementHp(int newHp) {
		this.setHp(newHp);
	}
	
	public void receiveDamage(int damage) {
		int rest = this.getDefense() - damage;
		
		if (rest < 0) {
			this.setDefense(0);
			this.setHp(this.getHp() + rest);
		} else if (rest > 0)
			this.setDefense(rest);
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
	
	public void inflictEffect(SpecialEffect newInflictedEffect) {
		this.inflictedEffects.add(newInflictedEffect);	
	}
	
	// Methode qui met a jours les effets infliges
	public void updateInflictedEffects() {
		SpecialEffect inflictedEffect;
		
		for (int i = 0; i < this.inflictedEffects.size() -1; i++) {
			inflictedEffect = this.inflictedEffects.get(i); 
			// L'effet est dissipe
			if (inflictedEffect.getTurnNbr() == 0) {
				inflictedEffect.endEffect(this);
				inflictedEffect.reinitializeEffect();
				this.inflictedEffects.remove(inflictedEffect);
			} else
				inflictedEffect.decrementTurnNbr();
		}
	}
	
	public abstract int getAttack();
	
	public abstract void useSpecialEffect(LivingObject livingObject);
}