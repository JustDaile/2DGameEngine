package info.justdaile.tdge.gfx;

import java.awt.image.BufferedImage;

public class Texture extends Bitmap{

	private Bitmap[] scenes;
	private boolean animated;
	private boolean playedOnce;
	private boolean playOnce;
	private int currentScene;
	private long startTime;
	private long sceneTime;
	
	public Texture(BufferedImage baseImage) {
		super(baseImage);
	}
	
	public void addScenes(Bitmap...scenes){
		this.addScenes(1000, false, scenes);
	}
	
	public void setRuntime(long runtime){
		this.sceneTime = runtime / this.scenes.length;
	}
	
	public Texture getMirroredTexture() {
		Texture mirrored = new Texture(this.getMirroredBitmap());
		for(int i = 0; i < this.scenes.length; i++){
			mirrored.addScenes(this.scenes[i].getMirroredBitmap());
			mirrored.setRuntime(this.sceneTime * this.scenes.length);
		}
		return mirrored;
	}
	
	public void addScenes(long runtime, boolean playOnce, Bitmap...scenes){
		this.playOnce = playOnce;
		if(scenes.length >= 1){
			if(this.scenes == null){
				this.scenes = new Bitmap[scenes.length + 1];
				this.scenes[0] = new Bitmap(this);
				for(int i = 0; i < scenes.length; i++){
					this.scenes[i + 1] = scenes[i];
				}
			}else{
				Bitmap[] newArray = new Bitmap[this.scenes.length + scenes.length];
				for(int i = 0; i < newArray.length; i++){
					if(i < this.scenes.length){
						newArray[i] = this.scenes[i];
					}else{
						newArray[i] = scenes[i - this.scenes.length];
					}
				}
				this.scenes = newArray;
			}
			this.currentScene = 0;
			this.animated = true;
			this.playedOnce = false;
			this.setRuntime(runtime);
		}
	}
	
	public void restart(){
		this.playedOnce = false;
		this.currentScene = 0;
		this.startTime = 0;
	}
	
	public void update(){
		if(this.scenes == null || this.playOnce && this.playedOnce){return;}
		if(this.startTime <= 0){
			this.startTime = System.currentTimeMillis();
		}
		long currentTime = System.currentTimeMillis();
		if(currentTime - this.startTime >= this.sceneTime){
			this.currentScene++;
			this.startTime = currentTime;
			if(this.currentScene >= this.scenes.length){
				this.playedOnce = true;
				this.currentScene = 0;
			}
			this.fill(0);
			this.merge(this.scenes[this.currentScene], 0, 0);
		};
	}
	
	public boolean isAnimated(){
		return this.animated;
	}
	
	public boolean playOnlyOnce(){
		return this.playOnce;
	}
	
}
