package game.entity;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import game.Checked;
import game.interfaces.Collisionable;
import game.interfaces.Renderable;
import game.world.GameWorld;
import game.world.Region;
import maths.Dimension2f;
import maths.Point2f;
import maths.Rectangle;

/**
 * 
 * A entity is like a block, but it is not fixed to a specific index.<br>
 * 'every "living thing" in the game should be an entity (like monsters, players, etc.)'
 *
 */
public abstract class Entity implements Renderable, Collisionable {

	/**
	 * 
	 * <b>Constructor</b>
	 * 
	 * @param location Location of the entity
	 * @param size Size of the entity
	 */
	public Entity(Point2f location, Dimension2f size){
		Location = location;
		Size = size;
	}
	
	/**
	 * Texture of the Entity
	 */
	@Checked(true)
	public BufferedImage Texture;
	
	
	/**
	 * Location of the Entity<br>
	 * THIS LOCATION IS NOT THE LOCATION IN PIXELS, THE PIXEL LOCATION IS CALCULATED LIKE THIS:
	 * {@code PixelLocation = new Point((int)(Location.X * BlockSize),(int)(Location.Y * BlockSize))}
	 */
	@Checked(true)
	public Point2f Location;
	
	
	/**
	 * Size of the Entity<br>
	 * THIS DIMENSION IS NOT THE DIMENSION IN PIXELS, THE PIXEL DIMENSION IS CALCULATED LIKE THIS:
	 * {@code PixelDimension = new Dimension((int)(Size.Width * BlockSize),(int)(Size.Height * BlockSize))}
	 */
	@Checked(true)
	public Dimension2f Size;
	
	
	/**
	 * 
	 * Calculates the location on screen
	 * 
	 * @param r the Region this Entity belongs to
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
	 * @param r the Region this Entity belongs to
	 * @return the Size on screen
	 */
	@Checked(true)
	public Dimension SizeOnScreen(Region r){
		return new Dimension((int)(Size.Width * r.BlockSize),(int)(Size.Height * r.BlockSize));
	}
	
	/**
	 * Renders the entity onto the next frame
	 */
	@Override 
	public void Render(Graphics2D g, GameWorld world, Object[] args){
		g.drawImage(Texture, LocationOnScreen(world.region).x, LocationOnScreen(world.region).y, SizeOnScreen(world.region).width, SizeOnScreen(world.region).height, null);
	}
	
	/**
	 * returns the bounds of this entity
	 */
	@Override
	public Rectangle getCollisionBounds(){
		return new Rectangle(Location, Size);
	}
	
	/**
	 * 
	 * moves an entity
	 * 
	 * @param x how far the entity should move horizontally
	 * @param y how far the entity should move vertically
	 * @param world The world the entity belongs to
	 * @param args additional parameters; can be 'null'
	 * @return whether the entity moved or not
	 */
	public boolean move(float x, float y, GameWorld world, Object[] args){
		
		if(world.collision(new Rectangle(new Point2f(Location.X + x, Location.Y + y), Size)) <= 1){
			Location = new Point2f(Location.X + x, Location.Y + y);
			return true;
		}
		
		return false;
	}
	
	
}
