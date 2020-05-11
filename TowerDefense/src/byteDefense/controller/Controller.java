package byteDefense.controller;

import java.net.URL;
import java.util.ResourceBundle;

import byteDefense.model.tiles.TileMap;
import byteDefense.view.TileMapView;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.layout.TilePane;

public class Controller implements Initializable {
	
	private TileMap map;
	
    @FXML
    private TilePane gameBoard;
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.map = new TileMap();
		TileMapView tmv = new TileMapView(map, gameBoard);
	}
	
    
}
