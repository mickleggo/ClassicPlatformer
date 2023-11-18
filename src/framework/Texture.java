package framework;
import java.awt.image.BufferedImage;

import coreGame.BufferedImageLoader;

public class Texture {
	
	SpriteSheet bs, ps;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	
	public BufferedImage[] block = new BufferedImage[2];
	public BufferedImage[] player = new BufferedImage[2];
	
	public Texture() {
		
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			block_sheet = loader.loadImage("/sprites.png");
			player_sheet = loader.loadImage("/player.png");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		
		getTextures();
	}
	
	private void getTextures() {
		block[0] = bs.grabImage(1, 1, 32, 32); //floating block
		block[1] = bs.grabImage(1, 2, 32, 32); //wall block
		
		player[0] = ps.grabImage(1, 1, 32, 64); //moving right
		player[1] = ps.grabImage(2, 1, 32, 64); //moving left
	}

}
