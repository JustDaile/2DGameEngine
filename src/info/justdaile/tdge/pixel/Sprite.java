package info.justdaile.tdge.pixel;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import info.justdaile.tdge.pixel.gfx.Bitmap;
import info.justdaile.tdge.pixel.gfx.Bitmap.AntiAliasing;
import info.justdaile.tdge.pixel.gfx.PixelScreen;

public class Sprite extends Rectangle{
	private static final long serialVersionUID = 1L;
	
	private Bitmap map;
	
	public Sprite(Bitmap map, int x, int y){
		this.map = map;
		this.x = x;
		this.y = y;
		this.width = this.map.getWidth();
		this.height = this.map.getHeight();
	}
	
	public Bitmap getBitmap(){
		return this.map;
	}
	
	public void moveX(double ax){
		this.move(ax, 0);
	}
	
	public void moveY(double ay){
		this.move(0, ay);
	}
	
	public void move(double ax, double ay){
		this.x += ax;
		this.y += ay;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void draw(PixelScreen screen, double tx, double ty, double shx, double shy, double angle, AntiAliasing aa){
		AffineTransform saveXform = screen.getTransform();
		AffineTransform transformed = new AffineTransform();
		
		transformed.translate(this.x, this.y);
	    transformed.shear(Math.toRadians(shx), Math.toRadians(shy));
		transformed.rotate(Math.toRadians(angle));
		transformed.translate(-tx, -ty);

		screen.setTransform(transformed);
		screen.draw(this.map, aa);
		screen.setTransform(saveXform); // reset transform as draw(bitmap) method doesn't do it for us
	}
	
	public void draw(Graphics2D g2d, double tx, double ty, double shx, double shy, double angle){
		AffineTransform saveXform = g2d.getTransform();
		AffineTransform transformed = new AffineTransform();
		
		transformed.translate(this.x, this.y);
	    transformed.shear(Math.toRadians(shx), Math.toRadians(shy));
		transformed.rotate(Math.toRadians(angle));
		transformed.translate(-tx, -ty);
		
		g2d.drawImage(this.map, transformed, null);

		g2d.setTransform(saveXform); // reset transform as draw(bitmap) method doesn't do it for us
	}
	
	public void draw(PixelScreen screen, double tx, double ty, double shx, double shy, double angle){
		this.draw(screen, tx, ty, shx, shy, angle, Bitmap.AntiAliasing.NONE);
	}

}
