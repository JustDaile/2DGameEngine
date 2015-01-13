package info.justdaile.tdge.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Toolkit {

	public static BufferedImage loadImage(String src) {
		try{
			return ImageIO.read(new File(src));
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
}
