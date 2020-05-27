/*
 * GameObjectView.java
 * Cette classe gere la partie visuelle d'un objet de jeu, ses responsabilites sont :
 * - stocker la grille correspondante pour l'objet de jeu (grille d'ennemis ou de tourelles)
 * - charger et stocker les ressources d'images des ennemis et des tourelles
 * - stocker les types d'ennemis et de tourelles
 * - faire la correspondance entre les types de d'ennemis, de tourelles et les donnees de l'objet de jeu a ajouter 
 * - ajouter un ennemi ou une tourelle à la vue
 * - supprimer un ennemi ou une tourelle de la vue.
 * -
 */

package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.GameObject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class GameObjectView {
	
	private Pane grid;
	
	public static final int ROOKIT = 1;
	public static final int ADWARE = 2;
	public static final int BOT = 3;
	public static final int RANSOMWARE = 4;
	public static final int SPYWARE = 5;
	public static final int TROJAN_HORSE = 6;
	
	public static final int ADCUBE = 7;
	public static final int ANTIVIRUS = 8;
	public static final int FIREWALL = 9;
	public static final int AUTHENTIPOINT = 10;
	public static final int SUDVPN = 11;
	
	private static Image ADWARE_SRC_IMG; 
	private static Image BOT_SRC_IMG;
	private static Image RANSOMWARE_SRC_IMG;
	private static Image ROOKIT_SRC_IMG;
	private static Image SPYWARE_SRC_IMG;
	private static Image TROJAN_HORSE_SRC_IMG;
	
	private static Image ADCUBE_SRC_IMG; 
	private static Image ANTIVIRUS_SRC_IMG;
	private static Image FIREWALL_SRC_IMG;
	private static Image AUTHENTIPOINT_SRC_IMG;
	private static Image SUDVPN_SRC_IMG;

	public GameObjectView(Pane grid) {
		this.grid = grid;
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
			
			ADCUBE_SRC_IMG = new Image(new File("./resources/character.png").toURI().toURL().toString()); 
			ANTIVIRUS_SRC_IMG = new Image(new File("./resources/character.png").toURI().toURL().toString()); 
			FIREWALL_SRC_IMG= new Image(new File("./resources/character.png").toURI().toURL().toString()); 
			AUTHENTIPOINT_SRC_IMG = new Image(new File("./resources/character.png").toURI().toURL().toString()); 
			SUDVPN_SRC_IMG = new Image(new File("./resources/character.png").toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private Image imageGet(GameObject gameObject) {
		Image img = null;

		switch (type) {
			case ADCUBE:
				img = ADCUBE_SRC_IMG;
				break;
			case ANTIVIRUS:
				img = ANTIVIRUS_SRC_IMG;
				break;
			case FIREWALL:
				img = FIREWALL_SRC_IMG;
				break;
			case AUTHENTIPOINT:
				img = AUTHENTIPOINT_SRC_IMG;
				break;
			case SUDVPN:
				img = SUDVPN_SRC_IMG;
				break;
			case ROOKIT:
				img = ROOKIT_SRC_IMG;
				break;
			case ADWARE:
				img = ADWARE_SRC_IMG;
				break;
			case BOT:
				img = BOT_SRC_IMG;
				break;
			case RANSOMWARE:
				img = RANSOMWARE_SRC_IMG;
				break;
			case SPYWARE:
				img = SPYWARE_SRC_IMG;
				break;
			case TROJAN_HORSE:
				img = TROJAN_HORSE_SRC_IMG;
				break;
		}
		return img;
	}
	
	public void addGameObject(GameObject gameObject) {
		Image ennemyImg = imageGet(gameObject);
		
		if (ennemyImg != null) {
			ImageView imgView = new ImageView();
			imgView.setImage(ennemyImg);
			imgView.setId(Integer.toString(gameObject.getId()));
			imgView.setTranslateX(gameObject.getX());
			imgView.setTranslateY(gameObject.getY());	
			
			imgView.translateXProperty().bind(gameObject.getXProperty());
			imgView.translateYProperty().bind(gameObject.getYProperty());
			
			this.grid.getChildren().add(imgView);
		}
	}
	
	public void removeEnnemy(GameObject gameObject) {
		grid.getChildren().remove(grid.lookup("#" + gameObject.getId()));
	}
}

