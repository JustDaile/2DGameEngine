package info.justdaile.tdge.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public abstract class GameApplication extends Canvas implements Rendered{
	private static final long serialVersionUID = 1L;
	
	private String title;
	private BufferStrategy bs;
	private BufferedImage screen;
	private RenderListener rl;
	private Engine engine;

	public boolean showRuntimeInfo = false;
	
	public GameApplication(String title, int width, int height, int scale){
		this.setSize(width * scale, height * scale);
		this.screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.title = title;
		this.setName(this.title);
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
		if(this.showRuntimeInfo){
			prebuffer.drawString("UDPS : " + this.getEngine().getUpdateCounter().getLastCount(), 2, drawbuffer.getFont().getSize());
			prebuffer.drawString("FPS : " + this.getEngine().getRenderCounter().getLastCount(), 2, drawbuffer.getFont().getSize() * 2);
			prebuffer.drawString("Total Loops : " + this.getEngine().getLoopCounter().getLastCount(), 2, drawbuffer.getFont().getSize() * 3);
		}
		drawbuffer.drawImage(this.screen, 0, 0, this.getWidth(), this.getHeight(), null);
		this.bs.show();
		prebuffer.dispose();
	}
	
	public void addRenderListener(RenderListener rl){
		this.rl = rl;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public Engine getEngine(){
		return this.engine;
	}
	
}
