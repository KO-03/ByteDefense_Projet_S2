package byteDefense.utilities;

import java.util.*;

import byteDefense.model.TileMap;
import javafx.geometry.Point2D;

public class BFS {
	private int V;
	private LinkedList<Integer> adj[]; //liste des tiles voisines, remplie avec fill graph
	public  ArrayList<Point2D> pathList =  new ArrayList<>();
	public ArrayList<Integer> bfsPath  =  new ArrayList<>();// ordre du bfs apres algo
	public int ARRIVAL_POINT = 0;
	
	public BFS(int v) {
		V = v;
		adj = new LinkedList[v];
		
		for (int i = 0; i < v; ++i)
			adj[i] = new LinkedList<Integer>();
	}

	private void addEdge(int v, int w) {
		adj[v].add(w);
	}
	
	public static boolean isWalkable(int index) {//not here
		return index == 2 || index == 3 ||  index == 5;
	}
	
	public  void createPathList(TileMap map) {
		int tilesIndex = 0;
		for(int i = 0; i < map.getTilesList().size()-1; i++) {
			tilesIndex = map.getTilesList().get(i);
			if(isWalkable(tilesIndex)) {
				pathList.add(new Point2D(map.tilePosX(i), map.tilePosY(i)));
			}
		}
	}

	public void fillGraph() {
		for (int i = 0; i < this.adj.length; i++) {
			System.out.println(i+ ",  " + this.adj.length);
			int [] voisins = fillNeightbours(pathList.get(i));
			for(int j = 0; j < voisins.length; j++) {
				if(voisins[j]!=-1)
					this.addEdge(i, voisins[j]);
			}
		}
	}
	
	public int[] fillNeightbours(Point2D index) {
		int x = (int) index.getX();
		int y = (int) index.getY();
		
		int[] neightbours = new int[] {
				neightboursTileIndex(x, y-1), 
				neightboursTileIndex(x+1, y), 
				neightboursTileIndex(x, y+1), 
				neightboursTileIndex(x-1, y)
		};	
		return neightbours;
	}
	
	public int neightboursTileIndex(int x, int y) {
		Point2D check  = new Point2D(x, y);
		for (int i = 0; i < pathList.size(); i++) {
			if( check.getX() == pathList.get(i).getX() && check.getY() == pathList.get(i).getY()) {
				return i;
			}
		}
		return -1;
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