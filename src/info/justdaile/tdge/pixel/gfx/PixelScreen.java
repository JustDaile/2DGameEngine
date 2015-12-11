package info.justdaile.tdge.pixel.gfx;

import java.awt.Color;

public class PixelScreen extends Bitmap{
	
	public PixelScreen(int width, int height) {
		super(width, height);
	}
	
	public void clear(Color color){
		super.fill(color.getRGB());
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
	
	public void setPixel(int n, Color color){
		this.setPixel(n, color.getRGB());
	}
	
	public void setPixel(int n, int color){
		super.overwritePixel(n, color);
	}
	
	public void draw(Bitmap bitmap){
		super.write(bitmap);	
	}

	public void draw(Bitmap bitmap, AntiAliasing aa){
		super.write(bitmap, aa);
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
	public void write(Bitmap bitmap, int x, int y){
		super.write(bitmap);
	}

}
