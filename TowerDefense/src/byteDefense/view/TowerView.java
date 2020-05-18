/*
 * TowerView.java
 * Cette classe gere la partie visuelle d'une tourelle, ses responsabilites sont de :
 * - charger et stocker les ressources d'images des tourelles
 * - stocker les types de tourelle
 * - faire la correspondance entre les types de tourelle et la tourelle a ajouter 
 * - ajouter une tourelle a la vue
 */

package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.towers.Tower;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TowerView {

	private Tower tower;
	private Pane gridTowers;
	
	// Donnees correspondant aux composantes visuelles des ennemis 
	public static final int ADCUBE = 1;
	public static final int ANTIVIRUS = 2;
	public static final int FIREWALL = 3;
	public static final int AUTHENTIPOINT = 4;
	public static final int SUDVPN = 5;
	
	private static Image ADCUBE_SRC_IMG; 
	private static Image ANTIVIRUS_SRC_IMG;
	private static Image FIREWALL_SRC_IMG;
	private static Image AUTHENTIPOINT_SRC_IMG;
	private static Image SUDVPN_SRC_IMG;
	
	public TowerView(Tower tower, Pane gridTowers) {
		this.tower = tower;
		this.gridTowers = gridTowers;
		this.imageLoader();
		this.addTower();
	}
	
	private void imageLoader() {
		try {
			ADCUBE_SRC_IMG = new Image(new File("./resources/character.png").toURI().toURL().toString()); 
			ANTIVIRUS_SRC_IMG = new Image(new File("./resources/character.png").toURI().toURL().toString()); 
			FIREWALL_SRC_IMG= new Image(new File("./resources/character.png").toURI().toURL().toString()); 
			AUTHENTIPOINT_SRC_IMG = new Image(new File("./resources/character.png").toURI().toURL().toString()); 
			SUDVPN_SRC_IMG = new Image(new File("./resources/character.png").toURI().toURL().toString()); 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	private Image towerImageGet(int towerType) {
		Image towerImg = null;

		switch (towerType) {
			case ADCUBE:
				towerImg = ADCUBE_SRC_IMG;
				break;
			case ANTIVIRUS:
				towerImg = ANTIVIRUS_SRC_IMG;
				break;
			case FIREWALL:
				towerImg = FIREWALL_SRC_IMG;
				break;
			case AUTHENTIPOINT:
				towerImg = AUTHENTIPOINT_SRC_IMG;
				break;
			case SUDVPN:
				towerImg = SUDVPN_SRC_IMG;
				break;
		}
		return towerImg;
	}
	
	private void addTower() {
		Image towerImg = towerImageGet(this.tower.getTowerType());
		
		if (towerImg != null) {
			ImageView imgView = new ImageView();
			imgView.setImage(towerImg);
			imgView.setId(Integer.toString(this.tower.getId()));
			imgView.setTranslateX(this.tower.getX());
			imgView.setTranslateY(this.tower.getY());
			
			this.gridTowers.getChildren().add(imgView);
		}
	}
}
