package info.justdaile;

import java.util.ArrayList;
import info.justdaile.tdge.core.FullscreenGameContainer;
import info.justdaile.tdge.pixelengine.BitmapTexture;
import info.justdaile.tdge.pixelengine.PixelGameApplication;
import info.justdaile.tdge.pixelengine.PixelScreen;
import info.justdaile.tdge.tools.Texture;
import info.justdaile.tdge.tools.TextureGrid;
import info.justdaile.tdge.tools.TextureLibrary;
import info.justdaile.tdge.tools.Toolkit;

public class Game extends PixelGameApplication {
	private static final long serialVersionUID = 1L;

	private static FullscreenGameContainer container;
	private static final int WIDTH = 16 * 10;
	private static final int HEIGHT = 16 * 8;
	private static final int SCALE = 5;

	public static void main(String[] args) {
		container = new FullscreenGameContainer(new Game("Test Application", WIDTH, HEIGHT, SCALE));
		container.onlyRenderAfterUpdates(true);
		container.disableResting(true);
		container.display();
		container.start();
	}

	private TextureGrid grid;
	
	public Game(String title, int width, int height, int scale) {
		super(title, width, height, scale);
		this.showRuntimeInfo = true;
		
		new TextureLibrary() {
			@Override
			public void init(ArrayList<Texture> library) {
				library.add(new BitmapTexture("dirt", Toolkit.loader.loadImage("res/dirt.png")));
				library.add(new BitmapTexture("grass", Toolkit.loader.loadImage("res/grass.png")));
			}
		};
		
		grid = new TextureGrid(width, height, 10, 8);
		grid.fill("dirt");
		for(int y = 1; y < 7; y++){
			for(int x = 1; x < 9; x++){
				grid.setTile(x, y, "grass");
			}
		}
	}

	@Override
	public void draw(PixelScreen screen) {
		grid.draw(screen, 0, 0);
	}

	@Override
	public void update() {

	}

}
