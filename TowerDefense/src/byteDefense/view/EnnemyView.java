/*
 * EnnemyView.java
 * Cette classe gere la partie visuelle d'un objet de jeu Ennemy, ses responsabilites sont :
 * - charger et stocker les ressources d'images des ennemis
 * - faire la correspondance entre les types de d'ennemis et les ressources d'images des ennemis
 * - supprimer un ennemi de la vue.
 */

package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.GameObject;
import byteDefense.model.ennemies.Adware;
import byteDefense.model.ennemies.Bot;
import byteDefense.model.ennemies.Ransomware;
import byteDefense.model.ennemies.Rookit;
import byteDefense.model.ennemies.Spyware;
import byteDefense.model.ennemies.TrojanHorse;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class EnnemyView extends GameObjectView {
	
	private static Image adwareImg; 
	private static Image botImg;
	private static Image ransomwareImg;
	private static Image rookitImg;
	private static Image spywareImg;
	private static Image trojanHorseImg;

	public EnnemyView(Pane gridEnnemy) {
		super(gridEnnemy);
	}
	
	public void imageLoader() {
		try {
			adwareImg = new Image(new File("./resources/ennemiesTextures/character.png").toURI().toURL().toString()); 
			botImg = new Image(new File("./resources/ennemiesTextures/character.png").toURI().toURL().toString()); 
			ransomwareImg= new Image(new File("./resources/ennemiesTextures/character.png").toURI().toURL().toString()); 
			rookitImg = new Image(new File("./resources/ennemiesTextures/character.png").toURI().toURL().toString()); 
			spywareImg = new Image(new File("./resources/ennemiesTextures/character.png").toURI().toURL().toString()); 
			trojanHorseImg = new Image(new File("./resources/ennemiesTextures/character.png").toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public Image imageGetter(GameObject gameObject) {
		Image img;
		
		if (gameObject instanceof Rookit)
			img = rookitImg;
		else if (gameObject instanceof Adware)
			img = adwareImg;
		else if (gameObject instanceof Bot)
			img = botImg;
		else if (gameObject instanceof Ransomware)
			img = ransomwareImg;
		else if (gameObject instanceof Spyware)
			img = spywareImg;
		else if (gameObject instanceof TrojanHorse)
			img = trojanHorseImg;
		else
			img = null;
			
		return img;
	}

	public void removeEnnemy(GameObject gameObject) {
		super.grid.getChildren().remove(super.grid.lookup("#" + gameObject.getId()));
	}
}