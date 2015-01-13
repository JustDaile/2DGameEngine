package info.justdaile.tdge.tools;

import java.awt.image.BufferedImage;

import info.justdaile.tdge.basic.Bitmap;

public class Texture extends Bitmap{

	public String name;
	
	public Texture(String name, BufferedImage image) {
		super(image);
		this.name = name;
	}

}
