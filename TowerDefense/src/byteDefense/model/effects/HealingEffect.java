package byteDefense.model.effects;

import byteDefense.model.LivingObject;

public class HealingEffect extends SpecialEffect {
	
	public HealingEffect(int timeEffect) {
		super(timeEffect);
	}
	/*
	public void startEffect() {
		Bot bot = (Bot)super.getShooter();
		Enemy ally = findAlly(bot);
		
		if (ally != null)
			ally.setHp((int)(ally.getHp() * bot.getHealingRate()));
	}
	
	public Enemy findAlly(Bot bot) {
		ObservableList<Enemy> enemies = bot.getGameEnvironment().getEnemies();
		Enemy ally = null;
		int i = 0, tileSize = GameArea.TILE_SIZE;
		
		while (i < enemies.size() && ally == null) {
			ally = enemies.get(i);
			
			if ((bot.getY() + 1) * tileSize == ally.getY()
					|| (bot.getY() - 1) * tileSize == ally.getY()
					|| (bot.getX() + 1) * tileSize == ally.getX()
					|| (bot.getX() - 1) * tileSize == ally.getX()) {
				return ally; 
			}
			i++;
		}
		return ally;
	}
	*/
	public void endEffect(LivingObject livingObject) {
		
	}
}
