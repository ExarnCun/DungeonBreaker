package game.block;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import game.interfaces.Renderable;
import game.world.GameWorld;
import game.world.Region;
import maths.Point2f;
import maths.Rectangle;

/**
 * 
 * A block is a object, that can be rendered by a {@link GameWorld} and has some specific events.<br>
 * Every visible object, that is not an overlay should be a Block.<br>
 * You can also implement some methods in addition to extending the Block so you get even more features!
 * 
 * @author ExarnCun
 *
 */
public abstract class Block extends Rectangle implements Renderable {

	/**
	 * Texture of the Block
	 */
	public BufferedImage Texture;
	
	
	/**
	 * Location of the Block<br>
	 * THIS LOCATION IS NOT THE LOCATION IN PIXELS, THE PIXEL LOCATION IS CALCULATED LIKE THIS:
	 * {@code PixelLocation = new Point((int)(Location.X * BlockSize),(int)(Location.Y * BlockSize))}
	 */
	public Point2f Location;
	
	
	/**
	 * Size of the Block<br>
	 * THIS DIMENSION IS NOT THE DIMENSION IN PIXELS, THE PIXEL DIMENSION IS CALCULATED LIKE THIS:
	 * {@code PixelDimension = new Dimension((int)(Size.X * BlockSize),(int)(Size.Y * BlockSize))}
	 */
	public Point2f Size = new Point2f(1, 1);
	
	
	/**
	 * 
	 * Calculates the location on screen
	 * 
	 * @param r the Region this Block belongs to
	 * @return the Location on screen
	 */
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
	public Dimension SizeOnScreen(Region r){
		return new Dimension((int)(Size.X * r.BlockSize),(int)(Size.Y * r.BlockSize));
	}
	
	@Override
	public void Render(Graphics2D g, GameWorld world, Object[] args){
		g.drawImage(Texture, LocationOnScreen(world.region).x, LocationOnScreen(world.region).y, SizeOnScreen(world.region).width, SizeOnScreen(world.region).height, null);
	}
	
	//TODO: add stuff
	
}
