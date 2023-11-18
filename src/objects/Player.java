package objects;
import java.awt.*;
import java.util.LinkedList;
import coreGame.CoreGame;
import coreGame.Handler;
import framework.*;

public class Player extends GameObject {
	
	private Handler handler; 
	
	private float width = 32, height = 64;
	private float gravity = 0.4f;
	
	private final float MAX_SPEED = 10;
	
	Texture tex = CoreGame.getInstance();

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
				if(getBounds().intersects(tempObject.getBounds()) ) {
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}
				else {
					falling = true;
				}
				
				if (tempObject.getId() == ObjectId.Block) {
					if(getBoundsLeft().intersects(tempObject.getBounds())) {
						x = tempObject.getX() + 35;
					}
				}
				
				if (tempObject.getId() == ObjectId.Block) {
					if(getBoundsRight().intersects(tempObject.getBounds())) {
						x = tempObject.getX() - (width+3);
					}
				}
				
				if (tempObject.getId() == ObjectId.Block) {
					if(getBoundsTop().intersects(tempObject.getBounds())) {
						y = tempObject.getY() + (height /2);
						velY *= -1;
					}
				}
			}
			
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		if(velX < 0) {
			g.drawImage(tex.player[1], (int) x, (int) y, null);
		}
		else {
			g.drawImage(tex.player[0], (int) x, (int) y, null);
		}
		
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.MAGENTA);
//		g2d.draw(getBounds());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsRight());
//		g2d.draw(getBoundsTop());
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x+4, (int) y+10, (int) width-9, (int) height-10);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x-2, (int) y+6, (int) 6, (int) height-12);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) x+ (int)width -5, (int) y+6, (int) 6, (int) height-12);
	}
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x+2, (int) y, (int) width-5, (int) 6);
	}

}
