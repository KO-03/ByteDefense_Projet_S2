/*
 * LivingObjectView.java
 * Cette classe gere la partie visuelle d'un LivingObject (Enemy ou Tower), sa responsabilite est de :
 * - ajouter un LivingObject a la vue (grille)
 */

package byteDefense.view;

import byteDefense.model.LivingObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class LivingObjectView extends GameObjectView {
	
	public LivingObjectView(Pane gameObjectGrid) {
		super(gameObjectGrid);
		this.imageLoader();
	}

	public abstract void imageLoader();

	public abstract Image imageGetter(LivingObject livingObject);

	// Methode qui ajoute la tourelle ou l'ennemi a la vue en fixant ces donnees de vues et ces actions d'evenements
	public void addLivingObjectView(LivingObject livingObject) {
		Image img = imageGetter(livingObject);

		if (img != null) {
			ImageView imgView = new ImageView();
			imgView.setImage(img);
			imgView.setId(Integer.toString(livingObject.getId()));
			imgView.setTranslateX(livingObject.getX());
			imgView.setTranslateY(livingObject.getY());	
			
			imgView.translateXProperty().bind(livingObject.getXProperty());
			imgView.translateYProperty().bind(livingObject.getYProperty());
			
			super.gameObjectGrid.getChildren().add(imgView);
		}
	}
}