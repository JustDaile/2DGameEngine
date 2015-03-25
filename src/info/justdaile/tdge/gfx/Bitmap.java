package info.justdaile.tdge.gfx;

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
				this.overwritePixel(x, y, image.getRGB(x, y));
			}
		}
	}
	
	private void init(){
		pixels = ((DataBufferInt) this.getRaster().getDataBuffer()).getData();
	}
	
	public int getPixel(int x, int y){
		return this.getPixel(x + (y * this.getWidth()));
	}
	
	public int getPixel(int n){
		return this.pixels[n];
	}
	
	public void overwritePixel(int x, int y, int color){
		int pos = x + (y * this.getWidth());
		if(pos >= 0 && pos < this.pixels.length){
			this.pixels[pos] = color;
		}
	}
	
	public void fill(int color){
		Arrays.fill(pixels, color);
	}
	
	public void merge(Bitmap bitmap, int x, int y){
		if(bitmap == null){return;}
		for(int yy = y; yy < y + bitmap.getHeight(); yy++){
			for(int xx = x; xx < x + bitmap.getWidth(); xx++){
				if(xx < 0 || yy < 0 || xx >= this.getWidth()){continue;}
				if(yy >= this.getHeight()){return;}
				int pixel = bitmap.getPixel(xx - x, yy - y);
				if(pixel < 0){
					this.overwritePixel(xx, yy, pixel);
				}
			}
		}
	}
	
	public Bitmap getMirroredBitmap(){
		Bitmap map = new Bitmap(this.getWidth(), this.getHeight());
		for(int y = 0; y < map.getHeight(); y++){
			for(int x = 0; x < map.getWidth(); x++){
				map.setRGB(map.getWidth() - 1 - (1 * x), y, this.getRGB(x, y));
			}
		}
		return map;
	}

}
