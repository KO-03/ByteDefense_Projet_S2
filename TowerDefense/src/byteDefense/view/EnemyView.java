/*
 * EnemyView.java
 * Cette classe gere la partie visuelle d'un objet de jeu Enemy (un ennemi), ses responsabilites sont de :
 * - charger et stocker les textures des ennemis
 * - faire la correspondance entre les types de d'ennemis et leurs textures
 */

package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.LivingObject;
import byteDefense.model.enemies.Adware;
import byteDefense.model.enemies.Bot;
import byteDefense.model.enemies.Ransomware;
import byteDefense.model.enemies.Rootkit;
import byteDefense.model.enemies.Spyware;
import byteDefense.model.enemies.TrojanHorse;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class EnemyView extends LivingObjectView {

	// Textures des ennemis
	private static Image adwareImg; 
	private static Image botImg;
	private static Image ransomwareImg;
	private static Image rookitImg;
	private static Image spywareImg;
	private static Image trojanHorseImg;

	public EnemyView(Pane gridEnemies) {
		super(gridEnemies);
	}

	public void imageLoader() {
		try {
			adwareImg = new Image(new File("./resources/enemiesTextures/adware.png").toURI().toURL().toString()); 
			botImg = new Image(new File("./resources/enemiesTextures/bot.png").toURI().toURL().toString()); 
			ransomwareImg= new Image(new File("./resources/enemiesTextures/ransomware.png").toURI().toURL().toString()); 
			rookitImg = new Image(new File("./resources/enemiesTextures/rootkit.png").toURI().toURL().toString()); 
			spywareImg = new Image(new File("./resources/enemiesTextures/spyware.png").toURI().toURL().toString()); 
			trojanHorseImg = new Image(new File("./resources/enemiesTextures/trojan.png").toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public Image imageGetter(LivingObject livingObject) {
		Image img;

		if (livingObject instanceof Rootkit)
			img = rookitImg;
		else if (livingObject instanceof Adware)
			img = adwareImg;
		else if (livingObject instanceof Bot)
			img = botImg;
		else if (livingObject instanceof Ransomware)
			img = ransomwareImg;
		else if (livingObject instanceof Spyware)
			img = spywareImg;
		else if (livingObject instanceof TrojanHorse)
			img = trojanHorseImg;
		else
			img = null;

		return img;
	}
}