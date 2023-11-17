package objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import framework.GameObject;
import framework.ObjectId;


public class Block extends GameObject {

	public Block(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int) x, (int) y, 32, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
