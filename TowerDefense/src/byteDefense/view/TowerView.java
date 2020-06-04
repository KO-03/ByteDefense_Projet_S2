/*
 * TowerView.java
 * Cette classe gere la partie visuelle d'un objet de jeu Tower (une tourelle), ses responsabilites sont de :
 * - charger et stocker les textures des tourelles
 * - faire la correspondance entre les types de tourelles et leurs textures
 * - initialiser  les ImagesView de chaques types de tourelles et les ajouter à la grille où elles sont affichees
 */

package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.GameObject;
import byteDefense.model.towers.AdCube;
import byteDefense.model.towers.Antivirus;
import byteDefense.model.towers.AuthenticationPoint;
import byteDefense.model.towers.Firewall;
import byteDefense.model.towers.SudVPN;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TowerView extends GameObjectView {

	private static Image adcubeImg; 
	private static Image antivirusImg;
	private static Image firewallImg;
	private static Image authenticationPointImg;
	private static Image sudvpnImg;

	private ImageView adcube;
	private ImageView antivirus;
	private ImageView authentipoint;
	private ImageView firewall;
	private ImageView sudvpn;

	public TowerView(Pane grid, ImageView adcube, ImageView antivirus, ImageView authentificatipoint, ImageView firewall, ImageView sudvpn) {
		super(grid);
		this.adcube = adcube;
		this.antivirus = antivirus;
		this.authentipoint = authentificatipoint;
		this.firewall = firewall;
		this.sudvpn = sudvpn;

		this.initInventory(grid);
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

	public Image imageGetter(GameObject gameObject) {
		Image img;

		if (gameObject instanceof AdCube)
			img = adcubeImg;
		else if (gameObject instanceof Antivirus)
			img = antivirusImg;
		else if (gameObject instanceof Firewall)
			img = firewallImg;
		else if (gameObject instanceof AuthenticationPoint)
			img = authenticationPointImg;
		else if (gameObject instanceof SudVPN)
			img = sudvpnImg;
		else
			img = null;

		return img;
	}

	public void initInventory(Pane grid) {
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

		grid.getChildren().add(this.adcube);
		grid.getChildren().add(this.antivirus);
		grid.getChildren().add(this.authentipoint);
		grid.getChildren().add(this.firewall);
		grid.getChildren().add(this.sudvpn);
	}
}