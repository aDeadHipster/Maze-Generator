package mz.depthfirstsearch.triangle;

import java.util.ArrayList;

/**
 * @author Justin Praas
 */
public class Grid {

	public View view;

	public int width;
	public int height;

	public int rows;

	public ArrayList<Node[]> nodes = new ArrayList<>();;

	public static int centerX;
	public ArrayList<Node> unvisitedNodes = new ArrayList<>();
	public Node start;
	public Node goal;

	public DepthFirstSearch dfs;

	public Grid(int width, int height, int rows) throws InterruptedException {
		this.width      = width;
		this.height     = height;
		this.rows       = rows;

		centerX = width/2;

		for (int r = 0; r < rows; r++) {
			nodes.add(new Node[1 + 2 * r]);
			for (int c = 0; c < (1 + 2 * r); c++) {
				nodes.get(r)[c] = new Node(r, c, width/rows, c % 2 == 0);
				unvisitedNodes.add(nodes.get(r)[c]);
			}
		}

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < (1 + 2 * r); c++) {
				assignNeighbors(r, c);
			}
		}

		this.start = nodes.get(rows - 1)[0];
		this.view = new View(width, height, this);
		this.goal = nodes.get(0)[0];
		this.dfs = new DepthFirstSearch(start, this);
		dfs.depthFirstSearch(this.start, this);

	}

	private void assignNeighbors(int r, int c) {
		if (c != 0) {
			nodes.get(r)[c].setNeighbor(nodes.get(r)[c - 1]);
		}

		if (c != (1 + 2 * r) - 1) {
			nodes.get(r)[c].setNeighbor(nodes.get(r)[c + 1]);
		}

		if (r != rows - 1 && c % 2 == 0) {
			nodes.get(r)[c].setNeighbor(nodes.get(r + 1)[c + 1]);
		}

		if (r != 0 && c % 2 != 0) {
			nodes.get(r)[c].setNeighbor(nodes.get(r - 1)[c - 1]);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Grid grid = new Grid(400, 400, 19);
	}
}
