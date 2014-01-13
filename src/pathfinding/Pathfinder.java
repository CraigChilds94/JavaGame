package pathfinding;

import java.util.ArrayList;

public class Pathfinder {
	
	public int width, height;
	public ArrayList<Node> nodes;
	public Node first, starting, target;
	
	
	public Pathfinder(int width, int height) {
		this.width = width;
		this.height = height;
		nodes = new ArrayList<Node>();
	}
	
	public void generateNodes() {
		Node prev = null;
		Node above = null;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				Node n = new Node();
				if(j > 0) {
					n.west = prev;
					prev.east = n;
					
					if(i > 0) {
						above = nodes.get((i * width) + j - (i * height));
						n.north = above;
						above.south = n;
					}
				}
				
				nodes.add(n);
				prev = n;
			}
		}
		
		first = nodes.get(0);
	}
	
	public void findPath(Node start, Node end) {
		//TODO: Finish the rest, currently only have h, need to find g and f
		starting = start;
		target = end;
		
		int index = nodes.indexOf(target);
		int x = (index / 4);
		int y = (index - ((index / 4) * 4));
		
		for(Node n : nodes) {
			if(n != target && n.walkable) {
				int a = nodes.indexOf(n);
				int i = x - (a / 4);
				int j = y - (a - ((a / 4) * 4));
				n.h = (Math.abs(j) + Math.abs(i));
				System.out.println(n.h);
			}
		}
		System.out.println(x + " : " + y);
	}
}
