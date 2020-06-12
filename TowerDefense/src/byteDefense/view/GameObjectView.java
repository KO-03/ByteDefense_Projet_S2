/*
 * GameObjectView.java
 * Cette classe gere la partie visuelle d'un objet de jeu (Enemy, Tower ou Bullet), ses responsabilites sont :
 * - stocker la grille ou les objets de jeu sont affiches
 * - supprimer un objet de jeu de la vue (grille)
 */

package byteDefense.view;

import byteDefense.model.GameObject;
import javafx.scene.layout.Pane;

public class GameObjectView {

	protected Pane gameObjectGrid;

	public GameObjectView(Pane gameObjectGrid) {
		this.gameObjectGrid = gameObjectGrid;
	}
	
	// Methode qui supprime l'objet de jeu de la vue
	public void removeGameObjectView(GameObject gameObject) {
		this.gameObjectGrid.getChildren().remove(this.gameObjectGrid.lookup("#" + gameObject.getId()));
	}
}
