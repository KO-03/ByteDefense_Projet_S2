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
	
	/* Fonction qui retourne la liste des tilesList contruites par récupération des donnees d'un fichier tiles.txt.
	 * Ces donnees sont des entiers correspondants chacun à une tuile precise du plateau de jeu sont separees par un ", ".
	 * Une ligne du fichier correspon au niveau du jeu
	 * Exemple d'une ligne du fichier : "7, 4, 5, 2, 2, 2, 5, 7"
	 * La correspondance entre chaque donnee du plateau de jeu et sa composante visuelle est decrite dans 
	 * la classe de vue GameAreaView.
	 */
	public static ArrayList<ArrayList<Integer>> generateLevelsTilesList(String sourceFile) {
		ArrayList<ArrayList<Integer>> levelsTilesList = new ArrayList<>();
		ArrayList<Integer> tilesList;
		String levelLine; // ligne du fichier contenant les donnees d'un niveau precis
		int level = 0;
		
		try {
			List<String> allLines = Files.readAllLines(Paths.get(sourceFile));
			int fileSize = allLines.size();
			
			// Traitements des donnees du fichier ligne par ligne et construction des listes de tuiles
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
