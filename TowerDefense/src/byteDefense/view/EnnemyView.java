/*
 * EnnemyView.java
 * Cette classe gere la partie visuelle d'un ennemi, ses responsabilites sont de :
 * - charger et stocker les ressources d'images des ennemis
 * - stocker les types d'ennemis
 * - faire la correspondance entre les types d'ennemis et l'ennemi a ajouter 
 * - ajouter un ennemi a la vue
 */

package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.ennemies.Ennemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class EnnemyView {

	private Ennemy ennemy;
	private Pane gridEnnemies;
	
	// Donnees correspondant aux composantes visuelles des ennemis 
	public static final int ADWARE = 1;
	public static final int BOT = 2;
	public static final int RANSOMWARE = 3;
	public static final int ROOKIT = 4;
	public static final int SPYWARE = 5;
	public static final int TROJAN_HORSE = 6;
	
	private static Image ADWARE_SRC_IMG; 
	private static Image BOT_SRC_IMG;
	private static Image RANSOMWARE_SRC_IMG;
	private static Image ROOKIT_SRC_IMG;
	private static Image SPYWARE_SRC_IMG;
	private static Image TROJAN_HORSE_SRC_IMG;
	
	public EnnemyView(Ennemy ennemy, Pane gridEnnemies) {
		this.ennemy = ennemy;
		this.gridEnnemies = gridEnnemies;
		this.imageLoader();
		this.addEnnemy();
	}
	
	private void imageLoader() {
		try {
			ADWARE_SRC_IMG = new Image(new File("./resources/character.png").toURI().toURL().toString()); 
			BOT_SRC_IMG = new Image(new File("./resources/character.png").toURI().toURL().toString()); 
			RANSOMWARE_SRC_IMG= new Image(new File("./resources/character.png").toURI().toURL().toString()); 
			ROOKIT_SRC_IMG = new Image(new File("./resources/character.png").toURI().toURL().toString()); 
			SPYWARE_SRC_IMG = new Image(new File("./resources/character.png").toURI().toURL().toString()); 
			TROJAN_HORSE_SRC_IMG = new Image(new File("./resources/character.png").toURI().toURL().toString()); 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private Image ennemyImageGet(int ennemyType) {
		Image ennemyImg = null;

		switch (ennemyType) {
			case ADWARE:
				ennemyImg = ADWARE_SRC_IMG;
				break;
			case BOT:
				ennemyImg = BOT_SRC_IMG;
				break;
			case RANSOMWARE:
				ennemyImg = RANSOMWARE_SRC_IMG;
				break;
			case ROOKIT:
				ennemyImg = ROOKIT_SRC_IMG;
				break;
			case SPYWARE:
				ennemyImg = SPYWARE_SRC_IMG;
				break;
			case TROJAN_HORSE:
				ennemyImg = TROJAN_HORSE_SRC_IMG;
				break;
		}
		return ennemyImg;
	}
	
	private void addEnnemy() {
		Image ennemyImg = ennemyImageGet(this.ennemy.getEnnemyType());
		
		if (ennemyImg != null) {
			ImageView imgView = new ImageView();
			imgView.setImage(ennemyImg);
			imgView.setId(Integer.toString(this.ennemy.getId()));
			imgView.setTranslateX(this.ennemy.getX());
			imgView.setTranslateY(this.ennemy.getY());	
			
			imgView.translateXProperty().bind(this.ennemy.getXProperty());
			imgView.translateYProperty().bind(this.ennemy.getYProperty());
			
			this.gridEnnemies.getChildren().add(imgView);
		}
	}
}
