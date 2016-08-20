package info.justdaile.tdge.tools;

public class Map2DLayer {

	private int[][] textureIDs;
	
	public Map2DLayer(int[][] textureIDs){
		this.textureIDs = textureIDs;
	}
	
	public Map2DLayer(int width, int height, int fill) {
		this.textureIDs = new int[height][width];
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				this.textureIDs[y][x] = fill;
			}
		}
	}

	public int getId(int x, int y){
		return this.textureIDs[y][x];
	}
	
	public void changeId(int x, int y, int v){
		this.textureIDs[y][x] = v;
	}
	
	public int getWidth(){
		return this.textureIDs[0].length;
	}
	
	public int getHeight(){
		return this.textureIDs.length;
	}
	
}
