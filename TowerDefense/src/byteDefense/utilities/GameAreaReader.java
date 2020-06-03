/*
 * GameAreaReader.java
 * Cette classe construit la liste des tiles du jeu par la lecture d'un fichier contenant les donnees correspondantes 
 */

package byteDefense.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class GameAreaReader {

	public static ArrayList<Integer> readFile(String sourceFile) {
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
