/*
 * EnnemyView.java
 * Cette classe gère la partie visuelle d'un ennemi, ses responsabilités sont de :
 */

package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.ennemies.Ennemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class EnnemyView {

	private Ennemy ennemy;
	private TilePane gridEnnemies;
	
	public EnnemyView(Ennemy ennemy, TilePane gridEnnemies) {
		this.ennemy = ennemy;
		this.gridEnnemies = gridEnnemies;
		this.addEnnemy();
	}
	
	private void addEnnemy() {
		File sourceFile = new File("./resources/character.png");
		
		if (sourceFile != null) {
			ImageView imgView = new ImageView();
			try {
				Image img = new Image(sourceFile.toURI().toURL().toString());
				
				imgView.setImage(img);
				imgView.setTranslateX(this.ennemy.getX());
				imgView.setTranslateY(this.ennemy.getY());	
				
				imgView.translateXProperty().bind(this.ennemy.getXProperty());
				imgView.translateYProperty().bind(this.ennemy.getYProperty());
				
				this.gridEnnemies.getChildren().add(imgView);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}	
		}
	}
}
