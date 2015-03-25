package info.justdaile.tdge.gfx;

import java.awt.Color;

public class PixelScreen extends Bitmap{

	public PixelScreen(int width, int height) {
		super(width, height);
	}
	
	public void clear(int color){
		super.fill(color);
	}
	
	public void setPixel(int x, int y, Color color){
		this.setPixel(x, y, color.getRGB());
	}
	
	public void setPixel(int x, int y, int color){
		super.overwritePixel(x, y, color);
	}
	
	public void draw(Bitmap bitmap, int x, int y){
		super.merge(bitmap, x, y);
	}
	
	@Deprecated
	public void fill(int color){
		super.fill(color);
	}
	
	@Deprecated
	public void overwritePixel(int x, int y, int color){
		super.overwritePixel(x, y, color);
	}
	
	@Deprecated
	public void merge(Bitmap bitmap, int x, int y){
		super.merge(bitmap, x, y);
	}

}
