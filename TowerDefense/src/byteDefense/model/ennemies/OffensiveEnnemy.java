package byteDefense.model.ennemies;

import byteDefense.utilities.BFS;

public abstract class OffensiveEnnemy extends Ennemy {

	public OffensiveEnnemy(int x, int y, int ennemyType, BFS bfsMap) {
		super(x, y, ennemyType, bfsMap);
	}

	public void attackTower() {
		
	}
	
	public abstract void act();
	
	public abstract float getAttack();

	public abstract int getDefense();

	public abstract int getAttackSpeed();

	public abstract int getAttackRange();
	
	public abstract int getLoot();
}
