/*
 * GameAreaReader.java
 * Cette classe gere la recuperation des donnees du plateau du jeu,ses responsabilites sont de :
 * - lire le fichier stockant les informations pour chaque niveau
 * - construire une tilesList a partir des donnees d'une ligne lue
 * - construire la liste de chaque niveau du jeu en fonction des tilesList construites
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
	
	// Fonction qui retourne la liste des tilesList contruites par récupération des donnees d'un fichier
	public static ArrayList<ArrayList<Integer>> generateLevelsTilesList(String sourceFile) {
		ArrayList<ArrayList<Integer>> levelsTilesList = new ArrayList<>();
		ArrayList<Integer> tilesList;
		String levelLine; // ligne du fichier contenant les donnees d'un niveau precis
		int level = 0;
		
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
}
