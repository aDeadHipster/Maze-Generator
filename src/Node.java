import java.awt.*;

/**
 * @author Justin Praas
 */
public class Node {

	public Color bgColor;
	public Color wallColor;

	public int x;
	public int y;

	public int c;
	public int r;

	public int width;
	public int height;

	// up, right, down, left
	public boolean[] walls;

	public Node(int c, int r, Grid grid) {
		this.c = c;
		this.r = r;

		this.width = grid.width/grid.columns;
		this.x = c * width;

		this.height = grid.height/grid.rows;
		this.y = r * height;

		this.walls = new boolean[]{true, true, true, true};

		bgColor = Color.LIGHT_GRAY;
		wallColor = Color.DARK_GRAY;

	}
}
