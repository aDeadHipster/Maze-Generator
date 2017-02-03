/**
 * @author Justin Praas
 */
public class Grid {

	public int width;
	public int height;

	public int columns;
	public int rows;

	public Node[][] nodes;

	public Grid(int width, int height, int columns, int rows) {
		this.width      = width;
		this.height     = height;
		this.columns    = columns;
		this.rows       = rows;
		this.nodes      = new Node[columns][rows];


		for (int c = 0; c < columns; c++) {
			for (int r = 0; r < rows; r++) {
				nodes[c][r] = new Node(c, r, this);
			}
		}
	}
}
