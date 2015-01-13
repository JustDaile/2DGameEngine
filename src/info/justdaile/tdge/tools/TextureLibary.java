package info.justdaile.tdge.tools;

import java.util.ArrayList;

public abstract class TextureLibary {

	private static ArrayList<Texture> libary = new ArrayList<Texture>();
	
	public TextureLibary(){
		this.initTextures(libary);
	}
	
	public abstract void initTextures(ArrayList<Texture> libary);
	
	public static Texture getTexture(String name){
		for(int i = 0; i < libary.size(); i++){
			Texture current = libary.get(i);
			if(current.name.contentEquals(name)){
				return current;
			}
		}
		return null;
	}
	
}
