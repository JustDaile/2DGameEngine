package info.justdaile.tdge.interative;

import java.util.ArrayList;

import info.justdaile.tdge.gfx.Texture;

public class BasicPlayer extends InterchangibleObject{
	
	public static final int LEFT = -1, RIGHT = 1;
	
	private float moveDirection = 0, moveSpeed = 0, MaxMoveSpeed = 1f, acceleration = 0.4f, jumpSpeed = 0;
	private int jumpHeight = 0;
	private boolean jump, jumping;
	private int leftBound = -1, rightBound = -1;
	private boolean effectScreen;
	
	public BasicPlayer(int sx, int sy, Texture idle) {
		super(sx, sy, idle);
		this.setGravity(true);
	}
	
	public void effectScreen(boolean effect){
		this.effectScreen = effect;
	}
	
	public void setHorizontalBoundings(int left, int right){
		this.leftBound = left;
		this.rightBound = right;
	}
	
	public void setMoveDirection(int dir){
		this.moveDirection = dir;
	}
	
	public void jump(){
		this.jump = true;
	}
	
	@Override
	public void update(ArrayList<GameObject> objects){
		super.update(objects);
		
		// basic movement code
		if(moveDirection > 0){
			if(moveSpeed < MaxMoveSpeed){
				moveSpeed += acceleration;
			}
		}else if(moveDirection < 0){
			if(moveSpeed > -MaxMoveSpeed){
				moveSpeed -= acceleration;
			}
		}else{
			// basic deceleration code
			float dec = GameWorld2D.friction; // default friction
			
			if(this.hasFloor()){
				dec = this.getFloor().getFriction();
			}
			if(moveSpeed > 0){				
				if(moveSpeed - dec >= 0){
					moveSpeed -= dec;
				}else{
					moveSpeed = 0;
				}
			}else if(moveSpeed < 0){
				if(moveSpeed + dec <= 0){
					moveSpeed += dec;
				}else{
					moveSpeed = 0;
				}
			}
		}
		
		// basic jumping code
		if(this.jump && this.hasFloor()){
			this.jumpHeight = this.getFloor().getY() - (this.getHeight() * 2);
			this.jumping = true;
		}
		
		if(jumping){
			if(this.getY() > this.jumpHeight){
				this.jumpSpeed -= this.acceleration;
			}else{
				this.jump = this.jumping = false;
			}
		}else{
			if(this.jumpSpeed < 0){
				if(this.jumpSpeed + acceleration <= 0){
					this.jumpSpeed += acceleration;
				}else{
					this.jumpSpeed = 0;
				}
			}
		}
		
		this.offset((int) this.moveSpeed, (int) this.jumpSpeed);
		
		// keep player within defined bounds
		if(this.leftBound >= 0){
			if(this.getX() <= 0){
				this.setPosition(0, this.getY());
			}
		}
		
		if(this.rightBound >= 0){
			if(this.getX() >= this.rightBound - this.getWidth()){
				this.setPosition(this.rightBound - this.getWidth(), this.getY());
			}
		}
	}
	
	public boolean effectsScreen() {
		return effectScreen;
	}

}
