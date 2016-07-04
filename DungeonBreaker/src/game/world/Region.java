package game.world;

import game.block.Block;

/**
 * 
 * A region is a collection of Blocks<br>
 * A region has a fixed size
 * 
 * @author ExarnCun
 *
 */
public class Region {

	/**
	 * width and height of the Region
	 */
	public int Width, Height;

	/**
	 * Size of a Block in pixels (used for rendering)
	 */
	public int BlockSize;

	/**
	 * a 2-dimensional Array of blocks.<br>
	 * The 1st index is the X-Coordinate of the block in the Region, the 2nd
	 * index is the Y-Coordinate of the block.<b> (if you imagine the screen as
	 * grid, where one unit the {@link BlockSize} starting in the upper-left
	 * corner of your screen, increasing its X-Coordinate to the right and the
	 * Y-Coordinate to the bottom.)<br>
	 * An undefined {@link Block} can be 'null'
	 */
	public Block[][] Blocks;

}
