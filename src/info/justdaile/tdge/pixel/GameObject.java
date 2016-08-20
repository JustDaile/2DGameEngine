package info.justdaile.tdge.pixel;

import java.awt.Graphics2D;

import info.justdaile.tdge.pixel.gfx.Bitmap;
import info.justdaile.tdge.pixel.gfx.PixelScreen;
import info.justdaile.tdge.pixel.gfx.Bitmap.AntiAliasing;

public class GameObject extends Sprite{
	private static final long serialVersionUID = 1L;
	
	public GameObject(Bitmap map, int x, int y) {
		super(map, x, y);
	}
	
	public void update(){

	}
	
	private float angle = 0;
	
	public void draw(PixelScreen screen, double tx, double ty){
		this.draw(screen, tx, ty, 0, 0, angle, AntiAliasing.NEAREST_NEIGHBOUR);
	}
	
	public void draw(Graphics2D g2d, double tx, double ty){
		this.draw(g2d, tx, ty, 0, 0, angle);
	}

}
