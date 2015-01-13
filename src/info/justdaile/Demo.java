package info.justdaile;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import info.justdaile.tdge.basic.Bitmap;
import info.justdaile.tdge.basic.PixelGameApplication;
import info.justdaile.tdge.core.BasicGameContainer;
import info.justdaile.tdge.tools.AnimatedTexture;
import info.justdaile.tdge.tools.Texture;
import info.justdaile.tdge.tools.TextureLibary;
import info.justdaile.tdge.tools.Tilesheet;
import info.justdaile.tdge.tools.Toolkit;

public class Demo extends PixelGameApplication implements MouseMotionListener{
	private static final long serialVersionUID = 1L;
	
	private static BasicGameContainer bgc;
	private static final int WIDTH = 16 * 2;
	private static final int HEIGHT = 16 * 2;
	private static final int SCALE = 10;
	
	public static void main(String args[]){
		bgc = new BasicGameContainer(new Demo("DEMO 1 by Daile Alimo", Demo.WIDTH, Demo.HEIGHT, Demo.SCALE), 60);
		bgc.display();
		bgc.start();
	}
		
	private int mx, my;
	
	public Demo(String title, int width, int height, int scale) {
		super(title, width, height, scale);
		this.mx = this.my = 0;
		this.addMouseMotionListener(this);
		
		new TextureLibary() {
			@Override
			public void initTextures(ArrayList<Texture> libary) {
				Tilesheet spriteSheet = new Tilesheet(16, Toolkit.loadImage("res/alien.png"));
				libary.add(new Texture("player_idle", spriteSheet.getTiles()[0]));
				libary.add(new AnimatedTexture(800, "player_moving", spriteSheet.getTiles()[1], spriteSheet.getTiles()[2], spriteSheet.getTiles()[3], spriteSheet.getTiles()[4]));
				
			}
		};
		
	}
	
	@Override
	public void update() {
		((AnimatedTexture)TextureLibary.getTexture("player_moving")).update();
	}

	@Override
	public void draw(Bitmap screen) {
		screen.clear(0);
		screen.draw(TextureLibary.getTexture("player_moving"), mx - 8, my - 16);
	}
	
	@Override
	public void mouseDragged(MouseEvent e){
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e){
		this.mx = e.getX() / Demo.SCALE;
		this.my = e.getY() / Demo.SCALE;
	}

}
