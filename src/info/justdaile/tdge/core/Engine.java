package info.justdaile.tdge.core;

import info.justdaile.tdge.tools.FrameIntervalCalculator;

import java.awt.Container;

public abstract class Engine extends FrameIntervalCalculator implements Runnable{

	protected final GameApplication game;
	protected Thread loop;
	protected boolean running;
	protected boolean disableRest;
	protected boolean syncRender;
	protected int syncUpdates;
	
	public Engine(GameApplication game, int sync){
		super(sync);
		this.game = game;
		this.game.init(this);
		this.running = false;
		this.disableRest = false;
		this.running = false;
		this.disableRest = false;
		this.syncRender = false;
		this.syncUpdates = 60;
	}
	
	public abstract void display();
	public abstract Container getDisplay();
	
	public synchronized void start(){
		if(!this.running){
			this.loop = new Thread(this);
			this.loop.start();
		}
	}
	
	public synchronized void stop(){
		if(this.running){
			this.running = false;
			try{
				this.loop.join();
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run(){
		this.running = true;
		long accTime = 0;
		boolean freshUpdate = false;
		while(running){
			accTime += this.getLastFrameTime();
			while(accTime >= getSingleFrameTime()){
				this.game.update();
				accTime -= getSingleFrameTime();
				freshUpdate = true;
			}
			if(syncRender && freshUpdate){
				this.game.render();
				this.setMarker();
				this.rest(1);
			}else if(!syncRender){
				this.game.render();
				this.setMarker();
				this.rest(1);
			}else{
				this.rest(5);
			}
			freshUpdate = false;			
		}
	}
	
	public void onlyRenderAfterUpdates(boolean syncRender){
		this.syncRender = syncRender;
	}
	
	public void disableResting(boolean disable){
		this.disableRest = disable;
	}
	
	public synchronized void rest(int millis){
		if(!disableRest){
			this.forceRest(millis);
		}
	}
	
	public synchronized void forceRest(int millis){
		try{
			Thread.sleep(millis);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
}
