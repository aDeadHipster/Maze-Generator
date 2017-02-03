package mz.depthfirstsearch;

import java.util.*;

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

	public void depthFirstSearch(Node goal, Grid grid) throws InterruptedException {

		while (this.grid.unvisitedNodes.size() > 0) {
			//Thread.sleep(2);

			ArrayList<Node> unvisitedNeighbors = unvisitedNeighbors(this.grid.unvisitedNodes, current);

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
				this.grid.unvisitedNodes.remove(current);
			} else if (stack.size() >  0) {
				current = stack.get(stack.size()-1);
				stack.remove(current);
			}

			this.grid.view.repaint();
		}

		System.out.println("DONE: " + current.c + ", " + current.r);
	}

	public ArrayList<Node> solve() {
		Node current = null;

		Set<Node> closedSet = new HashSet<>();
		Set<Node> openSet = new HashSet<>();
		openSet.add(grid.start);

		Map<Node, Node> cameFrom = new HashMap<>();

		Map<Node, Double> gScore = new HashMap<>();
		Map<Node, Double> fScore = new HashMap<>();

		for (int c = 0; c < grid.columns; c++) {
			for (int r = 0; r < grid.rows; r++) {
				gScore.put(grid.nodes[c][r], (double)Integer.MAX_VALUE);
				fScore.put(grid.nodes[c][r], (double)Integer.MAX_VALUE);
			}
		}

		gScore.put(grid.start, 0.0);
		fScore.put(grid.start, heuristicCost(grid.start, grid.goal));

		while (openSet.size() > 0) {
			current = getLowestValue(openSet, fScore);

			if (current.equals(grid.goal)) {
				System.out.println("SOLVED");
				return reconstructPath(cameFrom, current);
			}

			openSet.remove(current);
			closedSet.add(current);

			for (Node neighbor : current.neighbors) {
				if (closedSet.contains(neighbor) || wallIsUp(current, neighbor)) {
					continue;
				}

				double temp_gScore = gScore.get(current) + 1;

				if (!closedSet.contains(neighbor) && !wallIsUp(current, neighbor)) {
					openSet.add(neighbor);
				} else if (temp_gScore >= gScore.get(neighbor)) {
					continue;
				}

				cameFrom.put(neighbor, current);
				gScore.put(neighbor, temp_gScore);
				fScore.put(neighbor, gScore.get(neighbor) + heuristicCost(neighbor, grid.goal));
			}
		}
		System.out.println("FAILED");
		return reconstructPath(cameFrom, current);
	}

	private boolean wallIsUp(Node current, Node next) {
		// Next below current
		if (current.y < next.y) {
			return !(current.walls[2] == false);
		}

		if (current.y > next.y) {
			return !(current.walls[0] == false);
		}

		if (current.x < next.x) {
			return !(current.walls[1] == false);
		}

		if (current.x > next.x) {
			return !(current.walls[3] == false);
		}

		System.out.println("NOT SO SURE if the wall is up");
		return false;
	}

	private ArrayList<Node> reconstructPath(Map<Node, Node> cameFrom, Node current) {
		ArrayList<Node> totalPath = new ArrayList<>();
		totalPath.add(current);

		while (cameFrom.containsKey(current)) {
			current = cameFrom.get(current);
			totalPath.add(current);
		}

		return totalPath;
	}

	private Node getLowestValue(Set<Node> set, Map<Node, Double> map) {
		Node resultNode = null;
		double lowest = (double) Integer.MAX_VALUE;

		Iterator<Node> it = set.iterator();
		while (it.hasNext()) {
			Node n = it.next();
			if (resultNode == null || map.get(n) < lowest) {
				resultNode = n;
				lowest = map.get(n);
			}
		}
		return resultNode;
	}

	private Double heuristicCost(Node from, Node to) {
		return Math.sqrt(
				Math.pow(Math.abs(from.c - to.c), 2) +
						Math.pow(Math.abs(from.r - to.r), 2));
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
