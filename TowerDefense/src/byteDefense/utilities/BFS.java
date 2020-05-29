/*
 * BFS.java
 * Cette classe contruit le graphe correspondant aux chemins et leurs voisins
 * et lance l'alogrithme du bfs pour founir le plus court chemin.
 */

package byteDefense.utilities;

import java.util.*;

import byteDefense.model.GameArea;
import javafx.geometry.Point2D;

public class BFS {

	public final int ARRIVAL_POINT = 0;
	
	private int verticeSize;
	private GameArea gameArea;
	private LinkedList<Integer> adj[]; //liste des tiles voisines, remplie avec fill graph
	public  ArrayList<Point2D> pathList =  new ArrayList<>();
	public ArrayList<Point2D> bfsPath = new ArrayList<>();// ordre du bfs apres algo

	public BFS(GameArea gameArea) {
		this.gameArea = gameArea;
		this.createPathList();
		this.verticeSize = this.pathList.size();
		this.adj = new LinkedList[this.pathList.size()];

		for (int i = 0; i < this.pathList.size(); ++i)
			this.adj[i] = new LinkedList<Integer>();

		this.fillGraph();	
		this.BFS_algo(ARRIVAL_POINT);
	}

	private void addEdge(int v, int w) {
	}

	private  void createPathList() {
		int tilesIndex = 0;
		
		for (int i = 0; i < this.gameArea.getTilesList().size() - 1; i++) {
			tilesIndex = this.gameArea.getTilesList().get(i);
			if (this.gameArea.isWalkable(tilesIndex)) {
				this.pathList.add(new Point2D(this.gameArea.tilePosX(i), this.gameArea.tilePosY(i)));
			}
		}
	}

	private void fillGraph() {
		for (int i = 0; i < this.adj.length; i++) {
			int [] neightbour = fillNeightbours(pathList.get(i));
			for (int j = 0; j < neightbour.length; j++) {
				if (neightbour[j]!=-1)
					this.addEdge(i, neightbour[j]);
			}
		}
	}

	private int[] fillNeightbours(Point2D index) {
		int x = (int) index.getX();
		int y = (int) index.getY();

		int[] neightbours = new int[] {
				neightboursTileIndex(x, y - 1), 
				neightboursTileIndex(x + 1, y), 
				neightboursTileIndex(x, y + 1), 
				neightboursTileIndex(x - 1, y)
		};	
		return neightbours;
	}

	private int neightboursTileIndex(int x, int y) {
		Point2D check  = new Point2D(x, y);
		for (int i = 0; i < this.pathList.size(); i++) {
			if (check.getX() == this.pathList.get(i).getX() && check.getY() == this.pathList.get(i).getY()) {
				return i;
			}
		}
		return -1;
	}

	public void BFS_algo(int s) {
		boolean visited[] = new boolean[this.verticeSize];

		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[s] = true;
		queue.add(s);
		bfsPath.add(this.pathList.get(s));

		while (queue.size() != 0) {
			s = queue.poll();
			
			Iterator<Integer> i = adj[s].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
					bfsPath.add(this.pathList.get(n));
				}
			}
		}
	}
}
