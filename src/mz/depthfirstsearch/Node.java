package mz.depthfirstsearch;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Justin Praas
 */
public class Node {

	public Color bgColorVisited;
	public Color bgColorUnvisited;
	public Color wallColor;
	public Color currentColor;

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

		bgColorUnvisited = Color.LIGHT_GRAY;
		bgColorVisited = Color.WHITE;
		wallColor = Color.DARK_GRAY;
		currentColor = Color.RED;

	}

	public void setNeighbor(Node n) {
		if (!neighbors.contains(n)) {
			neighbors.add(n);
		}
	}
}
