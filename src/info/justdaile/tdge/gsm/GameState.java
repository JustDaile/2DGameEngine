package info.justdaile.tdge.gsm;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info.justdaile.tdge.core.GameApplication;
import info.justdaile.tdge.pixel.gfx.PixelScreen;

public abstract class GameState{
		
		private String id;
		private GameApplication game;
		
		public GameState(String id, GameApplication game){
			this.id = id;
			this.game = game;
		}
		
		public GameApplication getGame(){
			return this.game;
		}
		
		public String getId(){
			return this.id;
		}
		
		public abstract void update();
		public void draw(PixelScreen screen){}
		public void draw(Graphics2D g2d){}
		
		public void stop(){} // called when state is being changed
		public void restart(){}
		public void keyTyped(KeyEvent e, char keyChar) {}
		public void keyPressed(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
		public void mouseMoved(MouseEvent e, float x, float y){}
		public void mouseDragged(MouseEvent e, float x, float y){}
		public void mouseClicked(MouseEvent e, float x, float y){}
		public void mouseEntered(MouseEvent e, float x, float y){}
		public void mouseExited(MouseEvent e, float x, float y){}
		public void mousePressed(MouseEvent e, float x, float y){}
		public void mouseReleased(MouseEvent e, float x, float y){}

}