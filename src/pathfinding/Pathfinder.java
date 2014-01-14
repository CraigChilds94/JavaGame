package pathfinding;

import java.util.ArrayList;

public class Pathfinder {
	
	public int width, height;
	public ArrayList<Node> nodes, open, closed, path;
	public Node first, starting, target;
	public boolean pathable = true;
	
	
	public Pathfinder(int width, int height) {
		this.width = width;
		this.height = height;
		nodes = new ArrayList<Node>();
		open = new ArrayList<Node>();
		closed = new ArrayList<Node>();
		path = new ArrayList<Node>();
	}
	
	public void setUnWalkable(int y, int x) {
		int index = ((y * height) + x - (y * width));
		Node n = nodes.get(index + (width * y));
		n.walkable = false;
	}
	
	public void generateNodes() {
		Node prev = null;
		Node above = null;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				Node n = new Node();
				n.x = j;
				n.y = i;
				if(j > 0) {
					n.west = prev;
					prev.east = n;
				}
				
				if(i > 0) {
					int index = ((i * height) + j - (i * width));
					above = nodes.get(index + (width * (i - 1)));
					n.north = above;
					above.south = n;
				}
				
				nodes.add(n);
				prev = n;
			}
		}
		
		first = nodes.get(0);
	}
	
	public void findPath(int start, int end) {
		starting = nodes.get(start - 1);
		target = nodes.get(end - 1);
		
		int index = nodes.indexOf(target);
		int x = (index / width);
		int y = (index - ((index / height) * height));
		
		for(Node n : nodes) {
			if(n != target && n.walkable) {
				int a = nodes.indexOf(n);
				int i = x - (a / width);
				int j = y - (a - ((a / height) * height));
				n.h = (Math.abs(j) + Math.abs(i));
			}
		}
		
		Node current = starting;
		while(pathable && current.h != 1) {
			System.out.println(current.h);
			Node[] adjs = new Node[]{
				current.north, current.east, current.south, current.west	
			};
			
			if(open.contains(current)) {
				open.remove(current);
			}
			
			closed.add(current);
			
			for(Node n : adjs) {
				if(n != null && !closed.contains(n) && n.walkable) {
					open.add(n);
					n.g = current.g + current.movecost;
					n.f = n.g + n.h;
					if(current.g + current.movecost < n.g || n.parent == null) {
						n.parent = current;
						//System.out.println(n  + " : - : " + current.parent);
						//System.out.println(n.parent  + " : - : " + current);
						//System.out.println("----------------------");
					}
				}
				
			}
			
			Node next = null;
			for(Node o : open) {
				if(next == null || o.f < next.f) {
					next = o;
				}
			}
			
			current = next;
			if(current == null) {
				pathable = false;
			}
		}
		
		if(pathable) {
			target.parent = current;
			Node n = target;
			System.out.println("Start : "  + starting);
			System.out.println("End : "  + target);
			while(n != null) {	
				path.add(n);
				System.out.println(n.x + " : " + n.y);
				n = n.parent;
			}
		}
		
		
		
	}
}
