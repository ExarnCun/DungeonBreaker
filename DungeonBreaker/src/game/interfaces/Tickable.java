package game.interfaces;

import game.Checked;
import game.world.GameWorld;

/**
 * Every Tickable has a Tick-method
 */
public interface Tickable {

	/**
	 * 
	 * The Tick-method is called {@link world.World#TPS TPS} Times per second,
	 * once you added the Tickable to the world.
	 * 
	 * @param world
	 *            The world that invokes the method
	 * @param params
	 *            additional Parameters; can be 'null'
	 */
	@Checked(true)
	public void Tick(GameWorld world, Object[] params);

}
