package info.justdaile.tdge.pixelengine;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Bitmap extends BufferedImage{

	protected int[] pixels;
	
	public Bitmap(int width, int height){
		super(width, height, BufferedImage.TYPE_INT_ARGB);
		init();
	}
	
	public Bitmap(BufferedImage image){
		super(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		init();
		for(int y = 0; y < this.getHeight(); y++){
			for(int x = 0; x < this.getWidth(); x++){
				this.placePixel(x, y, image.getRGB(x, y));
			}
		}
	}
	
	private final void init(){
		pixels = ((DataBufferInt) this.getRaster().getDataBuffer()).getData();
	}
	
	public void placePixel(int x, int y, Color color){
		this.placePixel(x, y, color.getRGB());
	}
	
	public void placePixel(int x, int y, int color){
		if(x >= 0 && y >= 0 && (x + (y * this.getWidth())) < this.pixels.length){
			pixels[x + (y * this.getWidth())] = color;
		}
	}
	
}
