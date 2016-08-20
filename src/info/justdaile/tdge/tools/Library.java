package info.justdaile.tdge.tools;

import java.util.HashMap;

public abstract class Library <I, E> {

	private HashMap<I, E> items = new HashMap<I, E>();
	
	public Library(){
		this.initLibrary(items);
	}
	
	public abstract void initLibrary(HashMap<I, E> lib);
	
	public E getItem(I id){
		return items.get(id);
	}
	
	public int size(){
		return items.size();
	}

}
