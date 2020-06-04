/*
 * GameAreaReader.java
 * Cette classe construit la liste des tiles du jeu par la lecture d'un fichier contenant les donnees correspondantes 
 */

package byteDefense.utilities;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameAreaReader {
	
	// Fonction qui renvoie une tilesList construite à partir des donnees lues pour une ligne du fichier, en la decoupant 
	private static ArrayList<Integer> createTilesList(String levelLine, int  level) {
		ArrayList<Integer> tilesList = new ArrayList<>();
		String[] tilesDatas = levelLine.split(", "); // Decoupage des donnees recuperees
		
		for (int i = 0; i < tilesDatas.length; i++)
			tilesList.add(Integer.parseInt(tilesDatas[i]));
		
		return tilesList;
	}
	
	public static ArrayList<ArrayList<Integer>> generateLevelsTilesList(String sourceFile) {
		ArrayList<ArrayList<Integer>> levelsTilesList = new ArrayList<>();
		int level = 0;
		String levelLine;
		ArrayList<Integer> tilesList;
		
		try {
			List<String> allLines = Files.readAllLines(Paths.get(sourceFile));
			int fileSize = allLines.size();
			
			// recuperation dans le fichier la ligne correspondant au niveau
			while (level < fileSize && (levelLine = allLines.get(level)) != null) {
				tilesList = createTilesList(levelLine, level + 1);
				
				if (tilesList != null)
					levelsTilesList.add(tilesList);
				
				level++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return levelsTilesList;
	}
	
	/*
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
	}*/
}
