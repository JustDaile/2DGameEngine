package info.justdaile;

import info.justdaile.tdge.core.GameApplication;
import info.justdaile.tdge.gfx.Texture;
import info.justdaile.tdge.interative.DynamicObjectManager;
import info.justdaile.tdge.interative.GameWorld2D;
import info.justdaile.tdge.interative.StaticObjectManager;

public class Level extends GameWorld2D{

	public Level(GameApplication game, int[][] staticObjectIDs, int[][] dynamicObjectIDs, Texture[] textures) {
		super(game, new StaticObjectManager(staticObjectIDs, textures), new DynamicObjectManager(dynamicObjectIDs, textures));
	}

}
