package info.justdaile.tdge.tools;

public class FrameIntervalCalculator {
	
	public static final long SECOND_IN_NANO = 1000000000L;
	
	private long endMarker;
	private long currentMarker;
	private long lastMarker;
	private int maxFrames;
	
	public FrameIntervalCalculator(int maxFrames){
		this.maxFrames = maxFrames;
		this.endMarker = System.nanoTime() + SECOND_IN_NANO;
		this.currentMarker = System.nanoTime();
		this.lastMarker = 0;
	}
	
	public void setMarker(){
		lastMarker = currentMarker;
		currentMarker = System.nanoTime();
		if(currentMarker >= endMarker){
			endMarker = System.nanoTime() + SECOND_IN_NANO;
		}
	}
	
	public long getTimeLeft(){
		return this.endMarker - System.nanoTime();
	}
	
	public long getTimePassed(){
		return SECOND_IN_NANO - getTimeLeft();
	}
	
	public long getLastFrameTime(){
		return System.nanoTime() - lastMarker;
	}
	
	public float calculateFPS(){
		return this.calculatePossibleFrames(this.getLastFrameTime());
	}
	
	public float calculatePossibleFrames(float nano){
		return (this.getSingleFrameTime() * (SECOND_IN_NANO / nano)) / this.getSingleFrameTime();
	}
	
	public float calculatePossibleFramesLeft(){
		return this.getTimeLeft() / this.getSingleFrameTime();
	}
	
	public float calculatePossibleFramesPast(){
		return this.getTimePassed() / this.getSingleFrameTime();
	}

	public long getSingleFrameTime() {
		return SECOND_IN_NANO / this.maxFrames;
	}
	
	public float getMaxFrames(){
		return SECOND_IN_NANO / this.getSingleFrameTime();
	}

}
