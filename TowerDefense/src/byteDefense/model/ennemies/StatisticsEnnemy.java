package byteDefense.model.ennemies;

public class StatisticsEnnemy {

	private int hp;
	private final int attack;
	private final int defense;
	private final int attackSpeed;
	private final int attackRange;
	private final int loot;
	
	public StatisticsEnnemy(int hp, int attack, int defense, int attackSpeed, int attackRange, int loot) {
		this.hp = hp;
		this.attack = attack;
		this.defense = defense;
		this.attackSpeed = attackSpeed;
		this.attackRange = attackRange;
		this.loot = loot;
	}
	
	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
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

	public int getLoot() {
		return loot;
	}
	
}
