package pathfinding;

public class Node {
	
	public int h, g, f;
	public Node north, east, south, west;
	public boolean walkable = true;
	
	public Node() {
		h = 0;
		g = 0;
		f = 0;
	}
}
