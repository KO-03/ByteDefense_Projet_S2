/*
 * BulletView.java
 *  Cette classe gere la partie visuelle d'une Bullet (un tir), sa responsabilite est de :
 *  - ajouter un tir a la vue (grille)
 */

package byteDefense.view;

import byteDefense.model.Bullet;
import byteDefense.model.GameArea;
import byteDefense.model.enemies.Enemy;
import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BulletView extends GameObjectView {
	
	public BulletView(Pane gridBullets) {
		super(gridBullets);
	}
	
	public void addBulletView(Bullet bullet) {
		int tileCenter = GameArea.TILE_SIZE / 2; // centre d'une tuile du plateau de jeu
		Circle circ = new Circle();
		
		circ.setId(Integer.toString(bullet.getId()));
		circ.setTranslateX(bullet.getShooterX() + tileCenter);
		circ.setTranslateY(bullet.getShooterY() + tileCenter);
		circ.setRadius(5);
		if (bullet.getShooterObject() instanceof Enemy)
			circ.setFill(Color.FIREBRICK);
		else 
			circ.setFill(Color.DARKGREEN);
		
		super.gameObjectGrid.getChildren().add(circ);
		
		// Creation d'un animation de translation pour le deplacement du tir du tireur a la cible
		TranslateTransition tt = new TranslateTransition(Duration.millis(250), circ);
		// Fixation des point d'arrivee du tir sur la cible
		tt.setToX(bullet.getTargetX() + tileCenter);
		tt.setToY(bullet.getTargetY() + tileCenter);
		tt.play();
	}
}
