package world;

import java.util.Calendar;
import java.util.Date;

import game.Checked;

/**
 * A separated Thread for calling the Tick-method of the World
 */
@Checked(true)
public class Ticker implements Runnable{
	
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