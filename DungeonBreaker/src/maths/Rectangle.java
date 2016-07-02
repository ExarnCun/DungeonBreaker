package maths;

import game.Checked;

/**
 * A Rectangle has a position and a size and also some useful methods and functions
 * 
 */
public class Rectangle {

	/**
	 * 
	 * <b>Constructor</b>
	 * 
	 * @param location the Location of the rectangle
	 * @param size the Size of the rectangle
	 */
	public Rectangle(Point2f location, Dimension2f size) {
		Location = location;
		Size = size;
	}

	/**
	 * 
	 * Checks two rectangles for intersection
	 * 
	 * @param rectangle the second rectangle
	 * @return whether this rectangle intersects with the second rectangle or not
	 */
	public boolean intersects(Rectangle rectangle){
		return !(Location.X > rectangle.Location.X+rectangle.Size.Width || Location.X+Size.Width < rectangle.Location.X || Location.Y > rectangle.Location.Y+rectangle.Size.Height || rectangle.Location.Y+Size.Height < rectangle.Location.Y);
	}
	
	/**
	 * Location of the Rectangle
	 */
	@Checked(true)
	public Point2f Location;
	
	/**
	 * Size of the Rectangle
	 */
	@Checked(true)
	public Dimension2f Size;
	
	//TODO: Add methods and functions
	
}
