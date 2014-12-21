package info.justdaile.tdge.core;

import java.awt.Container;
import javax.swing.JFrame;

public class BasicGameContainer extends Engine{
	
	private JFrame frame;
	
	public BasicGameContainer(final GameApplication game, int sync){
		super(game, sync);
	}

	@Override
	public void display(){
		this.frame = new JFrame(this.game.getTitle());
		this.frame.add(this.game);
		this.frame.pack();
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
