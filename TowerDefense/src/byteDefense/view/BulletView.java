package byteDefense.view;

import byteDefense.model.Bullet;
import byteDefense.model.GameArea;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BulletView {
	
	private Pane grid;
	
	public BulletView(Pane grid) {
		this.grid = grid;
	}
	
	public void addBulletView(Bullet bullet) {
		int tileCenter = GameArea.TILE_SIZE / 2;
		Circle circ = new Circle();
		circ.setId(Integer.toString(bullet.getId()));
		circ.setTranslateX(bullet.getX() + tileCenter);
		circ.setTranslateY(bullet.getY() + tileCenter);
		circ.setRadius(5);
		
		this.grid.getChildren().add(circ);
		
		TranslateTransition tt = new TranslateTransition(Duration.millis(250), circ);
		tt.setToX(bullet.getTargetX() + tileCenter);
		tt.setToY(bullet.getTargetY() + tileCenter);
		tt.play();
	}
	
	public void removeBulletView(Bullet bullet) {
		this.grid.getChildren().remove(this.grid.lookup("#" + bullet.getId()));
	}
}
