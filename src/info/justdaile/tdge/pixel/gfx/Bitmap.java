package info.justdaile.tdge.pixel.gfx;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Arrays;

public class Bitmap extends BufferedImage{

	private Point2D activeVector = new Point2D.Double(0, 0);
	private AffineTransform at = new AffineTransform();
	
	public enum AntiAliasing {
		NONE, NEAREST_NEIGHBOUR, BILINEAR
	};
	
	protected int[] pixels;
	protected int[] mask;
	private int colorMask = 0xff;
		
	public Bitmap(int width, int height){
		super(width, height, BufferedImage.TYPE_INT_ARGB);
		this.pixels = ((DataBufferInt) this.getRaster().getDataBuffer()).getData();
		this.init();
	}
	
	public Bitmap(BufferedImage image){
		super(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		this.pixels = ((DataBufferInt) this.getRaster().getDataBuffer()).getData();
		for(int y = 0; y < image.getHeight(); y++){
			for(int x = 0; x < image.getWidth(); x++){
				this.overwritePixel(x, y, image.getRGB(x, y));
			}
		}
		this.init();
	}
	
	private void init(){
		this.mask = new int[this.pixels.length];
		this.createMask();
	}
	
	protected void createMask(){
		Arrays.fill(this.mask, 0); // clear by default
		// create mask
		for(int y = 0; y < this.getHeight(); y++){
			for(int x = 0; x < this.getWidth(); x++){
				if(((this.getPixel(x, y) >> 24) & 0xff) != 0){
					this.mask[x + (y * this.getWidth())] = 1; // set masks pixel to 1
				}
			}
		}
	}
	
	public void updateMask(){
		this.createMask();
	}
	
	public int getPixel(int x, int y){
		return this.getPixel(x + (y * this.getWidth()));
	}
	
	public int getPixel(int n){
		if(n >= 0 && n < this.size()){ // here I make sure pixel is not out of bounds if so I return 0, this prevents errors but bugs too if I am not careful :D
			return this.pixels[n];
		}
		return 0;
	}
	
	public void swapMask(int[] mask){
		this.mask = mask;
	}
	
	public int[] getMask(){
		return this.mask;
	}
	
	public void overwritePixel(int x, int y, int color){
		if(x >= 0 && x <= this.getWidth() - 1 && y >= 0 && y < this.getHeight()){
			this.overwritePixel(x + (y * this.getWidth()), color);
		}
	}
	
	public void overwritePixel(int n, int color){
		if(n >= 0 && n < this.pixels.length){
			this.pixels[n] = color;
		}
	}

	public void fill(int color){
		Arrays.fill(this.pixels, color);
	}
		
	/**
	 * Write bitmap to bitmap
	 * @param bitmap
	 * @param x
	 * @param y
	 */
	public void write(Bitmap bitmap){
		if(bitmap == null){return;}
		for(int y = 0; y < bitmap.getHeight(); y++){
			for(int x = 0; x < bitmap.getWidth(); x++){
				int pixel = bitmap.getPixel(x, y);
				
				at.transform(new Point2D.Double(x, y), activeVector);
				
				int ax = (int) activeVector.getX();
				int ay = (int) activeVector.getY();
				
				if(ax < 0 || ay < 0 || ax > this.getWidth() || ay > this.getHeight()){
					continue;
				}
								
				if(((pixel >> 24) & 0xff) == 255){ // if pixel has no alpha
					this.overwritePixel(ax, ay, pixel);			
				}else if(((pixel >> 24) & 0xff) != 0){ // pixel is not entirely transparent
					int tp = this.getPixel(ax, ay);
					int pa = ((pixel >> 24) & this.colorMask);
					this.overwritePixel(ax, ay, this.mixColor(tp, pixel, pa));
				}
			}
		}		
	}
	
	
	/**
	 * Write bitmap to bitmap
	 * @param bitmap
	 * @param x
	 * @param y
	 */
	public void write(Bitmap bitmap, AntiAliasing aa){
		if(bitmap == null){return;}
		Bitmap temp = new Bitmap(this.getWidth(), this.getHeight());
		temp.fill(0);
		
		// write to temp image
		for(int y = 0; y < bitmap.getHeight(); y++){
			for(int x = 0; x < bitmap.getWidth(); x++){
				int pixel = bitmap.getPixel(x, y);
								
				at.transform(new Point2D.Double(x, y), activeVector);
				
				int ax = (int) activeVector.getX();
				int ay = (int) activeVector.getY();

				temp.overwritePixel(ax, ay, pixel);
				
			}
		}
		
		// apply aliasing to temp image
		for(int y = 0; y < temp.getHeight(); y++){
			for(int x = 0; x < temp.getWidth(); x++){
				int pixels[] = temp.getSurroundingPixels(x, y);
				
				boolean best = false;
				
				if(pixels[4] == 0){ // pixel[4] is the center pixel
					switch(aa){
					// 0 1 2 => a b c 
					// 3 4 5 => h   d
					// 6 7 8 => g f e
					case NEAREST_NEIGHBOUR:
						if(pixels[3] != 0){
							if(pixels[3] == pixels[5]){
								pixels[4] = pixels[3];
								best = true;
							}else if(pixels[5] != 0){
								pixels[4] = pixels[5];
							}
						}
						if(pixels[1] != 0 && !best){
							if(pixels[1] == pixels[7]){
								pixels[4] = pixels[1];
								best = true;
							}else if(pixels[7] != 0){
								pixels[4] = pixels[7];
							}
						}
						if(pixels[0] != 0 && !best){
							if(pixels[0] == pixels[8]){
								pixels[4] = pixels[0];
								best = true;
							}else if(pixels[8] != 0){
								pixels[4] = pixels[8];
							}
						}
						if(pixels[2] != 0 && !best){
							if(pixels[2] == pixels[6]){
								pixels[4] = pixels[2];
								best = true;
							}else if(pixels[6] != 0){
								pixels[4] = pixels[6];
							}
						}
						break;
						
					case BILINEAR:
						if(pixels[3] != 0){
							if(pixels[3] == pixels[5]){
								pixels[4] = pixels[3];
								best = true;
							}else if(pixels[5] != 0){
								pixels[4] = this.mixColor(pixels[3], pixels[5], 255 / 2);
							}
						}
						if(pixels[1] != 0 && !best){
							if(pixels[1] == pixels[7]){
								pixels[4] = pixels[1];
								best = true;
							}else if(pixels[7] != 0){
								pixels[4] = this.mixColor(pixels[1], pixels[7], 255 / 2);
							}
						}
						if(pixels[0] != 0 && !best){
							if(pixels[0] == pixels[8]){
								pixels[4] = pixels[0];
								best = true;
							}else if(pixels[8] != 0){
								pixels[4] = this.mixColor(pixels[0], pixels[8], 255 / 2);
							}
						}
						if(pixels[2] != 0 && !best){
							if(pixels[2] == pixels[6]){
								pixels[4] = pixels[2];
								best = true;
							}else if(pixels[6] != 0){
								pixels[4] = this.mixColor(pixels[2], pixels[6], 255 / 2);
							}
						}
						break;
						
					default:
						break;
					}
					
					temp.overwritePixel(x, y, pixels[4]);

				}
			}
		}
		
		// write temp image to screen
		for(int y = 0; y < temp.getHeight(); y++){
			for(int x = 0; x < temp.getWidth(); x++){
				int pixel = temp.getPixel(x, y);
				
				if(((pixel >> 24) & 0xff) == 255){ // if pixel has no alpha
					this.overwritePixel(x, y, pixel);			
				}else if(((pixel >> 24) & 0xff) != 0){ // pixel is not entirely transparent
					int tp = this.getPixel(x, y);
					int pa = ((pixel >> 24) & this.colorMask);
					this.overwritePixel(x, y, this.mixColor(tp, pixel, pa));
				}
				
			}
		}	
	}
	
	public int[] getSurroundingPixels(int x, int y){
		int[] pixels = new int[9];
		for(int yi = 0; yi < 3; yi++){
			for(int xi = 0; xi < 3; xi++){
				pixels[xi + (yi * 3)] = this.getPixel((x - 1) + xi, (y - 1) + yi);
			}
		}
		return pixels;
	}
	
	public boolean containsColor(int rgb) {
		for(int i = 0; i < this.size(); i++){
			if(this.getPixel(i) == rgb){
				return true;
			}
		}
		return false;
	}

	public void changeColor(int oldColor, int newColor){
		for(int i = 0; i < this.size(); i++){
			if(this.getPixel(i) == oldColor){
				this.overwritePixel(i, newColor);
			}
		}
	}
	
	public int mixColor(int baseColor, int OverlayColor, int overlayAlpha){
		float transparency = overlayAlpha / 255.0f;
		int r = (int) ((((baseColor >> 16) & this.colorMask) * transparency) + (((OverlayColor >> 16) & this.colorMask) * (1.0f - transparency)));
		int g = (int) ((((baseColor >> 8) & this.colorMask) * transparency) + (((OverlayColor >> 8) & this.colorMask) * (1.0f - transparency)));
		int b = (int) (((baseColor & this.colorMask) * transparency) + ((OverlayColor & this.colorMask) * (1.0f - transparency)));
		return (255 << 24) + (r << 16) + (g << 8) + b;
	}
	
	public int size() {
		return this.getWidth() * this.getHeight();
	}
	
	public float getCenterX(){
		return this.getWidth() / 2;
	}
	
	public float getCenterY(){
		return this.getHeight() / 2;
	}
	
	public AffineTransform getTransform() {
		return this.at;
	}
	
	public void setTransform(AffineTransform at){
		this.at = at;
	}
	
}
