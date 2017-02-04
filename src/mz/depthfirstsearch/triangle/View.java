package mz.depthfirstsearch.triangle;

import mz.depthfirstsearch.triangle.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
		//frame.setResizable(false);
		frame.setBackground(Color.DARK_GRAY);
		frame.add(this);
		frame.setVisible(true);
		frame.pack();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		for (int r = 0; r < grid.nodes.size(); r++) {
			for (int c = 0; c < grid.nodes.get(r).length; c++) {
				Node node = grid.nodes.get(r)[c];
				g2.setColor(Color.ORANGE);
				g2.setStroke(new BasicStroke(3));
				if (node.up) {
					if (node.walls[0]) {
						g2.drawLine(node.x, node.y + node.height, node.x + node.length / 2, node.y);

					}

					if (node.walls[1]) {
						g2.drawLine(node.x + node.length, node.y + node.height, node.x + node.length / 2, node.y);

					}

					if (node.walls[2]) {
						g2.drawLine(node.x, node.y + node.height, node.x + node.length, node.y + node.height);

					}
				} else {

					if (node.walls[0]) {
						g2.drawLine(node.x, node.y, node.x + node.length/2, node.y + node.height);

					}

					if (node.walls[1]) {
						g2.drawLine(node.x + node.length, node.y, node.x + node.length/2, node.y + node.height);

					}

					if (node.walls[2]) {
						g2.drawLine(node.x, node.y, node.x + node.length, node.y);
					}


				}
			}
		}

	}
}
