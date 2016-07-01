package world;

import java.util.Calendar;
import java.util.Date;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import javax.swing.JFrame;

/**
 * 
 * 'Super-container' of Everything the game provides (Items, Entities, Listeners, ...)
 * 
 * @author ExarnCun
 *
 */
public abstract class World extends JFrame {
	


	/**
	 * Used for Object {@link ObjectInputStream} etc.
	 */
	private static final long serialVersionUID = 7060784873153920329L;


	/**
	 * Separated Thread for Ticking
	 * 
	 * @see Ticker
	 */
	public Ticker TickThread;
	
	
	/**
	 * Ticks per second as double
	 * 
	 * @see #Tick()
	 */
	public double TPS = 60;
	
	
	/**
	 * Separated Thread for Rendering
	 * 
	 * @see Ticker
	 */
	public Renderer RenderThread;
	
	
	/**
	 * Renderings per second as double
	 * 
	 * @see #paint(Graphics)
	 */
	public double RPS = 64;
	
	/**
	 * Background image of the game
	 */
	public BufferedImage GBackground;
	
	/**
	 * next image to draw on JFrame
	 */
	public BufferedImage NextFrame;
	
	/**
	 * Graphics of the game
	 */
	public Graphics2D GGraphics;
	
	/**
	 * Horizontal and vertical resolution of the game
	 */
	public int Xresolution, Yresolution;
	
	
	/**
	 * Initializes the world
	 */
	public void Init(){
		TickThread = new Ticker(TPS, this);
		RenderThread = new Renderer(RPS, this);
		
		//TODO: init 'GBackground' and 'NextFrame'
		
		GGraphics = NextFrame.createGraphics();
		
		//TODO: add stuff
		
		postInit();
	}
	
	
	
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
	public abstract void Tick();
	
	/**
	 * invoked every time when the {@link #paint(Graphics) paint-method} is invoked
	 * 
	 * @param g the Graphics of the game
	 */
	public abstract void render(Graphics2D g);
	
}

/**
 * A separated Thread for calling the Tick-method of the World
 */
class Ticker implements Runnable{
	
	/**
	 * Ticks per second
	 */
	double TPS;
	
	/**
	 * as long as this boolean is 'true' the Tick-Thread is running
	 */
	boolean active;
	
	/**
	 * Instance which's Tick-method should be called
	 */
	World Target;
	
	/**
	 * Starts the Tick-Thread
	 */
	public void start(){
		if(!active){
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
class Renderer implements Runnable{
	
	/**
	 * Renderings per second
	 */
	double RPS;
	
	/**
	 * as long as this boolean is 'true' the Render-Thread is running
	 */
	boolean active;
	
	/**
	 * Instance which's Render-method should be called
	 */
	World Target;
	
	/**
	 * Starts the Render-Thread
	 */
	public void start(){
		if(!active){
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
				Target.paint(Target.getGraphics());
				Timeout += TargetTimeout;
			}
			b = Calendar.getInstance().getTime();
			Timeout += TargetTimeout + a.getTime() - b.getTime();
			a = Calendar.getInstance().getTime();
		}
	}
	
}

