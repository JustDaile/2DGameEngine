package info.justdaile.tdge.gfx;

public class BackgroundLayer {
		
	private float xoffs, yoffs, hinc, vinc;
	private Texture texture;
	private Bitmap frame;
		
	public BackgroundLayer(float horizontalIncrement, float verticalIncrement, Texture texture){
		this.hinc = horizontalIncrement;
		this.vinc = verticalIncrement;
		this.texture = texture;
		captureFrame();
	}
		
	private void captureFrame(){
		if(this.frame == null){
			this.frame = new Bitmap(this.texture.getWidth(), this.texture.getHeight());
		}
		this.frame.fill(0);
		for(int y = 0; y < 3; y++){
			for(int x = 0; x < 3; x++){
				this.frame.merge(this.texture, (int) (this.xoffs - this.texture.getWidth()) + (this.texture.getWidth() * x), (int) (this.yoffs - this.texture.getHeight())  + (this.texture.getHeight() * y));
			}
		}
	}
	
	public Bitmap getCurrentFrame(){
		return frame;
	}
	
	public void update(){
		this.texture.update();
		this.xoffs += hinc;
		this.yoffs += vinc;
		if(hinc > 0){
			if(this.xoffs >= this.frame.getWidth()){
				this.xoffs += -this.frame.getWidth();
			}
		}else if(hinc < 0){
			if(this.xoffs <= -this.frame.getWidth()){
				this.xoffs += this.frame.getWidth();
			}
		}
		if(vinc > 0){
			if(this.yoffs >= this.frame.getHeight()){
				this.yoffs += -this.frame.getHeight();
			}
		}else if(vinc < 0){
			if(this.yoffs <= -this.frame.getHeight()){
				this.yoffs += this.frame.getHeight();
			}
		}
		this.captureFrame();
	}
	
}
	
