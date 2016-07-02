package world;

import java.util.Calendar;
import java.util.Date;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import javax.swing.JFrame;

import game.Checked;

/**
 * 
 * 'Super-container' of Everything the game provides (Items, Entities, Listeners, ...)
 * 
 * @author ExarnCun
 *
 */
public abstract class World extends JFrame {
	
	/**
	 * 
	 * <b>Constructor</b>
	 * 
	 * @param ResolutionX Horizontal resolution of the game
	 * @param ResolutionY Vertical resolution of the game
	 */
	@Checked(true)
	public World(int ResolutionX, int ResolutionY){
		Xresolution = ResolutionX;
		Yresolution = ResolutionY;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Init();
	}

	/**
	 * Used for Object {@link ObjectInputStream} etc.
	 */
	@Checked(true)
	private static final long serialVersionUID = 7060784873153920329L;


	/**
	 * Separated Thread for Ticking
	 * 
	 * @see Ticker
	 */
	@Checked(true)
	public Ticker TickThread;
	
	
	/**
	 * Ticks per second as double
	 * 
	 * @see #Tick()
	 */
	@Checked(true)
	public double TPS = 60;
	
	
	/**
	 * Separated Thread for Rendering
	 * 
	 * @see Ticker
	 */
	@Checked(true)
	public Renderer RenderThread;
	
	
	/**
	 * Renderings per second as double
	 * 
	 * @see #paint(Graphics)
	 */
	@Checked(true)
	public double RPS = 64;
	
	/**
	 * Background image of the game
	 */
	@Checked(true)
	public BufferedImage GBackground;
	
	/**
	 * next image to draw on JFrame
	 */
	@Checked(true)
	public BufferedImage NextFrame;
	
	/**
	 * Graphics of the game
	 */
	@Checked(true)
	public Graphics2D GGraphics;
	
	/**
	 * Horizontal and vertical resolution of the game
	 */
	@Checked(true)
	public int Xresolution, Yresolution;
	
	
	/**
	 * Initializes the world
	 */
	@Checked(true)
	public void Init(){
		TickThread = new Ticker(TPS, this);
		RenderThread = new Renderer(RPS, this);
		
		GBackground = new BufferedImage(Xresolution, Yresolution, BufferedImage.TYPE_3BYTE_BGR);
		NextFrame = new BufferedImage(Xresolution, Yresolution, BufferedImage.TYPE_4BYTE_ABGR);
		
		GGraphics = NextFrame.createGraphics();
		
		//TODO: add stuff
		
		postInit();
	}
	
	/**
	 * Starts rendering and ticking
	 */
	@Checked(true)
	public void start(){
		RenderThread.start();
		TickThread.start();
	}
	
	/**
	 * Stops rendering and ticking
	 */
	public void stop(){
		RenderThread.stop();
		TickThread.stop();
	}
	
	
	@Checked(true)
	@Override
	public void paint(Graphics g){
		
		GGraphics.drawImage(GBackground, 0, 0, getWidth(), getHeight(), null); //Draw Background
		
		render(GGraphics);
		
		g.drawImage(NextFrame, 0, 0, getWidth(), getHeight(), null); // Draw next Frame on JFrame
		
	}
	
	
	//ABSTRACT METHODS
	
	/**
	 * invoked every time after the {@link #Init() Init-method} is invoked
	 */
	public abstract void postInit();
	
	/**
	 * <b>Tick-Method.</b><br>
	 * Invoked {@link #TPS} times per second
	 */
	@Checked(true)
	public abstract void Tick();
	
	/**
	 * invoked every time when the {@link #paint(Graphics) paint-method} is invoked
	 * 
	 * @param g the Graphics of the game
	 */
	@Checked(true)
	public abstract void render(Graphics2D g);
	
}

/**
 * A separated Thread for calling the Tick-method of the World
 */
@Checked(true)
class Ticker implements Runnable{
	
	/**
	 * Ticks per second
	 */
	@Checked(true)
	double TPS;
	
	/**
	 * as long as this boolean is 'true' the Tick-Thread is running
	 */
	@Checked(true)
	boolean active;
	
	/**
	 * Instance which's Tick-method should be called
	 */
	@Checked(true)
	World Target;
	
	/**
	 * Starts the Tick-Thread
	 */
	@Checked(true)
	public void start(){
		if(!active){
			active = true;
			new Thread(this).start();
		}
	}
	
	/**
	 * Stops the Tick-Thread
	 */
	public void stop(){
		active = false;
	}
	
	
	/**
	 * 
	 * <b>Constructor</b>
	 * 
	 * @param TPS Ticks per second ( how often the Tick-method of the World should be called per second)
	 * @param Target World, which's Tick-method should be called
	 */
	@Checked(true)
	public Ticker(double TPS, World Target){
		this.TPS = TPS;
		this.Target = Target;
	}

	
	/**
	 * 
	 * <b>Basic GameLoop (TickLoop)</b>
	 * 
	 * @author ExarnCun
	 */
	@Checked(true)
	@Override
	public void run() {
		Date a = Calendar.getInstance().getTime();
		Date b = Calendar.getInstance().getTime();
		double TargetTimeout = 1000 / TPS;
		double Timeout = 0;
		while(active){
			Target.Tick();
			if(Timeout > 0){
				try{
					Thread.sleep((long)Timeout);
				} catch(InterruptedException e){
					
				}
			}
			if(Timeout <= 0){
				//TODO: Add information about skipped Ticks
			}
			while(Timeout <= 0){
				Target.Tick();
				Timeout += TargetTimeout;
			}
			b = Calendar.getInstance().getTime();
			Timeout += TargetTimeout + a.getTime() - b.getTime();
			a = Calendar.getInstance().getTime();
		}
	}
	
}

/**
 * A separated Thread for calling the Render-method of the World
 */
@Checked(true)
class Renderer implements Runnable{
	
	/**
	 * Renderings per second
	 */
	@Checked(true)
	double RPS;
	
	/**
	 * as long as this boolean is 'true' the Render-Thread is running
	 */
	@Checked(true)
	boolean active;
	
	/**
	 * Instance which's Render-method should be called
	 */
	@Checked(true)
	World Target;
	
	/**
	 * Starts the Render-Thread
	 */
	@Checked(true)
	public void start(){
		if(!active){
			active = true;
			new Thread(this).start();
		}
	}
	
	/**
	 * Stops the Render-Thread
	 */
	public void stop(){
		active = false;
	}
	
	
	/**
	 * 
	 * <b>Constructor</b>
	 * 
	 * @param RPS Renderings per second ( how often the Render-method of the World should be called per second)
	 * @param Target World, which's Render-method should be called
	 */
	@Checked(true)
	public Renderer(double RPS, World Target){
		this.RPS = RPS;
		this.Target = Target;
	}

	
	/**
	 * 
	 * <b>Basic RenderLoop</b>
	 * 
	 * @author ExarnCun
	 */
	@Checked(true)
	@Override
	public void run() {
		Date a = Calendar.getInstance().getTime();
		Date b = Calendar.getInstance().getTime();
		double TargetTimeout = 1000 / RPS;
		double Timeout = 0;
		while(active){
			try {
				Target.paint(Target.getGraphics());
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(Timeout > 0){
				try{
					Thread.sleep((long)Timeout);
				} catch(InterruptedException e){
					
				}
			}
			if(Timeout <= 0){
				//TODO: Add information about skipped Ticks
			}
			while(Timeout <= 0){
				try {
					Target.paint(Target.getGraphics());
				} catch (Exception e) {
					// TODO: handle exception
				}
				Timeout += TargetTimeout;
			}
			b = Calendar.getInstance().getTime();
			Timeout += TargetTimeout + a.getTime() - b.getTime();
			a = Calendar.getInstance().getTime();
		}
	}
	
}

