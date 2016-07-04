package game.interfaces;

import game.world.GameWorld;
import maths.Rectangle;

/**
 * 
 * A Collisionable collides with other Collisionables
 * 
 */
public interface Collisionable {

	/**
	 * 
	 * @return the bounds of the Collisionable (if the bounds of two or more
	 *         collisionables intersect the
	 *         {@link #onCollide(GameWorld, Object, Object[]) onCollide-method}
	 *         is invoked)
	 */
	public Rectangle getCollisionBounds();

	/**
	 * 
	 * if the bounds of two or more Collisionables intersect this method is
	 * invoked
	 * 
	 * @param world
	 *            The GameWorld this collisionable belongs to
	 * @param collider
	 *            The Object colliding with this collisionable
	 * @param args
	 *            additional parameters; can be 'null'
	 */
	public void onCollide(GameWorld world, Object collider, Object[] args);

}
