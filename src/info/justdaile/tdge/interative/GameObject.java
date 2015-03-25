package info.justdaile.tdge.interative;

import info.justdaile.tdge.gfx.PixelScreen;
import info.justdaile.tdge.gfx.Texture;

import java.awt.Rectangle;
import java.util.ArrayList;

public class GameObject {
	
	protected Texture texture;
	protected int x, y, width, height;
	protected boolean gravityEffected;
	protected float currentSpeed;
	protected Rectangle top, bottom, left, right;
	protected GameObject floor;

	private int collisionPadding;
	private float friction = GameWorld2D.friction;
	
	public GameObject(int x, int y, Texture texture){
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = this.texture.getWidth();
		this.height = this.texture.getHeight();
		
		this.collisionPadding = (int) (this.width * 0.3);
		top = new Rectangle(this.width, this.height / 2);
		bottom = new Rectangle(this.width, this.height / 2);
		left = new Rectangle(this.width / 2, this.height - (this.collisionPadding * 2));
		right = new Rectangle(this.width / 2, this.height - (this.collisionPadding * 2));
		
		alignCollision();
	}
	
	public void setGravity(boolean gravity){
		this.gravityEffected = gravity;
	}
	
	public void setFriction(float friction){
		this.friction = friction;
	}
	
	public float getFriction(){
		return this.friction;
	}
	
	public void disableGravity(){
		this.gravityEffected = false;
	}
	
	public boolean isGravityEffected(){
		return this.gravityEffected;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void offset(int x, int y){
		this.offsetX(x);
		this.offsetY(y);
	}
	
	public void offsetX(int x){
		this.x += x;
	}
	
	public void offsetY(int y){
		this.y += y;
	}

	public boolean hasFloor(){
		return this.floor != null;
	}
	
	public Rectangle getLeft(){
		return this.left;
	}
	
	public Rectangle getRight(){
		return this.right;
	}
	
	public Rectangle getTop(){
		return this.top;
	}
	
	public Rectangle getBottom(){
		return this.bottom;
	}
	
	public GameObject getFloor(){
		return this.floor;
	}
	
	private void alignCollision(){
		top.setLocation(this.x, this.y);
		bottom.setLocation(this.x, this.y + this.height - (this.height / 2));
		left.setLocation(this.x, this.y + this.collisionPadding);
		right.setLocation(this.x + this.width - (this.width / 2), this.y + this.collisionPadding);
	}
	
	public void update(GameObject...objects){
		ArrayList<GameObject> obj = new ArrayList<GameObject>();
		for(int i = 0; i < objects.length; i++){
			obj.add(objects[i]);
		}
		this.update(obj);
	}
			
	public void update(ArrayList<GameObject> objects){
		this.texture.update();
		if(this.isGravityEffected()){
			if(currentSpeed < GameWorld2D.maxFallSpeed){
				currentSpeed += GameWorld2D.fallAcceleration;
			}
		}
		this.offsetY((int) currentSpeed); 
		this.floor = null;
		for(int i = 0; i < objects.size(); i++){
			GameObject current = objects.get(i);
			if(current != this){
				if(this.bottom.intersects(current.top)){
					this.floor = current;
					this.currentSpeed = 0;
					this.y = current.y - this.height + 1;
				}
				
				if(this.top.intersects(current.bottom)){
					this.y = current.y + current.height;
				}
				
				if(this.left.intersects(current.right)){
					this.x = current.x + current.width;
				}
				
				if(this.right.intersects(current.left)){
					this.x = current.x - this.width;
				}
			}
		}
		alignCollision();
	}
	
	public void draw(PixelScreen screen){
		screen.draw(this.texture, this.getX(), this.getY());
	}
	
}
