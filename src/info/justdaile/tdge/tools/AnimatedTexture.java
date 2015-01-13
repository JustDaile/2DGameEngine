package info.justdaile.tdge.tools;

import java.awt.image.BufferedImage;

import info.justdaile.tdge.basic.Bitmap;

public class AnimatedTexture extends Texture{

	private Bitmap[] scenes;
	private int scenetime, current;
	private long timer;
	
	public AnimatedTexture(int playtime, String name, BufferedImage...scenes){
		super(name, scenes[0]);
		this.scenetime = playtime / scenes.length;
		this.timer = System.currentTimeMillis() + this.scenetime;
		this.current = 0;
		
		this.scenes = new Bitmap[scenes.length];
		for(int i = 0; i < this.scenes.length; i++){
			this.scenes[i] = new Bitmap(scenes[i]);
		}
	}
	
	public void update(){
		if(System.currentTimeMillis() >= this.timer){
			this.timer += this.scenetime;
			this.current++;
			if(this.current >= this.scenes.length){
				this.current = 0;
			}
			this.draw(this.scenes[this.current], 0, 0);
		}
	}
	
}
