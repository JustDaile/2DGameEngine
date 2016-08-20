package info.justdaile.tdge.tools;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JFrame;

public class GameOutputDevice {
	
	public int x, y;

	public GameOutputDevice(){
		x = y = 0;
		
		// a simple test frame for sound manipulation
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(500, 500));
		
		frame.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				x = e.getX();
				y = e.getY();
				System.out.println(x + ":" + y);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {

			}
		});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		try {
			AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
			
			// get resources
			line.open();
			line.start(); // line is now ready 
			
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					int vol = 15;
					int freq = 0;
					int angle = 0;
					while(line.isOpen()){
						if(freq++ > y){
							angle += 2;
							freq = 0;
						}
						
						byte[] arr = {(byte)(vol * Math.cos(Math.toRadians(angle))), (byte) (vol * Math.cos(Math.toRadians(angle * y))), (byte) (vol * Math.cos(Math.toRadians(angle * y))), (byte) (vol * Math.cos(Math.toRadians(angle * y)))};
						line.write(arr, 0, 4);
					}					
				}
			}).start();
			
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	
}
