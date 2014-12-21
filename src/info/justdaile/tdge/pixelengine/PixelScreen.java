package info.justdaile.tdge.pixelengine;

import info.justdaile.tdge.core.GameScreen;
import info.justdaile.tdge.tools.Texture;

import java.awt.Color;
import java.util.Arrays;

public class PixelScreen extends Bitmap implements GameScreen{

	public PixelScreen(int width, int height){
		super(width, height);
	}
	
	public void clear(Color color){
		this.clear(color.getRGB());
	}
	
	public void clear(int color){
		Arrays.fill(pixels, color);
	}
	
	public void draw(Texture graphic, int x, int y){
		Bitmap bitmap = (Bitmap) graphic.getGraphic();
		for(int yy = y; yy < y + bitmap.getHeight(); yy++){
			if(yy < 0){continue;}
			if(yy >= this.getHeight()){break;}
			for(int xx = x; xx < x + bitmap.getWidth(); xx++){
				if(xx < 0){xx = 0;}
				if(xx >= this.getWidth()){break;}
				this.placePixel(xx, yy, bitmap.getRGB(xx - x, yy - y));
			}
		}
	}
	
}
