package coreGame;

import java.awt.Canvas;

public class CoreGame extends Canvas implements Runnable {
	private static final long serialVersionUID = -6764672190690957850L;

	public static void main(String[] args) {
		new Window(800, 600, "Platformer", new CoreGame());
	}
	
	private boolean running = false;
	private Thread thread;
	
	public synchronized void start() {
		if(running) return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {
		
	}
	
	

}
