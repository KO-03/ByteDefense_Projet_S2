package byteDefense.view;

import java.io.File;
import java.net.MalformedURLException;

import byteDefense.model.ennemies.Ennemy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

public class EnnemyView {

	private Ennemy ennemy;
	private TilePane gridEnnemies;
	
	public EnnemyView(Ennemy ennemy, TilePane gridEnnemies) {
		this.ennemy = ennemy;
		this.gridEnnemies = gridEnnemies;
		this.addEnnemy();
	}
	
	public void addEnnemy() {
		File f = new File("./resources/character.png");
		
		if (f != null) {
			ImageView imgView = new ImageView();
			try {
				Image img = new Image(f.toURI().toURL().toString());
				
				imgView.setImage(img);
				imgView.setTranslateX(ennemy.getX());
				imgView.setTranslateY(ennemy.getY());	
				
				imgView.translateXProperty().bind(ennemy.getXProperty());
				imgView.translateYProperty().bind(ennemy.getYProperty());
				
				this.gridEnnemies.getChildren().add(imgView);
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	/*
	public void moveEnnemy(KeyEvent event) {

			if (event.getCode() == KeyCode.Q)
    			ennemy.setX(ennemy.getX() - 1);
    		else if (event.getCode() == KeyCode.D) {
    			ennemy.setX(ennemy.getX() + 1);
    			System.out.println("a");
    		}
    		else if (event.getCode() == KeyCode.Z)
    			ennemy.setX(ennemy.getX() + 1);
    		else if (event.getCode() == KeyCode.S)
    			ennemy.setX(ennemy.getX() - 1);
    }*/
}
