/*
 * GameAreaReader.java
 * Cette classe represente un objet GameAreaReader, sa responsabilite est de :
 * - lire un fichier contenant une map de tile
 */

package byteDefense.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class GameAreaReader {
	
	private String sourceFile;
	
	public GameAreaReader(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	
	public ArrayList<Integer> readFile() {
		ArrayList<Integer> tilesList = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(sourceFile));
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
