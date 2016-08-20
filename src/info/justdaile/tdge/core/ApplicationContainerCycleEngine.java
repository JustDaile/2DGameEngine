package info.justdaile.tdge.core;

import java.awt.Container;

public abstract class ApplicationContainerCycleEngine implements Runnable{

	protected final GameApplication game;
	private Thread loop;
	private boolean running;
	private boolean disableRest;
	private boolean syncRender;
	private float targetFPS;
	
	/**
	 * This class is the main engine of a cycled 
	 * @param game
	 * @param targetFPS
	 */
	public ApplicationContainerCycleEngine(GameApplication game, int targetFPS){
		this.game = game;
		this.running = false;
		this.disableRest = false;
		this.syncRender = false;
		this.targetFPS = targetFPS;
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
		long lastTime = System.nanoTime();
		long accTime = 0;
		boolean freshUpdate = false;
		while(running){
			accTime += System.nanoTime() - lastTime;
			lastTime = System.nanoTime();
			freshUpdate = false;	
			while(accTime >= (1000000000 / this.targetFPS)){
				this.game.update();
				freshUpdate = true;
				accTime -= (1000000000 / this.targetFPS);
			}
			
			if(syncRender && freshUpdate){
				this.game.render();
				this.rest(2);
			}else if(!syncRender){
				this.game.render();
				this.rest(2);
			}else{
				this.rest(2);
			}
			
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
