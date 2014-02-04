package pathfinding;

import java.util.ArrayList;

public class PathNavigator {
	
	private ArrayList<Node> path;
	
	public PathNavigator(ArrayList<Node> p) {
		path = p;
	}
	
	public Node current() {
		if(path == null) {
			return null;
		}
		
		if(path.size() > 0) {
			return path.get(path.size() - 1);
		}
		
		return null;
	}
	
	public Node next() {
		path.remove(path.size() - 1);
		return current();
	}
	
	public boolean atEnd() {
		if(path == null) {
			return true;
		}
		
		return path.isEmpty();
	}
	
	public void addPath(ArrayList<Node> p) {
		path = p;
	}
}
