/*
 * EnnemyView.java
 * Cette classe gere les donnees et actions sur la partie visuelle des ennemis, ses responsabilites sont de :
 * - charger et stocker les ressources d'images des ennemis
 * - stocker les donnees correspondants types d'ennemis
 * - faire la correspondance entre les types d'ennemis et l'ennemi a ajouter 
 * - ajouter un ennemi a la vue
 * - stocker la grille d'ennemis et gerer les operations qui y sont liees
 */

package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.ennemies.Ennemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class EnnemyView {

	private Pane gridEnnemies;
	
	// Donnees correspondant aux composantes visuelles des ennemis 
	public static final int ROOKIT = 1;
	public static final int ADWARE = 2;
	public static final int BOT = 3;
	public static final int RANSOMWARE = 4;
	public static final int SPYWARE = 5;
	public static final int TROJAN_HORSE = 6;
	
	// Image a ajoute aux ImageView des ennemis ajoutes à la vue
	private static Image ADWARE_SRC_IMG; 
	private static Image BOT_SRC_IMG;
	private static Image RANSOMWARE_SRC_IMG;
	private static Image ROOKIT_SRC_IMG;
	private static Image SPYWARE_SRC_IMG;
	private static Image TROJAN_HORSE_SRC_IMG;
	
	public EnnemyView(Pane gridEnnemies) {
		this.gridEnnemies = gridEnnemies;
		this.imageLoader();
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
			case ROOKIT:
				ennemyImg = ROOKIT_SRC_IMG;
				break;
			case ADWARE:
				ennemyImg = ADWARE_SRC_IMG;
				break;
			case BOT:
				ennemyImg = BOT_SRC_IMG;
				break;
			case RANSOMWARE:
				ennemyImg = RANSOMWARE_SRC_IMG;
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
	
	public void addEnnemyView(Ennemy ennemy) {
		Image ennemyImg = ennemyImageGet(ennemy.getEnnemyType());
		
		if (ennemyImg != null) {
			ImageView imgView = new ImageView();
			
			// Fixage des donnees de modele et de vue au noeud de la vue de l'ennemi
			imgView.setImage(ennemyImg);
			imgView.setId(Integer.toString(ennemy.getId()));
			imgView.setTranslateX(ennemy.getX());
			imgView.setTranslateY(ennemy.getY());	
			
			// Liaison des donnees de position entre le modele et la vue
			imgView.translateXProperty().bind(ennemy.getXProperty());
			imgView.translateYProperty().bind(ennemy.getYProperty());
			
			this.gridEnnemies.getChildren().add(imgView);
		}
	}
	
	public void removeEnnemy(Ennemy e) {
		gridEnnemies.getChildren().remove(gridEnnemies.lookup("#" + e.getId()));
	}
}
