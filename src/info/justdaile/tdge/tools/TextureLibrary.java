package info.justdaile.tdge.tools;

import java.util.ArrayList;

public abstract class TextureLibrary {

	private static final ArrayList<Texture> library = new ArrayList<Texture>();
	
	public TextureLibrary(){
		init(library);
	}
	
	public abstract void init(ArrayList<Texture> library);
	
	public static Texture getTexture(String name){
		for(int i = 0; i < library.size(); i++){
			if(library.get(i).getName().contentEquals(name)){
				return library.get(i);
			}
		}
		return null;
	}

}
