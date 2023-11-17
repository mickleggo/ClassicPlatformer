package objects;
import java.awt.*;
import java.util.LinkedList;
import coreGame.Handler;
import framework.GameObject;
import framework.ObjectId;

public class Player extends GameObject {
	
	private Handler handler; 
	
	private boolean jumping = false;
	private boolean falling = true;
	
	private float width = 32, height = 64;
	private float gravity = 0.09f;
	
	private final float MAX_SPEED = 10;

	public Player(float x, float y, ObjectId id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(jumping || falling) {
			velY += gravity;
			if(velY > MAX_SPEED) velY = MAX_SPEED;
		}
		
		Collision(object);
	}
	
	private void Collision(LinkedList<GameObject> object) {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ObjectId.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}
			}
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
