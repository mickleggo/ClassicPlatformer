package objects;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import coreGame.CoreGame;
import framework.*;


public class Block extends GameObject {
	
	Texture tex = CoreGame.getInstance();
	private int type = 0;

	public Block(float x, float y, int type, ObjectId id) {
		super(x, y, id);
		this.type = type;
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		if(type == 0) { //wall
			g.drawImage(tex.block[0], (int) x, (int) y, null);
		}
		if(type == 1) { //floor
			g.drawImage(tex.block[1], (int) x, (int) y, null);
		}
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
