package info.justdaile.tdge.pixelengine;

import java.awt.Graphics;

import info.justdaile.tdge.core.GameApplication;
import info.justdaile.tdge.core.RenderListener;
import info.justdaile.tdge.tools.Toolkit;

public abstract class PixelGameApplication extends GameApplication implements RenderListener{
	private static final long serialVersionUID = 1L;

	private PixelScreen pixelscreen;
	
	public PixelGameApplication(String title, int width, int height, int scale) {
		super(title, width, height, scale);
		pixelscreen = new PixelScreen(width, height);
		addRenderListener(this);
		new Toolkit(); // init toolkit
	}
	
	public abstract void draw(PixelScreen screen);

	@Override
	public void doRender(Graphics g) {
		this.draw(this.pixelscreen);
		g.drawImage(this.pixelscreen, 0, 0, null);
	}

}
