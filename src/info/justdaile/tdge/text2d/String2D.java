package info.justdaile.tdge.text2d;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class String2D {
	
	private String string;
	
	public String2D(String string){
		this.string = string;
	}
	
	public BufferedImage createString(Font font, int rgb){
		BufferedImage image = new BufferedImage(font.getSize() * this.string.length(), font.getSize(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		for(int i = 0; i < this.string.length(); i++){
			Char2D currentChar = new Char2D(this.string.charAt(i));
			BufferedImage charImage = currentChar.createImage(font, rgb);
			g2d.drawImage(charImage, charImage.getWidth() * i, 0, null);
		}
		return image;
	}

}
