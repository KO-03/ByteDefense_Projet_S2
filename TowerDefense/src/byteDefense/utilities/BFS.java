package byteDefense.utilities;

import java.util.*;

import byteDefense.model.TileMap;
import javafx.geometry.Point2D;

public class BFS {
	private int V;
	private LinkedList<Integer> adj[]; //liste des tiles voisines, remplie avec fill graph
	public  ArrayList<Point2D> pathList =  new ArrayList<>();
	private ArrayList<Integer> tilesList;//map on peut la récup d'autre part
	public ArrayList<Integer> bfsPath  =  new ArrayList<>();// ordre du bfs après algo
	public int ARRIVAL_POINT = 0;
	
	
	public BFS(int v) {
		V = v;
		adj = new LinkedList[v];
		
		this.tilesList = new TileMapReader("./resources/tiles.txt").readFile();
		
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList<Integer>();
		
		
		this.createPathList(); // liste des chemins parcourables
		this.fillGraph(); // liste des voisins
		this.BFS_algo(ARRIVAL_POINT); // lancement de l'algo a partir de l'indice du point d'arrivee
	}

	void addEdge(int v, int w) {
		adj[v].add(w);
	}
	
	public static boolean isWalkable(int index) {
		return index == 2 || index == 3 ||  index == 5;
	}
	
	public  void createPathList() {
		int tilesIndex = 0;

		for(int i = 0; i < this.tilesList.size()-1; i++) {
			tilesIndex = this.tilesList.get(i);
			if(isWalkable(tilesIndex)) {
				pathList.add(new Point2D(getX(i), getY(i)));
			}
		}
	}

	public void fillGraph() {
		for (int i = 0; i < this.adj.length; i++) {
			System.out.println(i+ ",  " + this.adj.length);
			int [] voisins = findNeightbours(pathList.get(i));
			for(int j = 0; j < voisins.length; j++) {
				if(voisins[j]!=-1)
					this.addEdge(i, voisins[j]);
			}
		}
	}
	
	public int[] findNeightbours(Point2D index) {
		int x = (int) index.getX();
		int y = (int) index.getY();
		
		int[] neightbours = new int[] {
				tileCaseN(x, y-1), 
				tileCaseN(x+1, y), 
				tileCaseN(x, y+1), 
				tileCaseN(x-1, y)
		};	
		return neightbours;
	}
	
	public int tileCaseN(int x, int y) {
		Point2D check  = new Point2D(x, y);
		for (int i = 0; i < pathList.size(); i++) {
			if( check.getX() == pathList.get(i).getX() && check.getY() == pathList.get(i).getY()) {
				return i;
			}
		}
		return -1;
	}
	
	public static int getX(int pos) {
		return pos%TileMap.tilesSize;
	}

	public static int getY(int pos) {
		return pos/TileMap.tilesSize;
	}
	
	public void BFS_algo(int s) {

		boolean visited[] = new boolean[V];

		LinkedList<Integer> queue = new LinkedList<Integer>();

		visited[s] = true;
		queue.add(s);

		while (queue.size() != 0) {
			s = queue.poll();
			bfsPath.add(s);

			Iterator<Integer> i = adj[s].listIterator();
			while (i.hasNext()) {
				int n = i.next();
				if (!visited[n]) {
					visited[n] = true;
					queue.add(n);
				}
			}
		}
	}
}