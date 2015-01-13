package info.justdaile.tdge.core;

import info.justdaile.tdge.Engine;

import java.awt.Container;

import javax.swing.JFrame;

public class BasicGameContainer extends Engine{
	
	protected JFrame frame;
	
	public BasicGameContainer(final GameApplication game){
		super(game, 60);
	}
	
	public BasicGameContainer(final GameApplication game, int targetFPS){
		super(game, targetFPS);
	}
	
	@Override
	public void display(){
		this.frame = new JFrame(this.game.getTitle());
		this.frame.add(this.game);
		this.frame.pack();
		this.frame.setResizable(true);
		this.frame.setMinimumSize(this.frame.getSize());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
	}

	@Override
	public Container getDisplay() {
		return this.frame;
	}
	
}
