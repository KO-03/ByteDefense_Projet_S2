/*
 * TowerView.java
 * Cette classe gere la partie visuelle d'un objet de jeu Tower (une tourelle), ses responsabilites sont de :
 * - charger et stocker les textures des tourelles
 * - faire la correspondance entre les types de tourelles et leurs textures
 * - initialiser les positions des ImagesView des tourelles dans leur menu d'achat et les ajouter à la grille 
 *   où elles sont affichees
 */

package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.LivingObject;
import byteDefense.model.towers.AdCube;
import byteDefense.model.towers.Antivirus;
import byteDefense.model.towers.AuthenticationPoint;
import byteDefense.model.towers.Firewall;
import byteDefense.model.towers.SudVPN;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TowerView extends LivingObjectView {

	// Textures des tourelles
	private static Image adcubeImg; 
	private static Image antivirusImg;
	private static Image firewallImg;
	private static Image authenticationPointImg;
	private static Image sudvpnImg;

	// Noeuds a ajouter a la vue
	private ImageView adcube;
	private ImageView antivirus;
	private ImageView authentipoint;
	private ImageView firewall;
	private ImageView sudvpn;

	public TowerView(Pane gameObjectGrid, ImageView adcube, ImageView antivirus, ImageView authentificatipoint, ImageView firewall, ImageView sudvpn) {
		super(gameObjectGrid);
		this.adcube = adcube;
		this.antivirus = antivirus;
		this.authentipoint = authentificatipoint;
		this.firewall = firewall;
		this.sudvpn = sudvpn;

		this.initInventory(super.gameObjectGrid);
	}

	public void imageLoader() {
		try {	
			adcubeImg = new Image(new File("./resources/towerTextures/adcube.png").toURI().toURL().toString()); 
			antivirusImg = new Image(new File("./resources/towerTextures/antivirus.png").toURI().toURL().toString()); 
			firewallImg= new Image(new File("./resources/towerTextures/firewall.png").toURI().toURL().toString()); 
			authenticationPointImg = new Image(new File("./resources/towerTextures/authpoint.png").toURI().toURL().toString()); 
			sudvpnImg = new Image(new File("./resources/towerTextures/sudvpn.png").toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public Image imageGetter(LivingObject livingObject) {
		Image img;

		if (livingObject instanceof AdCube)
			img = adcubeImg;
		else if (livingObject instanceof Antivirus)
			img = antivirusImg;
		else if (livingObject instanceof Firewall)
			img = firewallImg;
		else if (livingObject instanceof AuthenticationPoint)
			img = authenticationPointImg;
		else if (livingObject instanceof SudVPN)
			img = sudvpnImg;
		else
			img = null;

		return img;
	}
	
	/* Methode qui initialise les positions des ImagesView des tourelles dans leur menu d'achat 
	 * et les ajoute à la grille où elles sont affichees
	 */
	private void initInventory(Pane gameObjectGrid) {
		this.adcube.setX(129);
		this.adcube.setY(741);
		this.antivirus.setX(213);
		this.antivirus.setY(741);
		this.authentipoint.setX(298);
		this.authentipoint.setY(741);
		this.firewall.setX(383);
		this.firewall.setY(741);
		this.sudvpn.setX(466);
		this.sudvpn.setY(741);

		gameObjectGrid.getChildren().add(this.adcube);
		gameObjectGrid.getChildren().add(this.antivirus);
		gameObjectGrid.getChildren().add(this.authentipoint);
		gameObjectGrid.getChildren().add(this.firewall);
		gameObjectGrid.getChildren().add(this.sudvpn);
	}
}