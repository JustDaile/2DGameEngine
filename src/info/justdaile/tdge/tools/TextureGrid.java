package info.justdaile.tdge.tools;

import info.justdaile.tdge.core.GameScreen;

public class TextureGrid {
	
	private int w, h, r, c;
	private String[][] texture;
	
	public TextureGrid(int width, int height, int columns, int rows){
		this.c = columns;
		this.r = rows;
		this.w = width / columns;
		this.h = height / rows;
		this.texture = new String[r][c];
		
		fill("default"); // fill with default value so that no index is null
	}
	
	public void setTile(int x, int y, String textureID){
		this.texture[y][x] = textureID;
	}
	
	public void fill(String textureID){
		for(int yy = 0; yy < r; yy++){
			for(int xx = 0; xx < c; xx++){
				this.texture[yy][xx] = textureID;
			}
		}
	}
	
	public void draw(GameScreen screen, int xoffs, int yoffs){
		for(int y = 0; y < r; y++){
			for(int x = 0; x < c; x++){
				Texture texture = TextureLibrary.getTexture(this.texture[y][x]);
				if(texture != null){
					screen.draw(texture, xoffs + (w * x), yoffs + (h * y));
				}
			}
		}
	}

}
