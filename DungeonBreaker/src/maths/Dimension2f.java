package maths;

/**
 * 
 * A Dimension2f is a Dimension with width and height as float instead of an
 * Integer
 *
 */
public class Dimension2f {

	@Override
	public String toString() {
		return "Dimension2f [Width=" + Width + ", Height=" + Height + "]";
	}

	/**
	 * 
	 * <b>Constructor</b>
	 * 
	 * @param width
	 *            The Width of the Dimension
	 * @param height
	 *            The Height of the Dimension
	 */
	public Dimension2f(float width, float height) {
		Width = width;
		Height = height;
	}

	/**
	 * Width and Height of the Dimension
	 */
	public float Width, Height;

}
