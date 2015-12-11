package info.justdaile.tdge.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import info.justdaile.tdge.pixel.gfx.Bitmap;

public class GameToolkit {
	
	public static BufferedImage[] loadImagesFromSheet(String src, int tilesize){
		BufferedImage image = GameToolkit.loadImage(src);
		int rows = image.getHeight() / tilesize;
		int cols = image.getWidth() / tilesize;
		BufferedImage[] tiles = new BufferedImage[cols * rows];
		
		int i = 0;
		for(int y = 0; y < rows; y++){
			for(int x = 0; x < cols; x++){
				tiles[i++] = image.getSubimage(tilesize * x, tilesize * y, tilesize, tilesize);
			}
		}
		return tiles;
	}

	public static BufferedImage loadImage(String src) {
		try{
			return ImageIO.read(new File(src));
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}

	public static Bitmap loadBitmap(String src) {
		return new Bitmap(GameToolkit.loadImage(src));
	}
	
}
