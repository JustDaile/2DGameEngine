package info.justdaile.tdge.pixel;

import java.awt.Graphics2D;

import info.justdaile.tdge.core.GameApplication;
import info.justdaile.tdge.pixel.gfx.PixelScreen;

public abstract class PixelGameApplication extends GameApplication{
	private static final long serialVersionUID = 1L;
	
	protected PixelScreen screen;
	
	/**
	 * A GameApplication using Pixel based graphics rendering and a build in 
	 * affine transform. 
	 * @param title
	 *             - Application Title, title displayed on the window
	 * @param width
	 *             - Width of the screen
	 * @param height
	 *             - Height of the screen
	 * @param scale
	 *             - Scale factor of the Screen, overall size of window is Width + Height * scale
	 */
	public PixelGameApplication(String title, int width, int height, int scale) {
		super(title, width, height, scale);
		this.screen = new PixelScreen(width, height);
	}

	@Override
	public void draw(Graphics2D g2d) {
		draw(this.screen); // draw graphics
		g2d.drawImage(this.screen, 0, 0, null); // render drawn graphics
	}
	
	public abstract void draw(PixelScreen screen);

}
