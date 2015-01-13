package info.justdaile.tdge.basic;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Bitmap extends BufferedImage{

	protected int[] pixels;
	
	public Bitmap(int width, int height){
		super(width, height, BufferedImage.TYPE_INT_ARGB);
		this.init();
	}
	
	public Bitmap(BufferedImage image){
		super(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		this.init();
		for(int y = 0; y < image.getHeight(); y++){
			for(int x = 0; x < image.getWidth(); x++){
				this.placePixel(x, y, image.getRGB(x, y));
			}
		}
	}
	
	private void init(){
		pixels = ((DataBufferInt) this.getRaster().getDataBuffer()).getData();
	}
	
	public void placePixel(int x, int y, int color){
		int pos = x + (y * this.getWidth());
		if(pos >= 0 && pos < this.pixels.length){
			this.pixels[pos] = color;
		}
	}
	
	public int getPixel(int x, int y){
		return this.pixels[x + (y * this.getWidth())];
	}
	
	public void clear(int color){
		Arrays.fill(pixels, color);
	}
	
	public void draw(Bitmap bitmap, int x, int y){
		for(int yy = y; yy < y + bitmap.getHeight(); yy++){
			for(int xx = x; xx < x + bitmap.getWidth(); xx++){
				if(xx < 0 || yy < 0 || xx >= this.getWidth()){continue;}
				if(yy >= this.getHeight()){break;}
				this.placePixel(xx, yy, bitmap.getPixel(xx - x, yy - y));
			}
		}
	}

}
