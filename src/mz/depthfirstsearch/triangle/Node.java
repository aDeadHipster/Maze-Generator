package mz.depthfirstsearch.triangle;

import java.util.ArrayList;

/**
 * Created by Justin on 3-2-2017.
 */
public class Node {

	public int r;
	public int c;

	public int x;
	public int y;

	public int length;
	public int height;

	public boolean up;

	public boolean[] walls;


	public ArrayList<Node> neighbors = new ArrayList<>();
	public boolean visited;

	public Node(int r, int c, int length, boolean up) {
		this.r = r;
		this.c = c;

		this.length = length;
		this.height = (int) Math.sqrt(Math.pow(this.length, 2) - Math.pow(0.5 * this.length, 2));

		// Set the x of each triangle
		if (c < Math.floor((1 + 2 * r) / 2)) {
			this.x = (Grid.centerX - length / 2) - (length / 2) * (r - c);
		} else if (c == Math.floor((1 + 2 * r) / 2)) {
			this.x = (Grid.centerX - length / 2);
		} else {
			this.x = (Grid.centerX - length / 2) + (length / 2) * (c - r);
		}

		this.y = r * height;

		// left, right, up/down
		this.walls = new boolean[]{true, true, true};
		this.visited = false;
		this.up = up;
	}

	public void setNeighbor(Node neighbor) {
		if (!this.neighbors.contains(neighbor)) {
			this.neighbors.add (neighbor);
		}

	}
}
