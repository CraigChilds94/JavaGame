package gamestate;

import managers.GameStateManager;
import managers.LevelManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import pathfinding.Node;
import pathfinding.Pathfinder;
import core.GameState;

public class LevelSelectState extends State {

	public Pathfinder p;
	public int width, height;
	
	
	public LevelSelectState() {
		p = new Pathfinder(4);
        p.createAndLinkNodes();
        p.setWalkable(1, 1, false);
        p.findPath(0, 1, 2, 2);
	}
	
	@Override
	public void update(GameContainer c, float delta) throws SlickException {
		Input i = c.getInput();
		if(i.isKeyPressed(i.KEY_SPACE)) {
			LevelManager.current = 1;
			GameStateManager.state = GameState.PLAYING;
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawString("Level Select", 100, 100);
		g.drawString("Press space to play", 100, 170);
		
		showPathfindingExample(g);
	}
	
	public void showPathfindingExample(Graphics g) {
		
		for(int y = 0; y < 4; y++) {
			for(int x = 0; x < 4; x++) {
				
				Node n = p.nodes[x][y];
				g.setColor(Color.pink);
				
				if(!n.walkable) {
					g.setColor(Color.yellow);
				}
				
				if(p.path.contains(n)) {
					g.setColor(Color.orange);
					
					if(p.path.indexOf(n) == 0) {
						g.setColor(Color.red);
					}
					
					if(p.path.indexOf(n) == p.path.size() - 1) {
						g.setColor(Color.blue);
					}
				}
				
				g.fillRect(n.x * 32, n.y * 32, 32, 32);
				
			}
		}
		
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				g.setColor(Color.white);
				g.drawRect((j * 32), (i * 32), 32, 32);
			}
		}
	}

}
