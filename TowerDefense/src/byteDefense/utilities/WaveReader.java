/*
 * WaveReader.java
 * Cette classe gere la recuperation des donnees des Waves (vagues d'ennemis),ses responsabilites sont de :
 * - lire le fichier stockant les informations pour chaque Wave
 * - construire une Wave a partir des donnees (moveSpeed, spawnRate et ennemyInfos de la pile) d'une ligne lue
 * - empiler les ennemis (WaveEnemies) a ajouter dans une pile pour la construction des Waves
 * - construire la liste des Waves du jeu en fonction des Waves construites
 */

package byteDefense.utilities;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import byteDefense.model.enemies.Wave;
import byteDefense.model.enemies.WaveEnemy;

public class WaveReader {
	
	private static void addWaveEnemies(Stack<WaveEnemy> wavesEnemies, int ennemyType, int ennemyQty) {
		wavesEnemies.push(new WaveEnemy(ennemyType, ennemyQty));
	}
	
	// Fonction qui renvoie une Wave construite à partir des donnees lues pour une ligne du fichier, en la decoupant 
	private static Wave createWaveInstance(String waveLine, int waveNumber) {
		String[] waveDatas = waveLine.split(";"); // Decoupage des donnees recuperees
		Stack<WaveEnemy> waveEnemies = new Stack<>(); 
		int moveSpeed = 0, spawnRate = 0, waveDatasLength = waveDatas.length;

		// la ligne ne contient pas toutes les donnees necessaires pour la construction d'une Wave
		if(waveDatasLength < 3)
			return null;
		
		// Parcours des donnes decoupees
		for (int i = 0; i < waveDatasLength; i++) {
			switch (i) {
			case 0:
				moveSpeed = Integer.parseInt(waveDatas[i]); 
				break;
			case 1:
				spawnRate = Integer.parseInt(waveDatas[i]); 
				break;
			default:
				// Decoupage des informations liees a chaque type d'ennemi a ajouter
				String[] ennemyInfo = waveDatas[i].split(",");
				addWaveEnemies(waveEnemies, Integer.parseInt(ennemyInfo[0]), Integer.parseInt(ennemyInfo[1]));
			}
		}
		return new Wave(waveNumber, moveSpeed, spawnRate, waveEnemies);
	}
	
	// Fonction qui retourne une liste des vagues contruites par récupération des donnees d'un fichier
	public static ArrayList<Wave> generateWaves(String sourceFile) {
		ArrayList<Wave> waves = new ArrayList<>();
		Wave wave;
		String waveLine; // ligne du fichier contenant les donnees d'une vague precise
		int waveNumber = 0;
		
		try {
			List<String> allLines = Files.readAllLines(Paths.get(sourceFile));
			int fileSize = allLines.size();
			
			// recuperation dans le fichier la ligne correspondant au numero de la vague
			while (waveNumber < fileSize && (waveLine = allLines.get(waveNumber)) != null) {
				wave = createWaveInstance(waveLine, waveNumber + 1);
				
				if (wave != null)
					waves.add(wave);
				
				waveNumber++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return waves;
	}
}
