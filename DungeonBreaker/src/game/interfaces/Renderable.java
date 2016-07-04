package game.interfaces;

import java.awt.Graphics2D;

import game.Checked;
import game.world.GameWorld;

/**
 * Every Renderable has a Render-method
 */
public interface Renderable {

	/**
	 * The Render-method
	 * 
	 * @param g
	 *            The Graphics of the next frame
	 * @param world
	 *            The World that invoked this method
	 * @param params
	 *            Additional parameters; can be 'null'
	 */
	@Checked(true)
	public void Render(Graphics2D g, GameWorld world, Object[] params);

}
