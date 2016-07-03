package game.block;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import game.Checked;
import game.Item.Item;
import game.entity.Entity;
import game.interfaces.Renderable;
import game.world.GameWorld;
import game.world.Region;
import maths.Dimension2f;
import maths.Point2f;
import maths.Rectangle;

/**
 * 
 * A block is a object, that can be rendered by a {@link GameWorld} and has some specific events.<br>
 * Every "dead" visible object, that is not an overlay should be a Block.<br>
 * You can also implement some methods in addition to extending the Block so you get even more features!
 * 
 * @author ExarnCun
 *
 */
public abstract class Block implements Renderable {

	/**
	 * how strong an item must be to break this block
	 */
	public int breakLevel = 1;
	
	/**
	 * Texture of the Block
	 */
	@Checked(true)
	public BufferedImage Texture;
	
	/**
	 * Whether entities collide (can't go through) this block or not
	 */
	public boolean hasCollision = true;
	
	/**
	 * Location of the Block<br>
	 * THIS LOCATION IS NOT THE LOCATION IN PIXELS, THE PIXEL LOCATION IS CALCULATED LIKE THIS:
	 * {@code PixelLocation = new Point((int)(Location.X * BlockSize),(int)(Location.Y * BlockSize))}
	 */
	@Checked(true)
	public Point2f Location;
	
	
	/**
	 * Size of the Block<br>
	 * THIS DIMENSION IS NOT THE DIMENSION IN PIXELS, THE PIXEL DIMENSION IS CALCULATED LIKE THIS:
	 * {@code PixelDimension = new Dimension((int)(Size.Width * BlockSize),(int)(Size.Height * BlockSize))}
	 */
	public Dimension2f Size = new Dimension2f(1, 1);
	
	
	/**
	 * 
	 * Calculates the location on screen
	 * 
	 * @param r the Region this Block belongs to
	 * @return the Location on screen
	 */
	@Checked(true)
	public Point LocationOnScreen(Region r){
		return new Point((int)(Location.X * r.BlockSize),(int)(Location.Y * r.BlockSize));
	}
	
	/**
	 * 
	 * Calculates the Size on screen
	 * 
	 * @param r the Region this Block belongs to
	 * @return the Size on screen
	 */
	@Checked(true)
	public Dimension SizeOnScreen(Region r){
		return new Dimension((int)(Size.Width * r.BlockSize),(int)(Size.Height * r.BlockSize));
	}
	
	@Checked(true)
	@Override
	public void Render(Graphics2D g, GameWorld world, Object[] args){
		g.drawImage(Texture, LocationOnScreen(world.region).x, LocationOnScreen(world.region).y, SizeOnScreen(world.region).width, SizeOnScreen(world.region).height, null);
	}
	
	/**
	 * @return the bounds of this block
	 */
	public Rectangle getBounds() {
		return new Rectangle(Location, Size);
	}
	
	//TODO: add stuff
	
	//Abstract methods
	
	/**
	 * 
	 * Invoked, when a Entity collides with this block, or enters it.
	 * 
	 * @param world The world this block belongs to
	 * @param collider The object which entered ( / collided with) this block
	 */
	public abstract void OnEnter(GameWorld world, Object collider);
	
	/**
	 * 
	 * Invoked, when a item is used on this block
	 * 
	 * @param world The world this block belongs to
	 * @param entity The object which holds the item
	 * @param item The item used on this block
	 */
	public abstract void OnItemUse(GameWorld world, Object entity, Item item);

	/**
	 * 
	 * Invoked, when this block breaks
	 *  
	 * @param world The world this block belongs to
	 * @param entity The object which holds the item
	 * @param item The item used to break this block
	 */
	public abstract void OnBreak(GameWorld world, Object entity, Item item);
	
}
