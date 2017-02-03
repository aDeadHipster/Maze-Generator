package mz.depthfirstsearch;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Justin Praas
 */
public class Node {

	public static Color bgColorUnvisited = Color.LIGHT_GRAY;
	public static Color bgColorVisited = Color.WHITE;
	public static Color wallColor = Color.DARK_GRAY;
	public static Color currentColor = Color.RED;
	public static Color solvedColor = Color.GREEN;

	public int x;
	public int y;

	public int c;
	public int r;

	public int width;
	public int height;

	// up, right, down, left
	public boolean[] walls;

	public boolean visited;

	public ArrayList<Node> neighbors;

	public Node(int c, int r, Grid grid) {
		this.c = c;
		this.r = r;

		this.width = grid.width/grid.columns;
		this.x = c * width;

		this.height = grid.height/grid.rows;
		this.y = r * height;

		this.walls = new boolean[]{true, true, true, true};
		this.visited = false;

		this.neighbors = new ArrayList<>();

	}

	public void setNeighbor(Node n) {
		if (!neighbors.contains(n)) {
			neighbors.add(n);
		}
	}
}
