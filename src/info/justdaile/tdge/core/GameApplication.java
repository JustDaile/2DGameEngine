package info.justdaile.tdge.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public abstract class GameApplication extends Canvas implements Rendered{
	private static final long serialVersionUID = 1L;
	
	// object fields
	private String title;
	private BufferStrategy bs;
	private BufferedImage screen;
	private RenderListener rl;
	
	// timing based fields
	private Engine engine;
	private long fpsUpdateTime;
	private float fps;
	
	private int fpsUpdateDelay;
	
	// info
	public boolean showRuntimeInfo = false;
	
	public GameApplication(String title, int width, int height, int scale){
		this.setSize(width * scale, height * scale);
		this.screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.title = title;
		this.setName(this.title);
		this.fpsUpdateDelay = 200;
		this.fpsUpdateTime = System.currentTimeMillis() + fpsUpdateDelay;
		this.fps = 0;
	}
	
	public final void init(Engine engine){
		this.engine = engine;
	}
	
	@Override
	public void render(){
		if(this.bs == null){
			createBufferStrategy(2);
			this.bs = getBufferStrategy();
		}
		Graphics prebuffer = this.screen.getGraphics();
		Graphics drawbuffer = this.bs.getDrawGraphics();
		prebuffer.setColor(Color.black);
		prebuffer.fillRect(0, 0, getWidth(), getHeight());
		if(this.rl != null){
			this.rl.doRender(prebuffer);
		}
		prebuffer.setColor(Color.white);
		if(this.showRuntimeInfo){ // show engine info on screen
			if(System.currentTimeMillis() >= this.fpsUpdateTime){
				this.fps = this.getEngine().calculateFPS();
				this.fpsUpdateTime = System.currentTimeMillis() + this.fpsUpdateDelay;
			}
			prebuffer.drawString("FPS : " + String.format("%.2f", this.fps), 2, drawbuffer.getFont().getSize());
		}
		drawbuffer.drawImage(this.screen, 0, 0, this.getWidth(), this.getHeight(), null);
		this.bs.show();
		prebuffer.dispose();
	}
	
	public void addRenderListener(RenderListener rl){
		this.rl = rl;
	}
	
	public void setFPSUpdateDelay(int millis){
		this.fpsUpdateDelay = millis;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public Engine getEngine(){
		return this.engine;
	}
	
}
