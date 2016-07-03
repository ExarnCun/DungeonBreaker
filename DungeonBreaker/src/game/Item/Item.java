package game.Item;

import game.interfaces.Tickable;
import game.world.GameWorld;

public abstract class Item implements Tickable{

	
	//variables
	
	/**
	 * Whether the item is used at the moment or not
	 */
	public boolean itemUsed = false;
	
	/**
	 * how long the item has been used (just ignore :) )
	 */
	protected int timeout = 1;
	
	/**
	 * Object that used the item
	 */
	protected Object User;
	
	
	//methods
	
	/**
	 * 
	 * Invoked, every tick the item is used
	 * 
	 * @param world The world this item belongs to
	 * @param user The object using this item
	 * @param args additional parameters; can be 'null'
	 */
	public void UseItem(GameWorld world, Object user, Object[] args){
		if(canItemBeUsed(world, user, args)){
			if(itemUsed){
				UseItemTick(world, user, args);
				timeout += 1;
			} else {
				User = user;
				StartUseing(world, user, args);
				itemUsed = true;
				timeout += 1;
			}
		}
	}
	
	
	//Overwritten methods
	
	@Override
	public void Tick(GameWorld world, Object[] args){
		timeout -= timeout > 0 ? 1 : 0;
		if(timeout == 0){
			EndUseing(world, User, args);
		}
	}
	
	//Abstract functions
	
	/**
	 * 
	 * checks if the item can be used at the moment
	 * 
	 * @param world The world the item belongs to
	 * @param user The object using this item
	 * @param args additional parameters; can be 'null'
	 * @return Whether this item can be used or not
	 */
	public abstract boolean canItemBeUsed(GameWorld world, Object user, Object[] args);
	
	
	//Abstract methods
	
	/**
	 * 
	 * Invoked every Tick the item is used
	 * 
	 * @param world The world the item belongs to
	 * @param user The object using this item
	 * @param args additional parameters; can be 'null'
	 */
	public abstract void UseItemTick(GameWorld world, Object user, Object[] args);
	
	
	/**
	 * 
	 * Invoked when user of the item starts using the item
	 * 
	 * @param world The world the item belongs to
	 * @param user The object using this item
	 * @param args additional parameters; can be 'null'
	 */
	public abstract void StartUseing(GameWorld world, Object user, Object[] args);

	/**
	 * 
	 * Invoked when user of the item stops using the item
	 * 
	 * @param world The world the item belongs to
	 * @param user The object using this item
	 * @param args additional parameters; can be 'null'
	 */
	public abstract void EndUseing(GameWorld world, Object user, Object[] args);
	
	
}
