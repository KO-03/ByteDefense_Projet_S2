/*
 * GameObjectView.java
 * Cette classe gere la partie visuelle d'un objet de jeu (Ennemy ou Tower), ses responsabilites sont :
 * - stocker la grille correspondante pour l'objet de jeu (gridEnnemies ou de gridTowers)
 * - ajouter un objet de jeu a la vue
 * - supprimer un objet de jeu de la vue (de la grille)
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

	public Pane getGrid() {
		return this.grid;
	}

	public abstract void imageLoader();

	public abstract Image imageGetter(GameObject gameObject);

	public void addGameObject(GameObject gameObject) {
		Image gameObjectImg = imageGetter(gameObject);

		if (gameObjectImg != null) {
			ImageView imgView = new ImageView();
			imgView.setImage(gameObjectImg);
			imgView.setId(Integer.toString(gameObject.getId()));
			imgView.setTranslateX(gameObject.getX());
			imgView.setTranslateY(gameObject.getY());	
			
			imgView.translateXProperty().bind(gameObject.getXProperty());
			imgView.translateYProperty().bind(gameObject.getYProperty());

			this.grid.getChildren().add(imgView);
		}
	}
	
	public void removeEnnemy(GameObject gameObject) {
		this.grid.getChildren().remove(this.grid.lookup("#" + gameObject.getId()));
	}
}