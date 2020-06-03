/*
 * BFS.java
 * Cette classe explore la gameArea et fourni les chemins les plus courts vers le point de lancement
 * 
 */

package byteDefense.utilities;

import java.util.*;

import byteDefense.model.GameArea;
import javafx.geometry.Point2D;

public class BFS {

	public final int ARRIVAL_POINT = 28;// ne doit pas être ici
	private int verticeSize;
	int tilesIndex = 0;
	int gameAreaDimension;
	public GameArea gameArea;
	public int[] cameFrom;

	public BFS(GameArea gameArea) {
		this.gameArea = gameArea;
		this.gameAreaDimension = (int) Math.pow(this.gameArea.gameAreaSize(), 2);
		this.cameFrom = new int[gameAreaDimension];
		this.BFS_algo(this.ARRIVAL_POINT);	
	}

	private int[] foundNeightbours(int index) {
		int x = this.gameArea.tilePosX(index);
		int y = this.gameArea.tilePosY(index);	
		int up = this.gameArea.tileIndex(x, y-1);
		int right = this.gameArea.tileIndex(x + 1, y);
		int down = this.gameArea.tileIndex(x, y + 1);
		int left = this.gameArea.tileIndex(x - 1, y);
	
		int[] neightbours = new int[] {
				this.lookTileValue(up),
				this.lookTileValue(right), 
				this.lookTileValue(down), 
				this.lookTileValue(left)
		};	
		return neightbours;
	}
	
	private int lookTileValue(int value) {
		if(this.gameArea.isWalkable(this.gameArea.getTilesList().get(value))) 
			return value;
		else
			return -1;
	}
	
	private boolean isInCameFrom(int value) {
		for(int i : this.cameFrom) {
			if(i==value)
				return true;
		}
		return false;
	}

	public void BFS_algo(int start) {
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		queue.add(start);
		
		this.cameFrom[start] = -1;
	
		while (queue.size() != 0) {
			int current = queue.poll();
			int [] neightbour = this.foundNeightbours(current);
			int next;
			for(int i =0 ; i<neightbour.length; i++) {
				if(neightbour[i]!=-1) {
					next= neightbour[i];					
					if (!this.isInCameFrom(next)) {
						queue.add(next);
						this.cameFrom[next] = current;
					}
				}
			}
		}		
	}	
}
