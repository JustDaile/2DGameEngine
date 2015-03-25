package info.justdaile.tdge.gfx;

import java.awt.Color;
import java.util.ArrayList;

import info.justdaile.tdge.core.GameApplication;

public class Background extends Bitmap{

	private int mainColor = 0;
	private ArrayList<BackgroundLayer> layer = new ArrayList<BackgroundLayer>();
	
	public Background(GameApplication game) {
		super(game.getWidth(), game.getHeight());
	}
	
	public void addLayer(BackgroundLayer layer){
		this.layer.add(layer);
	}
	
	public void setMainColor(Color color){
		this.setMainColor(color.getRGB());
	}
	
	public void setMainColor(int color){
		this.mainColor = color;
	}
	
	public void update(){
		this.fill(this.mainColor);
		for(int i = 0; i < this.layer.size(); i++){
			this.layer.get(i).update();
			Bitmap compiled = this.layer.get(i).getCurrentFrame();
			for(int y = 0; y < compiled.getHeight(); y++){
				for(int x = 0; x < compiled.getWidth(); x++){
					int pixel = compiled.getPixel(x, y);
					if(pixel < 0){
						this.pixels[x + (y * this.getWidth())] = pixel;
					}
				}
			}
		}
	}
	
	public void draw(PixelScreen screen){
		screen.draw(this, 0, 0);
	}
	
	
}
