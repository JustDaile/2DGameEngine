package info.justdaile.tdge.tools;

public class IntervalCounter {
	
	public static final long SECOND_IN_NANO = 1000000000L;
	
	private long endMarker;
	private long currentMarker;
	private long lastMarker;
	private int last;
	private int total;
	
	public IntervalCounter(){
		this.endMarker = System.nanoTime() + IntervalCounter.SECOND_IN_NANO;
		this.currentMarker = System.nanoTime();
		this.lastMarker = this.currentMarker;
	}
	
	public void setMarker(){
		this.lastMarker = this.currentMarker;
		this.currentMarker = System.nanoTime();
		this.total++;
		if(this.currentMarker >= this.endMarker){
			this.reset();
		}
	}
	
	public long getTimePassed(){
		return this.currentMarker - this.lastMarker;
	}
	
	public int getLastCount(){
		return this.last;
	}
	
	public int getCurrentCount(){
		return this.total;
	}
	
	public void reset(){
		this.endMarker += IntervalCounter.SECOND_IN_NANO;
		this.last = this.total;
		this.total = 0;
	}

}
