package coreGame;
import java.awt.Graphics;
import java.util.LinkedList;
import framework.GameObject;
import framework.ObjectId;
import objects.Block;


public class Handler {
	
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;

	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.tick(object);
		}	
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void createLevel() {
		for (int xx = 0; xx < CoreGame.WIDTH*2; xx += 32) {
			addObject(new Block(xx, CoreGame.HEIGHT-32, ObjectId.Block));
		}
		
		for (int xPlat = 200; xPlat < 600; xPlat += 32) {
			addObject(new Block(xPlat, 400, ObjectId.Block));
		}
		
		for (int yy = 0; yy < CoreGame.HEIGHT+32; yy += 32) {
			addObject(new Block(0, yy, ObjectId.Block));
		}
		
	}
	
}
