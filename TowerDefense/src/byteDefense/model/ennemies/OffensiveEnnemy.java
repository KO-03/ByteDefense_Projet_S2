package byteDefense.model.ennemies;

public abstract class OffensiveEnnemy extends Ennemy {

	public OffensiveEnnemy(int x, int y, int ennemyType) {
		super(x, y, ennemyType);
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
