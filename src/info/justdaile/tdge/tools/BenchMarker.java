package info.justdaile.tdge.tools;

import java.util.ArrayList;

public class BenchMarker {

	private ArrayList<Integer> collection = new ArrayList<Integer>();
    private long currentMarker, lastMarker;

	public BenchMarker(){
		this.currentMarker = System.nanoTime();
		this.lastMarker = System.nanoTime();
	}
	
	public void mark(){
		collection.add((int) this.calculateMarksPerSec());
		this.lastMarker = this.currentMarker;
		this.currentMarker = System.nanoTime();
		if(collection.size() > 10){
			collection.remove(0);
		}
	}
	
	public long getAverageTimePerMark(){
		long sum = 1;
		for(int i = 0; i < this.collection.size(); i++){
			sum += this.collection.get(i);
		}
		return sum / this.collection.size();
	}
	
	public long calculateMarksPerSec(){
		return 1000000000L / this.getTimeBetweenMarks();
	}
	
	public long getTimeBetweenMarks(){
		return this.currentMarker - this.lastMarker;
	}
	
}
