package info.justdaile.tdge.gfx;

import java.awt.Graphics;

import info.justdaile.tdge.core.GameApplication;

public abstract class PixelGameApplication extends GameApplication{
	private static final long serialVersionUID = 1L;
	
	private PixelScreen screen;
	
	public PixelGameApplication(String title, int width, int height, int scale) {
		super(title, width, height, scale);
		screen = new PixelScreen(width, height);
	}

	@Override
	public void draw(Graphics g) {
		draw(screen);
		g.drawImage(screen, 0, 0, null);
	}
	
	public abstract void draw(PixelScreen screen);

}
