package info.justdaile.tdge.tools;

import info.justdaile.tdge.gfx.Bitmap;
import info.justdaile.tdge.gfx.Texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Toolkit {
	
	public static Texture loadTexturesFromTiles(String src, int tilesize, int startTile, int endTile){
		BufferedImage[] images = Toolkit.loadImageAsTiles(src, tilesize);
		Texture texture = new Texture(images[startTile]);
		for(int i = startTile + 1; i < endTile; i++){
			texture.addScenes(new Bitmap(images[i]));
		}
		return texture;
	}
	
	public static Texture[] loadTexturesFromTiles(String src, int tilesize){
		BufferedImage[] images = Toolkit.loadImageAsTiles(src, tilesize);
		Texture[] textures = new Texture[images.length];
		for(int i = 0; i < textures.length; i++){
			textures[i] = new Texture(images[i]);
		}
		return textures;
	}
	
	public static BufferedImage[] loadImageAsTiles(String src, int tilesize){
		BufferedImage image = Toolkit.loadImage(src);
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
	
	public static Texture loadTextureInSlices(String src, int segments, int start, int end){
		BufferedImage[] images = Toolkit.loadImageInSlices(src, segments);
		Texture texture = new Texture(images[start]);
		for(int i = start + 1; i < end; i++){
			texture.addScenes(new Bitmap(images[i]));
		}
		return texture;
	}
	
	public static BufferedImage[] loadImageInSlices(String src, int segments){
		BufferedImage image = Toolkit.loadImage(src);
		BufferedImage[] slices = new BufferedImage[segments];
		for(int i = 0; i < segments; i++){
			slices[i] = image.getSubimage((image.getWidth() / segments) * i, 0, image.getWidth() / segments, image.getHeight());
		}
		return slices;
	}

	public static BufferedImage loadImage(String src) {
		try{
			return ImageIO.read(new File(src));
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
}
