package info.justdaile.tdge.interative;

import java.util.ArrayList;

import info.justdaile.tdge.gfx.PixelScreen;
import info.justdaile.tdge.gfx.Texture;

public class InterchangibleObject extends GameObject{

	private ArrayList<Texture> states = new ArrayList<Texture>();
	private int currentState = 0;
	
	public InterchangibleObject(int x, int y, Texture baseTexture) {
		super(x, y, baseTexture);
		this.states.add(texture);
	}
	
	public void addState(Texture texture){
		this.states.add(texture);
	}
	
	public void setState(int index){
		this.currentState = index;
	}
	
	@Override
	public void update(ArrayList<GameObject> object){
		states.get(this.currentState).update();
		super.update(object);
	}
	
	@Override
	public void draw(PixelScreen screen){
		screen.draw(this.states.get(currentState), this.getX(), this.getY());
	}

}
