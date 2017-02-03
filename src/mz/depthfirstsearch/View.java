package mz.depthfirstsearch;

import javax.swing.*;
import java.awt.*;

/**
 * @author Justin Praas
 */
public class View extends JPanel {

	private Grid grid;

	public View(int width, int height, Grid grid) {
		Dimension d = new Dimension(width + 1, height + 1);
		this.setPreferredSize(d);
		this.grid = grid;
		setup();
	}

	public void setup() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(Color.DARK_GRAY);
		frame.add(this);
		frame.setVisible(true);
		frame.pack();
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;

		for (int c = 0; c < grid.columns; c++) {
			for (int r = 0; r < grid.rows; r++) {
				Node node = grid.nodes[c][r];

				if (node.equals(grid.dfs.current) ) {
					g2.setColor(node.currentColor);
				} else if (node.visited) {
					g2.setColor(node.bgColorVisited);
				} else {
					g2.setColor(node.bgColorUnvisited);
				}

				g2.fillRect(node.x, node.y, node.width, node.height);

				g2.setColor(node.wallColor);
				// Top wall
				if (node.walls[0]) {
					g2.drawLine(node.x, node.y, node.x + node.width, node.y);
				}

				// Right wall
				if (node.walls[1]) {
					g2.drawLine(node.x + node.width, node.y, node.x + node.width, node.y + node.height);
				}

				// Bottom wall
				if (node.walls[2]) {
					g2.drawLine(node.x, node.y + node.height, node.x + node.width, node.y + node.height);
				}

				// Left wall
				if (node.walls[3]) {
					g2.drawLine(node.x, node.y, node.x, node.y + node.height);
				}
			}
		}
	}

}
