package world;

import java.util.Calendar;
import java.util.Date;

import game.Checked;

/**
 * A separated Thread for calling the Render-method of the World
 */
@Checked(true)
public class Renderer implements Runnable {

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
	public void start() {
		if (!active) {
			active = true;
			new Thread(this).start();
		}
	}

	/**
	 * Stops the Render-Thread
	 */
	public void stop() {
		active = false;
	}

	/**
	 * 
	 * <b>Constructor</b>
	 * 
	 * @param RPS
	 *            Renderings per second ( how often the Render-method of the
	 *            World should be called per second)
	 * @param Target
	 *            World, which's Render-method should be called
	 */
	@Checked(true)
	public Renderer(double RPS, World Target) {
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
		while (active) {
			try {
				Target.paint(Target.getGraphics());
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (Timeout > 0) {
				try {
					Thread.sleep((long) Timeout);
				} catch (InterruptedException e) {

				}
			}
			if (Timeout <= 0) {
				// TODO: Add information about skipped Ticks
			}
			while (Timeout <= 0) {
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