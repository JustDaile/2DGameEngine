package info.justdaile.tdge.core;

import java.util.HashMap;

public class KeyManager {
	
	private HashMap<Integer, Boolean> keyStatus = new HashMap<Integer, Boolean>();

	public boolean check(int keycode) {
		if(!keyStatus.containsKey(keycode)){
			return false;
		}
		return keyStatus.get(keycode);
	}

	public void KeyPressed(int keycode) {
		keyStatus.put(keycode, true);
	}

	public void keyReleased(int keycode) {
		keyStatus.put(keycode, false);
	}

}
