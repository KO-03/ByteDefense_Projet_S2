/*
 * EnemyView.java
 * Cette classe gere la partie visuelle d'un objet de jeu Enemy (un ennemi), ses responsabilites sont de :
 * - charger et stocker les textures des ennemis
 * - faire la correspondance entre les types de d'ennemis et leurs textures
 * - ajouter un ennemi dans la vue en fixant ces donnees de vues et ces actions d'evenements
 */

package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.LivingObject;
import byteDefense.model.enemies.Adware;
import byteDefense.model.enemies.Bot;
import byteDefense.model.enemies.Enemy;
import byteDefense.model.enemies.Ransomware;
import byteDefense.model.enemies.Rootkit;
import byteDefense.model.enemies.Spyware;
import byteDefense.model.enemies.TrojanHorse;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

	// Methode qui recupere et charge les textures des ennemis
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

	// Fonction qui retourne la texture correspondante a l'ennemi donne en parametre
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
	
	// Methode qui ajoute l'ennemi a la vue en fixant ces donnees de vues et ces actions d'evenements
	public void addEnemyView(Enemy enemy) {
		Image img = imageGetter(enemy);

		if (img != null) {
			ImageView imgView = new ImageView();
			imgView.setImage(img);
			imgView.setId(Integer.toString(enemy.getId()));
			imgView.setTranslateX(enemy.getX());
			imgView.setTranslateY(enemy.getY());	

			imgView.translateXProperty().bind(enemy.getXProperty());
			imgView.translateYProperty().bind(enemy.getYProperty());
			
			super.gameObjectGrid.getChildren().add(imgView);
		}
	}
}