package info.justdaile.tdge.gsm;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import info.justdaile.tdge.pixel.gfx.PixelScreen;

public abstract class GameStateManager {

	private static ArrayList<GameState> states;
	private static int activeState = -1;
	
	public GameStateManager(){
		GameStateManager.states = new ArrayList<GameState>();
		this.initGameStates(states);
	}
	
	public static boolean hasActiveState(){
		return GameStateManager.activeState > -1 && GameStateManager.activeState < GameStateManager.states.size();
	}
	
	public abstract void initGameStates(ArrayList<GameState> states);
	
	public static void setState(int index){
		if(index >= 0 && index < GameStateManager.states.size()){
			if(GameStateManager.activeState != -1){
				GameStateManager.states.get(GameStateManager.activeState).stop();
			}
			GameStateManager.activeState = index;
			GameStateManager.states.get(GameStateManager.activeState).restart();
		}
	}
	
	public static void setState(String id){
		for(int i = 0; i < GameStateManager.states.size(); i++){
			if(GameStateManager.states.get(i).getId().contentEquals(id)){
				GameStateManager.setState(i);
			}
		}
	}
	
	public static void update(){
		if(GameStateManager.hasActiveState()){
			GameStateManager.states.get(GameStateManager.activeState).update();
		}
	}
	
	public static void draw(PixelScreen screen) {
		if(GameStateManager.hasActiveState()){
			GameStateManager.states.get(GameStateManager.activeState).draw(screen);
		}
	}
	
	public static void draw(Graphics2D g2d) {
		if(GameStateManager.hasActiveState()){
			GameStateManager.states.get(GameStateManager.activeState).draw(g2d);
		}
	}

	public static void mouseMoved(MouseEvent e, float x, float y) {
		if(GameStateManager.hasActiveState()){
			GameStateManager.states.get(GameStateManager.activeState).mouseMoved(e, x, y);
		}
	}
	
	public static void mouseDragged(MouseEvent e, float x, float y) {
		if(GameStateManager.hasActiveState()){
			GameStateManager.states.get(GameStateManager.activeState).mouseDragged(e, x, y);
		}
	}
	
	public static void mouseClicked(MouseEvent e, float x, float y) {
		if(GameStateManager.hasActiveState()){
			GameStateManager.states.get(GameStateManager.activeState).mouseClicked(e, x, y);
		}
	}

	public static void mouseEntered(MouseEvent e, float x, float y) {
		if(GameStateManager.hasActiveState()){
			GameStateManager.states.get(GameStateManager.activeState).mouseEntered(e, x, y);
		}	
	}

	public static void mouseExited(MouseEvent e, float x, float y) {
		if(GameStateManager.hasActiveState()){
			GameStateManager.states.get(GameStateManager.activeState).mouseExited(e, x, y);
		}		
	}

	public static void mousePressed(MouseEvent e, float x, float y) {
		if(GameStateManager.hasActiveState()){
			GameStateManager.states.get(GameStateManager.activeState).mousePressed(e, x, y);
		}		
	}

	public static void mouseReleased(MouseEvent e, float x, float y) {
		if(GameStateManager.hasActiveState()){
			GameStateManager.states.get(GameStateManager.activeState).mouseReleased(e, x, y);
		}		
	}

	public static void keyTyped(KeyEvent e, char keyChar) {
		if(GameStateManager.hasActiveState()){
			GameStateManager.states.get(GameStateManager.activeState).keyTyped(e, keyChar);
		}				
	}

	public static void keyPressed(KeyEvent e) {
		if(GameStateManager.hasActiveState()){
			GameStateManager.states.get(GameStateManager.activeState).keyPressed(e);
		}			
	}

	public static void keyReleased(KeyEvent e) {
		if(GameStateManager.hasActiveState()){
			GameStateManager.states.get(GameStateManager.activeState).keyReleased(e);
		}			
	}
	
}
