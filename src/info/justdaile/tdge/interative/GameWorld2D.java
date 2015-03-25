package info.justdaile.tdge.interative;

import info.justdaile.tdge.core.GameApplication;
import info.justdaile.tdge.gfx.Background;
import info.justdaile.tdge.gfx.PixelScreen;

public class GameWorld2D {

	public static float gravity = 1f;
	public static float friction = 0.1f;
	public static float maxFallSpeed = 3;
	public static float fallAcceleration = 0.3f;
	
	private Background bg;
	private StaticObjectManager wbm;
	private DynamicObjectManager dbm;
	private InterchangibleObject player;
	private GameApplication game;
	
	public GameWorld2D(GameApplication game, StaticObjectManager wbm, DynamicObjectManager dbm){
		this.game = game;
		this.wbm = wbm;
		this.dbm = dbm;
		bg = new Background(this.game);
	}
	
	public Background getBackground(){
		return this.bg;
	}
	
	public void setPlayer(BasicPlayer player) {
		if(player.effectsScreen()){
			player.setHorizontalBoundings(0, this.game.getWidth());
		}
		this.player = player;
	}
	
	public InterchangibleObject getPlayerObject(){
		return this.player;
	}
	
	public void update(){
		bg.update();
		wbm.update();
		dbm.update(wbm.getBlocks());
		if(player != null){
			player.update(wbm.getBlocks());
			player.update(dbm.getBlocks());
		}
	}
	
	public void horizontalScroll(int xoffs){
		this.scroll(xoffs, 0);
	}
	
	public void verticalScroll(int yoffs){
		this.scroll(0, yoffs);
	}
	
	public void scroll(int xoffs, int yoffs){
		this.wbm.offset(xoffs, yoffs);
		this.dbm.offset(xoffs, yoffs);
	}
	
	public void draw(PixelScreen screen){
		bg.draw(screen);
		wbm.draw(screen);
		dbm.draw(screen);
		player.draw(screen);
	}
	
}
