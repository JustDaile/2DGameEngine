package info.justdaile.tdge.core;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public abstract class GameApplication extends Canvas implements MouseMotionListener, MouseListener, KeyListener{

	private static final long serialVersionUID = 1L;
	
	private ApplicationContainer container;
	
	private String title;
	private int scale;

	protected BufferStrategy bs;
	private BufferedImage screen;
	protected Image cursor;
	private boolean usesGameCursor, gameCursorShown;
	
	private Robot robot;
	private int lock[];
	private float mx = 0;
	private float my = 0;
	private float cursorHorizontalSensitivity = 0.1F;
	private float cursorVerticalSensitivity = 0.1F;
	
	private KeyManager keyManager = new KeyManager();
	
	/**
	 * A GameApplication using Pixel based graphics rendering and a build in 
	 * affine transform. 
	 * @param title
	 *             - Application Title, title displayed on the window
	 * @param width
	 *             - Width of the screen
	 * @param height
	 *             - Height of the screen
	 * @param scale
	 *             - Scale factor of the Screen, overall size of window is Width + Height * scale
	 */
	public GameApplication(String title, int width, int height, int scale){
		this.setSize(width * scale, height * scale);
		this.screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		this.title = title;
		this.scale = scale;
		this.usesGameCursor = false;
		this.gameCursorShown = false;
		this.setName(this.title);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
	}
	
	/**
	 * Use the pre-build ingame mouse function.
	 * You must first setGraphicCursor
	 */
	public void useInGameCursor(){
		try {
			GraphicsDevice monitor = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			this.robot = new Robot(monitor);
			
			this.lock = new int[2];
			
			if(this.getGameContainer() != null){
				Point p = this.getGameContainer().getLocationOnScreen();
				this.lock[0] = p.x + this.getWidth() / 2;
				this.lock[1] = p.y + this.getHeight() / 2;
			}else{
				this.lock[0] = monitor.getDisplayMode().getWidth() / 2;
				this.lock[1] = monitor.getDisplayMode().getHeight() / 2;
			}
			
			
			this.mx = this.getWidth() / 2;
			this.my = this.getHeight() / 2;
			
			this.setCursor(java.awt.Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "cursor"));
		} catch (HeadlessException | AWTException e) {
			e.printStackTrace();
		}
		this.usesGameCursor = true;
		this.gameCursorShown = true;
	}
	
	/**
	 * Use the standard OS cursor
	 */
	public void useDefaultCursor(){
		this.setCursor(Cursor.getDefaultCursor());
		this.usesGameCursor = false;
		this.gameCursorShown = false;
	}
	
	/**
	 * This method switches to in-game cursor
	 * and does not draw it. Giving the impression of 
	 * having no cursor
	 */
	public void hideCursor(){
		this.useInGameCursor();
		this.gameCursorShown = false;
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		this.keyManager.KeyPressed(e.getKeyCode());
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		this.keyManager.keyReleased(e.getKeyCode());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		this.mouseMoved(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(this.usesGameCursor){
			int xdiff = this.lock[0] - e.getXOnScreen();
			int ydiff = this.lock[1] - e.getYOnScreen();
			this.robot.mouseMove(lock[0], lock[1]);
			this.mx -= this.cursorHorizontalSensitivity * xdiff;
			this.my -= this.cursorVerticalSensitivity * ydiff;
			
			if(this.mx < 0){
				this.mx = 0;
			}else if(this.mx > (this.getWidth()  / this.scale) - 1){
				this.mx = (this.getWidth() / this.scale) - 1;
			}
			
			if(this.my < 0){
				this.my = 0;
			}else if(this.my > (this.getHeight() / this.scale) - 1){
				this.my = (this.getHeight() / this.scale) - 1;
			}
		}else{
			this.mx = e.getX();
			this.my = e.getY();
		}
	}

	/**
	 * @return
	 *        - x position of the mouse cursor on the application screen.
	 */
	public float getMouseX(){
		return this.mx;
	}
	
	/**
	 * @return
	 *        - y position of the mouse cursor on the application screen.
	 */
	public float getMouseY(){
		return this.my;
	}
	
	/**
	 * Update game logic
	 */
	public abstract void update();
	
	/**
	 * Draw game graphics
	 * @param g 
	 *          - Graphics object used for drawing
	 */
	public abstract void draw(Graphics2D g2d);
	
	/**
	 * Render drawn graphics to the screen
	 */
	public void render(){
		if(this.bs == null){
			createBufferStrategy(2);
			this.bs = getBufferStrategy();
		}
		Graphics2D prebuffer = (Graphics2D) this.screen.getGraphics();
		Graphics drawbuffer = this.bs.getDrawGraphics();
		prebuffer.setColor(Color.black);
		prebuffer.fillRect(0, 0, getWidth(), getHeight());
		this.draw(prebuffer);
		if(this.cursor != null && this.usesGameCursor && this.gameCursorShown){
			prebuffer.drawImage(cursor, (int) this.getMouseX(), (int) this.getMouseY(), null);
		}
		drawbuffer.drawImage(this.screen, 0, 0, this.getWidth(), this.getHeight(), null);
		this.bs.show();
		prebuffer.dispose();
	}
	
	/**
	 * @return 
	 *         - Application's title
	 */
	public String getTitle(){
		return this.title;
	}
	
	/**
	 * @return
	 *         - Imaged used as cursor
	 */
	public Image getGraphicCursor(){
		return this.cursor;
	}
	
	/**
	 * @param cursor
	 *              - Image to be used as a cursor
	 */
	public void setGraphicCursor(Image cursor){
		this.cursor = cursor;
	}
	
	/**
	 * Set the sensitivity of the mouse
	 * @param horizontal
	 *                  - Sensitivity on the X-axis
	 * @param vertical
	 *                  - Sensitivity on the Y-axis
	 */
	public void setGraphicCursorSensitivity(float horizontal, float vertical){
		this.cursorHorizontalSensitivity = horizontal;
		this.cursorVerticalSensitivity = vertical;
	}

	/**
	 * @return
	 *        - if using the default cursor
	 */
	public boolean isUsingDefaultCursor() {
		return !this.usesGameCursor;
	}
	
	/**
	 * if using the set graphical curso
	 * @return
	 *         - returns true only if useGraphicCursor has been called, is shown and an image has been set 
	 */
	public boolean isUsingGameCursor(){
		return this.usesGameCursor && this.gameCursorShown && this.cursor != null;
	}

	/**
	 * 
	 * @param container
	 *                  - The Container class of this Application
	 */
	protected final void setGameContainer(ApplicationContainer container) {
		this.container = container;
	}
	
	protected final ApplicationContainer getGameContainer(){
		return this.container;
	}

	public int getScreenWidth() {
		return this.screen.getWidth();
	}

	public int getScreenHeight() {
		return this.screen.getHeight();
	}

	public KeyManager getKeyManager() {
		return this.keyManager;
	}
	
}
