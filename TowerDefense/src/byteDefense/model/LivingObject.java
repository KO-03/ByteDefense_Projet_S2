/*
 * LivingObject.java
 * Cette classe parent represente un objet "vivant" du jeu (un ennemi ou une tourelle), ses responsabilites sont de :
 * - stocker, recuperer et incremente ses points de vie 
 * - stocker et recuperer la defense
 * - stocker et recuperer son environnement de jeu
 * - stocker, recuperer l'effet propre a l'objet
 * - stocker, recuperer et mettre a jour la liste des effets infliges
 * - gerer la perte de points de vie et de defense
 * - verifier si l'objet est mort ou non
 * - recuperer ses caracteristiques (x, y et points d'attaque)
 */

package byteDefense.model;

import java.util.ArrayList;

import byteDefense.factories.SpecialEffectFactory;
import byteDefense.model.effects.SpecialEffect;

public abstract class LivingObject extends GameObject {

	private int hp;
	private int defense;
	private GameEnvironment gameEnv;
	private SpecialEffect specialEffect; // effet propre a l'objet
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
	
	public abstract int getX();
	
	public abstract int getY();
	
	public abstract int getAttack();
	
	public abstract int getAttackRange();
	
	public GameEnvironment getGameEnvironment() {
		return this.gameEnv;
	}
	
	public SpecialEffect getSpecialEffect() {
		return this.specialEffect;
	}
	
	public void incrementHp(int newHp) {
		this.setHp(newHp);
	}
	
	// Methode qui gere la decrementation de la defense et des points de vie de l'objet
	public void receiveDamage(int damage) {
		int rest = this.defense - damage;
		
		if (rest < 0) {
			this.setDefense(0);
			this.setHp(this.getHp() + rest);
		} else if (rest > 0)
			this.setDefense(rest);
	}
	
	// Fonction qui verifie si l'objet est en vie ou non 
	public boolean isAlive() {
		return this.hp > 0;
	}
	
	public abstract void useSpecialEffect(LivingObject livingObject);
	
	// Methode qui inflige un nouveau effet au livingObject (l'ajoute a la liste des effets infliges)
	public void inflictEffect(SpecialEffect newInflictedEffect) {
		this.inflictedEffects.add(newInflictedEffect);	
	}
	
	// Methode qui met a jour les effets infliges
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
}