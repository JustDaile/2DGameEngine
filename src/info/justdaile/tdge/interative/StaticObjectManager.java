package info.justdaile.tdge.interative;

import info.justdaile.tdge.gfx.PixelScreen;
import info.justdaile.tdge.gfx.Texture;

import java.util.ArrayList;

public class StaticObjectManager{
	
	private ArrayList<GameObject> blocks = new ArrayList<GameObject>();
	
	public StaticObjectManager(int[][] indexGrid, Texture...textures){
		for(int y = 0; y < indexGrid.length; y++){
			for(int x = 0; x < indexGrid[y].length; x++){
				int index = indexGrid[y][x];
				if(index >= 0){
					blocks.add(new StaticObject(textures[index].getWidth() * x, textures[index].getHeight() * y, textures[index]));
				}			
			}
		}
	}
	
	public void setOffset(int xoffs, int yoffs){
		for(int i = 0; i < this.blocks.size(); i++){
			this.blocks.get(i).setPosition(xoffs, yoffs);
		}
	}
	
	public void offset(int xoffs, int yoffs){
		for(int i = 0; i < this.blocks.size(); i++){
			this.blocks.get(i).offset(xoffs, yoffs);
		}
	}
	
	public ArrayList<GameObject> getBlocks(){
		return this.blocks;
	}
	
	public void update(){
		for(int i = 0; i < blocks.size(); i++){
			blocks.get(i).update();
		}
	}
	
	public void draw(PixelScreen screen){
		for(int i = 0; i < blocks.size(); i++){
			blocks.get(i).draw(screen);
		}
	}
	
}