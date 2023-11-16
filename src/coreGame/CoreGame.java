package coreGame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import framework.ObjectId;
import objects.Test;

public class CoreGame extends Canvas implements Runnable {
	private static final long serialVersionUID = -6764672190690957850L;
	private boolean running = false;
	private Thread thread;
	
	Handler handler;

	public static void main(String[] args) {
		new Window(800, 600, "Platformer", new CoreGame());
	}
	
	private void init() {
		handler = new Handler();
		handler.addObject(new Test(100, 100, ObjectId.Test));
	}
	
	public synchronized void start() {
		if(running) return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
//				fps = frames;
//				ticks = updates;
				System.out.println("FPS: " + frames + "  | TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick() {
		handler.tick();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
	//***************************************************************************************//	
		//Draw game here
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		handler.render(g);
		
	//***************************************************************************************//	
		g.dispose();
		bs.show();
	}
	
}
