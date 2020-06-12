/*
 * LivingObjectView.java
 * Cette classe gere la partie visuelle d'un LivingObject (Enemy ou Tower)
 */

package byteDefense.view;

import byteDefense.model.LivingObject;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public abstract class LivingObjectView extends GameObjectView {
	
	public LivingObjectView(Pane gameObjectGrid) {
		super(gameObjectGrid);
		this.imageLoader();
	}

	public abstract void imageLoader();

	public abstract Image imageGetter(LivingObject livingObject);
}