package objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
		//y += velY;
		
		if(jumping || falling) {
			velY += gravity;
			if(velY > MAX_SPEED) velY = MAX_SPEED;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
		
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.MAGENTA);
		g2d.draw(getBounds());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsTop());
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x+2, (int) y+4, (int) width-5, (int) height-4);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x-1, (int) y+4, (int) 4, (int) height-8);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) x+ (int)width -4, (int) y+4, (int) 4, (int) height-8);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x, (int) y-1, (int) width-1, (int) 4);
	}

}
