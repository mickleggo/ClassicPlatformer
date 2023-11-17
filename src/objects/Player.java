package objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import framework.GameObject;
import framework.ObjectId;

public class Player extends GameObject {
	
	private boolean jumping = false;
	private boolean falling = true;
	
	private float width = 32, height = 64;
	private float gravity = 0.09f;
	
	private final float MAX_SPEED = 10;

	public Player(float x, float y, ObjectId id) {
		super(x, y, id);
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(jumping || falling) {
			velY += gravity;
			if(velY > MAX_SPEED) velY = MAX_SPEED;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

}
