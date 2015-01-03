package info.justdaile.tdge.core;

import java.awt.Container;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class FullscreenGameContainer extends Engine{
	
	protected JFrame frame;
	private GraphicsEnvironment enviroment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private GraphicsDevice userMonitor = enviroment.getDefaultScreenDevice();
	
	public FullscreenGameContainer(final GameApplication game){
		super(game);
	}

	@Override
	public void display(){
		frame = new JFrame(userMonitor.getDefaultConfiguration());
		frame.add(this.game);
		userMonitor.setFullScreenWindow(frame);
	}

	@Override
	public Container getDisplay() {
		return userMonitor.getFullScreenWindow();
	}
	
}