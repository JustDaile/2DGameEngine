package info.justdaile.tdge.core;

import java.awt.Container;
import java.awt.Point;
import javax.swing.JFrame;

public class ApplicationContainer extends ApplicationContainerCycleEngine{
	
	private JFrame frame;
	
	public ApplicationContainer(final GameApplication game){
		super(game, 60);
		this.init();
	}
	
	public ApplicationContainer(final GameApplication game, int targetFPS){
		super(game, targetFPS);
		game.setGameContainer(this);
		this.init();
	}
	
	private void init(){
		this.frame = new JFrame();
	}
	
	@Override
	public void display(){
		this.frame.setTitle(this.game.getName());
		this.frame.add(this.game);
		this.frame.pack();
		this.frame.setResizable(true);
		this.frame.setMinimumSize(this.frame.getSize());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
		
		this.game.requestFocusInWindow();
	}

	@Override
	public Container getDisplay() {
		return this.frame;
	}

	public Point getLocationOnScreen() {
		return this.frame.getLocationOnScreen();
	}
	
}
