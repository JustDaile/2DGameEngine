package info.justdaile.tdge.text2d;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Char2D {

	private char c;
	
	public Char2D(char c){
		this.c = c;
	}
	
	public char getChar(){
		return this.c;
	}
	
	public BufferedImage createImage(Font font){
		return this.createImage(font, Color.black.getRGB());
	}
	
	public BufferedImage createImage(Font font, int rgb){
		BufferedImage image = new BufferedImage(font.getSize(), font.getSize() + 2, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) image.getGraphics();
		
		// g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2d.setFont(font);
		g2d.setColor(new Color(rgb));
		g2d.drawString("" + this.c, (image.getWidth() / 2) - (g2d.getFontMetrics().charWidth(this.c) / 2), ((g2d.getFontMetrics().getAscent() + g2d.getFontMetrics().getDescent() + g2d.getFontMetrics().getLeading()) - (image.getHeight() / 2)));
		return image;
	}
	
}
