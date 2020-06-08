/*
 * WaveReader.java
 * Cette classe gere la recuperation des donnees des Waves (vagues d'ennemis),ses responsabilites sont de :
 * - lire le fichier stockant les informations pour chaque Wave
 * - construire une Wave a partir des donnees (moveSpeed, spawnRate et la liste waveEnemies d'ennemis de la vague) d'une ligne lue
 * - ajouter les ennemis d'un type (WaveEnemy) à une liste pour la construction des Waves
 * - construire la liste des Waves du jeu en fonction des Waves construites
 */

package byteDefense.utilities;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import byteDefense.model.enemies.Wave;
import byteDefense.model.enemies.WaveEnemy;

public class WaveReader {
	
	// Methode qui ajoute les ennemis d'un type (WaveEnemy) à une liste pour la construction des Waves
	private static void addWaveEnemies(ArrayList<WaveEnemy> wavesEnemies, int ennemyType, int ennemyQty) {
		wavesEnemies.add(new WaveEnemy(ennemyType, ennemyQty));
	}
	
	// Fonction qui renvoie une Wave construite à partir des donnees lues pour une ligne du fichier, en la decoupant 
	private static Wave createWaveInstance(String waveLine, int waveNumber) {
		String[] waveDatas = waveLine.split(";"); // Decoupage des donnees recuperees
		ArrayList<WaveEnemy> waveEnemies = new ArrayList<>(); 
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
	
	/* Fonction qui retourne une liste des vagues contruites par récupération des donnees du fichier "waves_informations.txt".
	 * Ces donnees sont des entiers, separees par des ";". Une ligne correspond a un numero de vague
	 * Exemple d'une ligne du fichier : "5;6;1,3;2,5"
	 * - "5" correspond a la vitesse de deplacement en nombre de tuile des ennemis de la vague
	 * - "6" correspond a la cadence d'apparition des ennemis par tour dans un vague
	 * - "1" et "2" correspondent aux types d'ennemis a faire apparaitre
	 * - "3" et "5" correspondent a la quantite d'ennemis d'un type a faire apparaitre
	 * La correspondance pour les types d'ennemis est decrite dans la classe EnemyFactory.
	 */
	public static ArrayList<Wave> generateWaves(String sourceFile) {
		ArrayList<Wave> waves = new ArrayList<>();
		Wave wave;
		String waveLine; // ligne du fichier contenant les donnees d'une vague precise
		int waveNumber = 0;
		
		try {
			List<String> allLines = Files.readAllLines(Paths.get(sourceFile));
			int fileSize = allLines.size();
			
			// Traitements des donnees du fichier ligne par ligne et construction des vagues
			while (waveNumber < fileSize && (waveLine = allLines.get(waveNumber)) != null) {
				wave = createWaveInstance(waveLine, waveNumber + 1);
				// La construction de la vague a pu etre realise
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
