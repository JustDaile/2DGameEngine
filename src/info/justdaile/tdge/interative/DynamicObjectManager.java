package info.justdaile.tdge.interative;

import info.justdaile.tdge.gfx.PixelScreen;
import info.justdaile.tdge.gfx.Texture;

import java.util.ArrayList;

public class DynamicObjectManager{
	
	private ArrayList<GameObject> blocks = new ArrayList<GameObject>();
	
	public DynamicObjectManager(int[][] indexGrid, Texture...textures){
		for(int y = 0; y < indexGrid.length; y++){
			for(int x = 0; x < indexGrid[y].length; x++){
				int index = indexGrid[y][x];
				if(index >= 0){
					blocks.add(new DynamicObject(textures[index].getWidth() * x, textures[index].getHeight() * y, textures[index]));
				}
			}
		}
	}
	
	public void setOffset(float xoffs, float yoffs){
		for(int i = 0; i < this.blocks.size(); i++){
			this.blocks.get(i).setPosition((int) xoffs, (int) yoffs);
		}
	}
	
	public void offset(float xoffs, float yoffs){
		for(int i = 0; i < this.blocks.size(); i++){
			this.blocks.get(i).offset((int) xoffs, (int) yoffs);
		}
	}
	
	public ArrayList<GameObject> getBlocks(){
		return this.blocks;
	}	
	
	public void update(ArrayList<GameObject> objects){
		for(int i = 0; i < blocks.size(); i++){
			blocks.get(i).update(blocks);
		}
		for(int i = 0; i < blocks.size(); i++){
			blocks.get(i).update(objects);
		}
	}
	
	public void draw(PixelScreen screen){
		for(int i = 0; i < blocks.size(); i++){
			blocks.get(i).draw(screen);
		}
	}

}
