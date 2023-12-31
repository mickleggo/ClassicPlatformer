package coreGame;
import java.awt.*;
import java.awt.image.*;
import framework.*;
import objects.Block;
import objects.Player;


public class CoreGame extends Canvas implements Runnable {
	private static final long serialVersionUID = -6764672190690957850L;	
	private boolean running = false;
	private Thread thread;
	private BufferedImage level = null;
	
	public static int WIDTH, HEIGHT;
	
	Handler handler;
	Camera cam;
	static Texture tex;

	public static void main(String[] args) {
		new Window(800, 600, "Platformer", new CoreGame());
	}
	
	private void init() {
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level.png");
		
		cam = new Camera( 0, 0);
		handler = new Handler();
		//handler.createLevel();
		
		LoadImageLevel(level);
		
		this.addKeyListener(new KeyInput(handler));
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
				System.out.println("FPS: " + frames + "  | TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick() {
		handler.tick();
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ObjectId.Player ) {
				cam.tick(handler.object.get(i));
			}
		}
		
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		
	//***************************************************************************************//	
		//Draw game here
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.translate(cam.getX(), cam.getY());
		handler.render(g);
		g2d.translate(-cam.getX(), -cam.getY());
		
	//***************************************************************************************//	
		g.dispose();
		bs.show();
	}
	
	public static Texture getInstance() {
		return tex;
	}
	
	private void LoadImageLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0; xx < w; xx++) {
			for(int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 0 && green == 0 && blue == 0) handler.addObject(new Block(xx*32, yy*32, 0, ObjectId.Block));
				if(red == 127 && green == 127 && blue == 127) handler.addObject(new Block(xx*32, yy*32, 1, ObjectId.Block));
				if(red == 0 && green == 0 && blue == 255) handler.addObject(new Player(xx*32, yy*32, ObjectId.Player, handler));
			}
		}
	}
	
}
