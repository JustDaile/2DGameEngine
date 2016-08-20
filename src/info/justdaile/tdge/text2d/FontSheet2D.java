package info.justdaile.tdge.text2d;

import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.util.HashMap;

import info.justdaile.tdge.pixel.gfx.Bitmap;
import info.justdaile.tdge.pixel.gfx.PixelScreen;

public class FontSheet2D {
	
	private HashMap<Character, Bitmap> characters = new HashMap<Character, Bitmap>();
	private Font font;
	private int color;
		
	/**
	 * A 2D Fontsheet created out of bitmaps, each character is created only on use
	 * and then stored incase of use again. This prevents having to create a list of characters
	 * you may use and saves memory. However the first time a character is use it has to be created.
	 * @param font
	 *             - java.awt.Font
	 * @param color
	 *             - text color
	 */
	public FontSheet2D(Font font, int color){
		this.font = font;
		this.color = color;
	}
	
	public void drawTo(PixelScreen screen, String string, int tx, int ty){
		this.drawTo(screen, string, tx, ty, 0);
	}
	
	public void drawTo(PixelScreen screen, String string, int tx, int ty, int xa){
		AffineTransform defaultTransform = screen.getTransform();
				
		for(int i = 0; i < string.length(); i++){
			AffineTransform effectedTransform = new AffineTransform();
			effectedTransform.translate(tx + ((font.getSize() - xa) * i), ty);
			screen.setTransform(effectedTransform);
			Bitmap select = characters.get(string.charAt(i));
			if(select != null){
				screen.draw(select);
			}else{
				characters.put(string.charAt(i), new Bitmap(new Char2D(string.charAt(i)).createImage(font, color)));
				i--;
			}
		}
		
		screen.setTransform(defaultTransform);
	}
	
	public int getFontSize(){
		return this.font.getSize();
	}
	
}
