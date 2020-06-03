/*
 * WaveReader.java
 * Les responsabilites de cette classe sont de :
 * - lire le fichier fichier stockant les informations pour chaque Wave
 * - construire une Wave à partir des données (moveSpeed, spawnRate et ennemyInfos de la pile) de la ligne lue correspondant au numéro de la vague
 * - empiler les ennemis (WaveEntities) à ajouter dans une pile pour la construction des Waves
 * - construire la liste des vagues du jeu en fonction des Waves construites
 */

package byteDefense.utilities;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import byteDefense.model.ennemies.Wave;
import byteDefense.model.ennemies.WaveEntities;

public class WaveReader {
	
	private static void addWaveEntity(Stack<WaveEntities> wavesEntities, int ennemyType, int ennemyQty) {
		wavesEntities.push(new WaveEntities(ennemyType, ennemyQty));
	}
	
	// Fonction qui renvoie une Wave construite à partir des donnees lues pour une ligne du fichier, en la decoupant 
	private static Wave createWaveInstance(String waveLine, int waveNumber) {
		String[] waveDatas = waveLine.split(";"); // Decoupage des donnees recuperees
		Stack<WaveEntities> waveEntities = new Stack<>(); 
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
				addWaveEntity(waveEntities, Integer.parseInt(ennemyInfo[0]), Integer.parseInt(ennemyInfo[1]));
			}
		}
		return new Wave(waveNumber, moveSpeed, spawnRate, waveEntities);
	}
	
	public static ArrayList<Wave> generateWaves(String sourceFile) {
		ArrayList<Wave> waves = new ArrayList<>();
		int waveNumber = 0;
		String waveLine;
		Wave wave;
		
		try {
			List<String> allLines = Files.readAllLines(Paths.get(sourceFile));
			int fileSize = allLines.size();
			
			// recuperation dans le fichier des informations de la vague la ligne correspondant au numero de la vague
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
