/*
 * GameObjectView.java
 * Cette classe gere la partie visuelle d'un objet de jeu, ses responsabilites sont :
 * - stocker la grille correspondante pour l'objet de jeu (grille d'ennemis ou de tourelles)
 * - ajouter un ennemi ou une tourelle a la vue
 */

package byteDefense.view;

import byteDefense.model.GameObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class GameObjectView {
	
	protected Pane grid;

	public GameObjectView(Pane grid) {
		this.grid = grid;
		this.imageLoader();
	}
	
	public abstract void imageLoader();
	
	public abstract Image imageGetter(GameObject gameObject);
	
	public void addGameObject(GameObject gameObject) {
		Image ennemyImg = imageGetter(gameObject);
		
		if (ennemyImg != null) {
			ImageView imgView = new ImageView();
			imgView.setImage(ennemyImg);
			imgView.setId(Integer.toString(gameObject.getId()));
			imgView.setTranslateX(gameObject.getX());
			imgView.setTranslateY(gameObject.getY());	
			
			imgView.translateXProperty().bind(gameObject.getXProperty());
			imgView.translateYProperty().bind(gameObject.getYProperty());
			
			this.grid.getChildren().add(imgView);
		}
	}
}