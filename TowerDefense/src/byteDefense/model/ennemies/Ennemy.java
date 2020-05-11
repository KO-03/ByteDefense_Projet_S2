package byteDefense.model.ennemies;

public abstract class Ennemy {

	private int id;
	private int x;
	private int y;
	private StatisticsEnnemy stats;
	
	private static int counterId = 0;
	
	public Ennemy(int x, int y, StatisticsEnnemy stats) {
		this.x = x;
		this.y = y;
		this.stats = stats;
		this.id = counterId;
		counterId++;
	}
	
	public int getId() {
		return this.id;
	}

	public int getX() {
		return this.x;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}	
	
	public int getHp() {
		return this.stats.getHp();
	}
	
	public void decrementHp(int lostHp) {
		this.stats.setHp(this.stats.getHp() - lostHp);
	}

	public boolean isAlive() {
		return this.stats.getHp() > 0;
	}
	
	public int getAttack() {
		return this.stats.getAttack();
	}

	public int getDefense() {
		return this.stats.getDefense();
	}

	public int getAttackSpeed() {
		return this.stats.getAttackSpeed();
	}

	public int getAttackRange() {
		return this.stats.getAttackRange();
	}

	public int getCost() {
		return this.stats.getLoot();
	}

}
