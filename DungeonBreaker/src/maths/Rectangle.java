package maths;

import game.Checked;

/**
 * A Rectangle has a position and a size and also some useful methods and
 * functions
 * 
 */
public class Rectangle {

	/**
	 * 
	 * <b>Constructor</b>
	 * 
	 * @param location
	 *            the Location of the rectangle
	 * @param size
	 *            the Size of the rectangle
	 */
	public Rectangle(Point2f location, Dimension2f size) {
		Location = location;
		Size = size;
	}

	/**
	 * creates a rectangle without any negative values within the size (doesn't change the rectangle)
	 */
	public void normalize(){
		if(Size.Width < 0){
			Size.Width = - Size.Width;
			Location.X -= Size.Width;
		}
		if(Size.Height < 0){
			Size.Height = - Size.Height;
			Location.Y -= Size.Height;
		}
	}
	
	/**
	 * divides the width, size, X-coordinate and Y-coordinate by the given value
	 * 
	 * @param value the value divide
	 * 
	 * @return a new Rectangle with the width, size, X-coordinate and Y-coordinate divided by the given value
	 */
	public Rectangle withDivide(float value){
		return new Rectangle(new Point2f(Location.X / value, Location.Y / value), new Dimension2f(Size.Width / value, Size.Height / value));
	}
	
	@Override
	public String toString() {
		return "Rectangle [Location=" + Location + ", Size=" + Size + "]";
	}

	/**
	 * 
	 * Checks two rectangles for intersection
	 * 
	 * @param r
	 *            the second rectangle
	 * @return whether this rectangle intersects with the second rectangle or
	 *         not
	 */
	public boolean intersects(Rectangle rect) {

		return Location.X < rect.Location.X + rect.Size.Width && Location.X + Size.Width > rect.Location.X && Location.Y < rect.Location.Y + rect.Size.Height && Location.Y + Size.Height > rect.Location.Y;

	}

	/**
	 * 
	 * checks whether this rectangle contains a point or not
	 * 
	 * @param X
	 *            X-coordinate of the point
	 * @param y
	 *            Y-coordinate of the point
	 * @return whether the rectangle contains the point or not
	 */
	public boolean contains(float x, float y) {
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

	// TODO: Add methods and functions

}
