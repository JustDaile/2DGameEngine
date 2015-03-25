package info.justdaile.tdge.interative;

import java.util.ArrayList;

import info.justdaile.tdge.gfx.Texture;

public class DynamicObject extends GameObject{
	
	public DynamicObject(int x, int y, Texture texture) {
		super(x, y, texture);
		this.setGravity(true);
	}
		
	@Override
	public void update(ArrayList<GameObject> objects){
		super.update(objects);
		for(int i = 0; i < objects.size(); i++){
			GameObject current = objects.get(i);
			if(this.left.intersects(current.getRight()) || 
					this.right.intersects(current.getLeft()) ||
					this.top.intersects(current.getBottom()) || 
					this.bottom.intersects(current.getTop())){
				this.collision(current);
			}
		}
	}
	
	public void collision(GameObject object){
		// to be implemented by children if required
	}

}
