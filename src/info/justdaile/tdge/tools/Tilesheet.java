package info.justdaile.tdge.tools;

import java.awt.image.BufferedImage;

public class Tilesheet {

	private BufferedImage[] tiles;
	
	public Tilesheet(int tilesize, BufferedImage image){
		int rows = image.getHeight() / tilesize;
		int cols = image.getWidth() / tilesize;
		this.tiles = new BufferedImage[cols * rows];
		int i = 0;
		for(int y = 0; y < rows; y++){
			for(int x = 0; x < cols; x++){
				this.tiles[i++] = image.getSubimage(tilesize * x, tilesize * y, tilesize, tilesize);
			}
		}
	}
	
	public BufferedImage[] getTiles(){
		return this.tiles;
	}
	
}
