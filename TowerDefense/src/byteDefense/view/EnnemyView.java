/*
 * EnnemyView.java
 * Cette classe gere la partie visuelle d'un objet de jeu Ennemy (un ennemi), ses responsabilites sont de :
 * - charger et stocker les textures des ennemis
 * - faire la correspondance entre les types de d'ennemis et leurs textures
 */

package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.GameObject;
import byteDefense.model.enemies.Adware;
import byteDefense.model.enemies.Bot;
import byteDefense.model.enemies.Ransomware;
import byteDefense.model.enemies.Rookit;
import byteDefense.model.enemies.Spyware;
import byteDefense.model.enemies.TrojanHorse;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class EnnemyView extends GameObjectView {

	private static Image adwareImg; 
	private static Image botImg;
	private static Image ransomwareImg;
	private static Image rookitImg;
	private static Image spywareImg;
	private static Image trojanHorseImg;

	public EnnemyView(Pane grid) {
		super(grid);
	}

	public void imageLoader() {
		try {
			adwareImg = new Image(new File("./resources/ennemiesTextures/adware.png").toURI().toURL().toString()); 
			botImg = new Image(new File("./resources/ennemiesTextures/bot.png").toURI().toURL().toString()); 
			ransomwareImg= new Image(new File("./resources/ennemiesTextures/ransomware.png").toURI().toURL().toString()); 
			rookitImg = new Image(new File("./resources/ennemiesTextures/rootkit.png").toURI().toURL().toString()); 
			spywareImg = new Image(new File("./resources/ennemiesTextures/spyware.png").toURI().toURL().toString()); 
			trojanHorseImg = new Image(new File("./resources/ennemiesTextures/trojan.png").toURI().toURL().toString());
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
}