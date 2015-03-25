package info.justdaile.tdge.interative;

import info.justdaile.tdge.gfx.Texture;

public class StaticObject extends GameObject{

	public StaticObject(int x, int y, Texture texture) {
		super(x, y, texture);
	}
	
	@Deprecated
	public void setGravity(float maxFallSpeed, float fallAcceleration){}

}
