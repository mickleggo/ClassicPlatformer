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
		for (int xx = 0; xx < CoreGame.WIDTH+32; xx += 32) {
			addObject(new Block(xx, CoreGame.HEIGHT-32, ObjectId.Block));
		}
		
		for (int xPlat = 128; xPlat < CoreGame.WIDTH-128; xPlat += 32) {
			addObject(new Block(xPlat, CoreGame.HEIGHT-192, ObjectId.Block));
		}
		
		for (int yyL = 0; yyL < CoreGame.HEIGHT+32; yyL += 32) {
			addObject(new Block(0, yyL, ObjectId.Block));
		}
		
		for (int yyR = 0; yyR < CoreGame.HEIGHT+32; yyR += 32) {
			addObject(new Block(CoreGame.WIDTH-32, yyR, ObjectId.Block));
		}
	}
	
}
