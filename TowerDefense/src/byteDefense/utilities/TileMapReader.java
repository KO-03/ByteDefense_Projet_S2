/*
 * TileMapReader.java
 * Cette classe représente un objet TileMapReader, sa responsabilite est de :
 * - lire un fichier contenant une map de tile
 */

package byteDefense.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class TileMapReader {
	
	private String source;
	
	public TileMapReader(String source) {
		this.source = source;
	}
	
	public ArrayList<Integer> readFile() {
		ArrayList<Integer> tilesList = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(source));
		    String line = null;
		    
		    while ((line = br.readLine()) != null) {
		    	String[] values = line.split(", ");
		    	for (int i = 0; i < values.length; i++)
		    		tilesList.add(Integer.parseInt(values[i]));
		    }
		    br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tilesList;
	}
}
