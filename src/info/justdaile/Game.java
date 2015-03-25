package info.justdaile;

import info.justdaile.tdge.core.BasicGameContainer;
import info.justdaile.tdge.gfx.PixelGameApplication;
import info.justdaile.tdge.gfx.PixelScreen;
import info.justdaile.tdge.interative.GameWorld2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Game extends PixelGameApplication implements MouseMotionListener{
	private static final long serialVersionUID = 1L;
	
	private static BasicGameContainer bgc;
	private static final int WIDTH = 16 * 8;
	private static final int HEIGHT = 16 * 6;
	private static final int SCALE = 3;
	
	public static void main(String args[]){
		bgc = new BasicGameContainer(new Game("Testing Screen", Game.WIDTH, Game.HEIGHT, Game.SCALE), 60);
		bgc.display();
		bgc.start();
	}
	
	private int mx, my;
	private GameWorld2D world;
	
	public Game(String title, int width, int height, int scale) {
		super(title, width, height, scale);
		this.mx = this.my = 0;
		this.addMouseMotionListener(this);
	}
	
	@Override
	public void update() {
		world.update();
	}

	@Override
	public void draw(PixelScreen screen) {
		screen.clear(0);
		world.draw(screen);
	}
	
	@Override 
	public void mouseDragged(MouseEvent e){
		this.mx = e.getX() / Game.SCALE;
		this.my = e.getY() / Game.SCALE;
	}
	
	@Override
	public void mouseMoved(MouseEvent e){
		this.mx = e.getX() / Game.SCALE;
		this.my = e.getY() / Game.SCALE;
	}

	public int getMouseX(){
		return this.mx;
	}
	
	public int getMouseY(){
		return this.my;
	}
	
}
