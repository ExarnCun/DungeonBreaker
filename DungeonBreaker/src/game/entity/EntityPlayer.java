package game.entity;

import game.world.GameWorld;
import maths.Dimension2f;
import maths.Point2f;

/**
 * An entityplayer is an entity that listens for input
 */
public abstract class EntityPlayer extends Entity {

	/**
	 * 
	 * <b>Constructor</b>
	 * 
	 * @param location Location of the entity
	 * @param size Size of the entity
	 */
	public EntityPlayer(Point2f location, Dimension2f size) {
		super(location, size);
	}
	
	
	@Override 
	public void Tick(GameWorld world, Object[] args){
		super.Tick(world, args);
		checkInput(world, args);
	}
	
	/**
	 * 
	 * use this method to work with input
	 * 
	 * @param world The world this entity belongs to
	 * @param args additional parameters; can be 'null'
	 */
	public abstract void checkInput(GameWorld world, Object[] args);

}
