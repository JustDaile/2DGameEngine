package info.justdaile.tdge.basic;

import java.awt.Graphics;

import info.justdaile.tdge.core.GameApplication;

public abstract class PixelGameApplication extends GameApplication{
	private static final long serialVersionUID = 1L;
	
	private Bitmap bs;
	
	public PixelGameApplication(String title, int width, int height, int scale) {
		super(title, width, height, scale);
		bs = new Bitmap(width, height);
	}

	@Override
	public void draw(Graphics g) {
		draw(bs);
		g.drawImage(bs, 0, 0, null);
	}
	
	public abstract void draw(Bitmap screen);

}
