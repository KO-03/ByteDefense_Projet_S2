/*
 * GameObjectView.java
 * Cette classe gere la partie visuelle d'un objet de jeu (Ennemy ou Tower), ses responsabilites sont :
 * - stocker la grille ou les objets de jeu sont affiches
 * - ajouter un objet de jeu a la vue (grille)
 * - supprimer un objet de jeu de la vue (grille)
 */

package byteDefense.view;

import byteDefense.model.LivingObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class LivingObjectView {

	private Pane grid;

	public LivingObjectView(Pane grid) {
		this.grid = grid;
		this.imageLoader();
	}

	public abstract void imageLoader();

	public abstract Image imageGetter(LivingObject livingObject);

	public void addLivingObject(LivingObject livingObject) {
		Image gameObjectImg = imageGetter(livingObject);

		if (gameObjectImg != null) {
			ImageView imgView = new ImageView();
			imgView.setImage(gameObjectImg);
			imgView.setId(Integer.toString(livingObject.getId()));
			imgView.setTranslateX(livingObject.getX());
			imgView.setTranslateY(livingObject.getY());	
			
			imgView.translateXProperty().bind(livingObject.getXProperty());
			imgView.translateYProperty().bind(livingObject.getYProperty());

			this.grid.getChildren().add(imgView);
		}
	}
	
	public void removeGameObject(LivingObject livingObject) {
		this.grid.getChildren().remove(this.grid.lookup("#" + livingObject.getId()));
	}
}