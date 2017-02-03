package mz.depthfirstsearch;

import java.util.ArrayList;

/**
 * @author Justin Praas
 */
public class Grid {

	public View view;

	public int width;
	public int height;

	public int columns;
	public int rows;

	public Node[][] nodes;

	public Node start;
	public Node goal;

	public ArrayList<Node> unvisitedNodes;
	public DepthFirstSearch dfs;
	public ArrayList<Node> solution;

	public static void main(String[] args) throws InterruptedException {
		Grid grid = new Grid(800, 800, 100, 100);
	}

	public Grid(int width, int height, int columns, int rows) throws InterruptedException {
		this.width      = width;
		this.height     = height;
		this.columns    = columns;
		this.rows       = rows;
		this.nodes      = new Node[columns][rows];
		this.unvisitedNodes = new ArrayList<>();

		for (int c = 0; c < columns; c++) {
			for (int r = 0; r < rows; r++) {
				nodes[c][r] = new Node(c, r, this);
				unvisitedNodes.add(nodes[c][r]);
			}
		}

		for (int c = 0; c < columns; c++) {
			for (int r = 0; r < rows; r++) {
				assignNeighbors(c, r);
			}
		}

		this.start      = nodes[0][0];
		this.dfs        = new DepthFirstSearch(start, this);
		this.view       = new View(width, height, this);
		this.goal       = dfs.depthFirstSearch(goal, this);
		this.solution   = dfs.solve();
		view.repaint();
	}

	private void assignNeighbors(int i, int j) {
		// Find and assign all neighbors (WITH DIAGONALS!!!)
//		for (int c = Math.max(0, i - 1); c <= Math.min(i + 1, columns - 1); c++) {
//			for (int r = Math.max(0, j - 1); r <= Math.min(j + 1, rows - 1); r++) {
//				if (c != i || r != j) {
//					nodes[i][j].setNeighbor(nodes[c][r]);
//				}
//			}
//		}

		// Find neighbors (left, up, right, down)
		if (i != 0) {
			nodes[i][j].setNeighbor(nodes[i - 1][j]);
		}
		if (j != 0) {
			nodes[i][j].setNeighbor(nodes[i][j - 1]);
		}
		if (i != columns - 1) {
			nodes[i][j].setNeighbor(nodes[i + 1][j]);
		}
		if (j != rows - 1) {
			nodes[i][j].setNeighbor(nodes[i][j + 1]);
		}
	}
}
