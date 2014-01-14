package pathfinding;

public class Node {
	
	public int h, g, f, movecost, x, y;
	public Node north, east, south, west, parent;
	public boolean walkable = true;
	
	public Node() {
		h = 0;
		g = 0;
		f = 0;
		movecost = 10;
	}
}
