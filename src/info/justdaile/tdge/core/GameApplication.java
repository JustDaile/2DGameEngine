package info.justdaile.tdge.core;

import info.justdaile.tdge.tools.Toolkit;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public abstract class GameApplication extends Canvas{
	private static final long serialVersionUID = 1L;
	
	private String title;
	private BufferStrategy bs;
	private BufferedImage screen;
	
	public GameApplication(String title, int width, int height, int scale){
		this.setSize(width * scale, height * scale);
		this.screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.title = title;
		this.setName(this.title);
		new Toolkit();
	}
	
	public abstract void update();
	public abstract void draw(Graphics g);
	
	public void render(){
		if(this.bs == null){
			createBufferStrategy(2);
			this.bs = getBufferStrategy();
		}
		Graphics prebuffer = this.screen.getGraphics();
		Graphics drawbuffer = this.bs.getDrawGraphics();
		prebuffer.setColor(Color.black);
		prebuffer.fillRect(0, 0, getWidth(), getHeight());
		prebuffer.setColor(Color.white);
		this.draw(prebuffer);
		drawbuffer.drawImage(this.screen, 0, 0, this.getWidth(), this.getHeight(), null);
		this.bs.show();
		prebuffer.dispose();
	}
	
	public String getTitle(){
		return this.title;
	}
	
}
