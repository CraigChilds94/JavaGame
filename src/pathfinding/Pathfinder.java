package pathfinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Pathfinder {
	
	public int size;
	public Node[][] nodes;
	public ArrayList<Node> open, closed, path;
	public Node first, starting, target;
	public boolean pathable = true;
	
	/**
	 * Create a new pathfinder object
	 * @param size - the number of nodes will be size*size
	 */
	public Pathfinder(int size) {
		this.size = size;
		nodes = new Node[size][size];
		open = new ArrayList<Node>();
		closed = new ArrayList<Node>();
		path = new ArrayList<Node>();
	}
	
	/**
	 * Set the walkable state of a node in the list
	 * @param y - y of the node
	 * @param x - x of the node
	 * @param f - true or false
	 */
	public void setWalkable(int y, int x, Boolean f) {
		// if the node exists
		if(nodes[y] != null && nodes[y][x] != null) {
			nodes[y][x].walkable = f;
		}
	}
	
	/**
	 * Fill 'nodes' with new nodes and join them together for traversal
	 * Uses 2D array
	 */
	public void createAndLinkNodes() {
		// loop until end is reached
		for(int row = 0; row < size; row++) {
			for(int column = 0; column < size; column++) {
				// Create a new node and set it's x and y for future use
				Node n = new Node();
				n.y = row;
				n.x = column;
				
				linkNode(n.y, n.x, n);
			}
		}
	}
	
	/**
	 * Link a collection of existing nodes
	 * @param list
	 */
	public void linkNodes(Collection<Node> list) {
		for(Node n : list) {
			linkNode(n.y, n.x, n);	
		}
	}
	
	/**
	 * Link a node to it's adjacent nodes and add it to the list
	 * @param row
	 * @param column
	 * @param node
	 */
	private void linkNode(int row, int column, Node n) {
		// add it to the 2d array
		nodes[row][column] = n;
		
		// If we're no longer on the first row we need to set north and south adjacents
		if(row > 0) {
			nodes[row - 1][column].south = nodes[row][column];
			nodes[row][column].north = nodes[row - 1][column];
		}
		
		// If we're not on the first column we need to set west and east adjacents
		if(column > 0) {
			nodes[row][column - 1].east = nodes[row][column];
			nodes[row][column].west = nodes[row][column - 1];
		}
	}
	
	/**
	 * Calculate the distance between two nodes
	 * @param y1 - y of node 1
	 * @param x1 - x of node 1
	 * @param y2 - y of node 2
	 * @param x2 - x of node 2
	 * @return The distance
	 */
	public int calcHeuristic(int y1, int x1, int y2, int x2) {
		return (Math.abs(y1 - y2) + Math.abs(x1 - x2));
	}
	
	/**
	 * Find the shortest path between two nodes
	 * @param y - y of start
	 * @param x - x of start
	 * @param a - y of end
	 * @param b - x of end
	 * @return An array-list of all nodes in the path if a path exists, null otherwise
	 */
	public ArrayList<Node> findPath(int y, int x, int a, int b) {
		// find our start and end nodes in the array
		starting = nodes[y][x];
		target = nodes[a][b];
		
		// Initialise the empty lists
		path = new ArrayList<Node>();
		closed = new ArrayList<Node>();
		open = new ArrayList<Node>();
		
		// Calculate the heuristic and the f value for the starting node
		starting.h = calcHeuristic(starting.y, starting.x, target.y, target.x);
		starting.f = starting.g + starting.h;
		
		// Add the start node to the open list
		open.add(0, starting);
		
		// Loop until the open list is empty
		Node current = null;
		while(!open.isEmpty()) {
			// Get the node from the open list (lowest f is at index 0)
			current = open.get(0);
			
			// If it's the target we've found a path
			if(current == target) {
				Node n = target;
				while(n != null) {
					path.add(n);
					n = n.parent;
				}
				return path;
			}
			
			// Swap it to the closed list becuase we've seen it
			open.remove(current);
			closed.add(current);
			
			// Get all adjacent nodes
			Node[] adjs = new Node[]{
				current.north, current.east, current.south, current.west	
			};
			
			// For each adjacent node
			for(Node n : adjs) {
				
				// If we've already seen it, or its null or not walkable go to the next one
				if(closed.contains(n) || n == null || !n.walkable) {
					continue;
				}
				
				// Calc the g of this node
				int g = current.g + current.movecost;
				
				// Assign the g
				n.g = g;
				
				// Calculate the heuristic and the f values
				n.h = calcHeuristic(n.y, n.x, target.y, target.x);
				n.f = n.g + n.h;
				
				// If this node doesn't have a parent, or it's path is better than previous, set it's parent to the current node
				if(current.g + current.movecost < n.g || n.parent == null) {
					n.parent = current;
					
					// Position the node in the open list sorted by f value
					int i = 0;
					while(i < open.size() && !(n.f < open.get(i).f)) {
						i++;
					}
					open.add(i,n);
				}
			}
		}
		
		// If there's no path return null
		return null;
	}
	
	public ArrayList<Node> getRandomPath(int startX, int startY) {
		Random ran = new Random();
		int x = ran.nextInt(this.size) / 32;
		int y = ran.nextInt(this.size) / 32;
		if(nodes[y][x] != null) {
			System.out.println(nodes[y][x].walkable + " - " + y + " : " +x );
			if(nodes[y][x].walkable) {
				ArrayList<Node> p = this.findPath(startY, startX, y, x);
				System.out.println(x + " : " + y);
				System.out.println(p);
				System.out.println();
				if(p != null) {
					path = p;
				}
			}
		}
		return path;
	}
}
