package info.justdaile.tdge.pixelengine;

import info.justdaile.tdge.tools.Texture;

import java.awt.image.BufferedImage;

public class BitmapTexture extends Texture{

	private Bitmap bitmap;
	
	public BitmapTexture(String name, Bitmap bitmap) {
		super(name);
		this.bitmap = bitmap;
	}
	
	public BitmapTexture(String name, BufferedImage image) {
		super(name);
		this.bitmap = new Bitmap(image);
	}
	
	@Override 
	public Object getGraphic(){
		return this.bitmap;
	}

}
