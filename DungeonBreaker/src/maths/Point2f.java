package maths;

import game.Checked;

/**
 * 
 * A Dimension2f is a Point with x and y as float instead of an Integer
 *
 */
public class Point2f {

	/**
	 * 
	 * <b>Constructor</b>
	 *
	 * @param x X-Coordinate of the Point
	 * @param y Y-Coordinate of the Point
	 */
	@Checked(true)
	public Point2f(float x, float y) {
		X = x;
		Y = y;
	}

	/**
	 * X-and Y-Coordinate of the Point
	 */
	@Checked(true)
	public float X, Y;
	
}
