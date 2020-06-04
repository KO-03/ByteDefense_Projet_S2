package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.Bullet;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BulletView {
	
	private Pane grid;
	private Image bulletImg;
	
	public BulletView(Pane grid) {
		this.grid = grid;
		try {
			bulletImg = new Image(new File("./resources/icons/attack.png").toURI().toURL().toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void addBulletImgView(Bullet bullet) {
		ImageView bulletImgView = new ImageView();
		
		bulletImgView.setImage(this.bulletImg);
		
		bulletImgView.setTranslateX(bullet.getX());
		bulletImgView.setTranslateY(bullet.getY());	

		bulletImgView.translateXProperty().bind(bullet.getXProperty());
		bulletImgView.translateYProperty().bind(bullet.getYProperty());

		this.grid.getChildren().add(bulletImgView);
	}
	
	public void removeBulletImgView(Bullet bullet) {
		this.grid.getChildren().remove(this.grid.lookup("#" + bullet.getId()));
	}
}
