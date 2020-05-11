package applicationV1.model.ennemies;

public class Ennemy {

	private int id;
	private int x;
	private int y;
	private int hp;
	private int attack;
	private int defense;
	private int attackSpeed;
	private int attackRange;
	private int cost;
	private String urlImg;
	
	private static int counterId = 0;
	
	public Ennemy(int x, int y, int hp, int attack, int defense, int attackSpeed, int attackRange, int cost, String urlImg) {
		this.x = x;
		this.y = y;
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.attackRange = attackRange;
		this.attackSpeed = attackSpeed;
		this.cost = cost;
		this.urlImg = urlImg;
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
		return this.hp;
	}
	
	public void decrementHp(int lostHp) {
		this.hp -= lostHp;
	}

	public boolean isAlive() {
		return this.hp > 0;
	}
	
	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public int getCost() {
		return cost;
	}
	
	public String getUrlImg() {
		return this.urlImg;
	}
}
