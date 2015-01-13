package info.justdaile.tdge.tools;

public class BenchMarker {

	private long startTime, currentMarker, lastMarker;
	
	public BenchMarker(){
		this.startTime = System.nanoTime();
		this.currentMarker = this.startTime;
		this.lastMarker = this.startTime;
	}
	
	public void setMarker(){
		this.lastMarker = this.currentMarker;
		this.currentMarker = System.nanoTime();
	}
	
	/**
	 * @return possible marks the can occure if the setMarker method is called at the current Time Bettween marks for 1 second
	 */
	public float calculateMarksPerSec(){
		return 1000000000F / this.getTimeBetweenMarks();
	}
	
	public long getTimeBetweenMarks(){
		return this.currentMarker - this.lastMarker;
	}
	
	public long getTimeSinceStart(){
		return this.currentMarker - this.startTime;
	}
	
}
