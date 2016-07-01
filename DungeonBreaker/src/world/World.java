package world;


/**
 * 
 * 'Super-container' of Everything the game provides (Items, Entities, Listeners, ...)
 * 
 * @author ExarnCun
 *
 */
public abstract class World {
	
	/**
	 * Ticks per second as double
	 * 
	 * @see #Tick()
	 */
	public double TPS = 60;
	
	
	/**
	 * Tick-Method.
	 * Executed {@link #TPS} times per second
	 */
	public void Tick(){
		
	}
	
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

	@Override
	public void run() {
		
	}
	
}

