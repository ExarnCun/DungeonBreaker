package game.Item;

import java.awt.Point;

import game.entity.Entity;
import game.interfaces.Renderable;
import game.world.GameWorld;
import maths.Rectangle;

public abstract class Sword extends Item implements Renderable{

	/**
	 * damage of the sword
	 */
	public double damage;
	
	/**
	 * how strong the weapon is against blocks
	 */
	public int breakLevel;
	
	@Override 
	public void StartUseing(GameWorld world, Object user, Object[] args){
		Rectangle hitbounds = getHitBounds(world, user, args);
		for(Object o : world.getObjects(hitbounds)){
			if(o instanceof Entity && !o.equals(user)){
				((Entity) o).HitByItem(world, user, this, damage);
			}
		}
		for(Point p : world.getBlocks(hitbounds)){
			world.region.Blocks[p.x][p.y].OnItemUse(world, user, this);
			if(world.region.Blocks[p.x][p.y].breakLevel <= breakLevel){
				world.region.Blocks[p.x][p.y].OnBreak(world, user, this);
				world.region.Blocks[p.x][p.y] = null;
			}
		}
	}
	
	/**
	 * 
	 * @param world the world this item belongs to
	 * @param user the object that used the item
	 * @param args additional parameters; can be 'null'
	 * @return the bounds of the hit-box
	 */
	public abstract Rectangle getHitBounds(GameWorld world, Object user, Object[] args);
}
