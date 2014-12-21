package info.justdaile.tdge.tools;

public abstract class Texture {

	private String name;
	
	public Texture(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public abstract Object getGraphic();
	
}
