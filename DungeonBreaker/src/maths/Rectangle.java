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
	 * @param r the second rectangle
	 * @return whether this rectangle intersects with the second rectangle or not
	 */
	public boolean intersects(Rectangle r){
			
		
		//TODO: Create own code (as this only works for integers)
		return new java.awt.Rectangle((int)Location.X, (int)Location.Y, (int)Size.Width, (int)Size.Height).intersects(
				new java.awt.Rectangle((int)r.Location.X, (int)r.Location.Y, (int)r.Size.Width, (int)r.Size.Height));
		
	}
	
	
	/**
	 * 
	 * checks whether this rectangle contains a point or not
	 * 
	 * @param x X-coordinate of the point
	 * @param y Y-coordinate of the point
	 * @return whether the rectangle contains the point or not
	 */
	public boolean contains(float x, float y){
		return (x >= Location.X && x <= Location.X + Size.Width && y >= Location.Y && y <= Location.Y + Size.Height);
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
