package mz.depthfirstsearch;

import java.util.ArrayList;

/**
 * @author Justin Praas
 */
public class DepthFirstSearch {

	public Node current;
	public Grid grid;
	public ArrayList<Node> stack = new ArrayList<>();

	public DepthFirstSearch(Node first, Grid grid) {
		this.grid = grid;
		this.current = first;
		current.visited = true;
		grid.unvisitedNodes.remove(current);
//		for(mz.depthfirstsearch.Node node : current.neighbors) {
//			node.neighbors.remove(current);
//		}
	}

	public void depthFirstSearch() throws InterruptedException {

		while (grid.unvisitedNodes.size() > 0) {
			//Thread.sleep(1);

			ArrayList<Node> unvisitedNeighbors = unvisitedNeighbors(grid.unvisitedNodes, current);

			if (unvisitedNeighbors.size() > 0) {

				// Step 1
				int rand = (int)Math.floor(Math.random() * unvisitedNeighbors.size());
				Node next = unvisitedNeighbors.get(rand);
				//current.neighbors.remove(next);

				// Step 2
				stack.add(current);

				// Step 3
				removeWalls(current, next);

				// Step 4
				next.neighbors.remove(current);
				current = next;
				current.visited = true;
				grid.unvisitedNodes.remove(current);
			} else if (stack.size() >  0) {
				current = stack.get(stack.size()-1);
				stack.remove(current);
			}

			grid.view.repaint();
		}

		System.out.println("DONE");
	}

	private void removeWalls(Node current, Node next) {
		// Next below current
		if (current.y < next.y) {
			current.walls[2]    = false;
			next.walls[0]       = false;
		}

		if (current.y > next.y) {
			current.walls[0]    = false;
			next.walls[2]       = false;
		}

		if (current.x < next.x) {
			current.walls[1]    = false;
			next.walls[3]       = false;
		}

		if (current.x > next.x) {
			current.walls[3]    = false;
			next.walls[1]       = false;
		}
	}

	public ArrayList<Node> unvisitedNeighbors(ArrayList<Node> unvisitedNodes, Node node) {
		ArrayList<Node> unvisitedNeighborsList = new ArrayList<>();

		for (Node n : unvisitedNodes) {
			if(node.neighbors.contains(n)) {
				unvisitedNeighborsList.add(n);
			}
		}
		return unvisitedNeighborsList;
	}

}
