package game.world.regions;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;

import game.Checked;
import game.block.Block;
import game.world.Region;

/**
 * Loads a region from a file or from a resource
 */
public class RegionLoader {

	/**
	 * 
	 * Creates a region
	 * 
	 * @param Width The width of the region ( = how many blocks horizontally)
	 * @param Height The height of the region ( = how many blocks vertically)
	 * @param BlockSize The Size of one block ( in pixels)
	 * @return The region
	 */
	@Checked(true)
	public static Region CreateRegion(int Width, int Height, int BlockSize) {
		Region r = new Region();

		r.Width = Width;
		r.Height = Height;
		r.BlockSize = BlockSize;

		r.Blocks = new Block[Width][Height];

		for (int x = 0; x < Width; x++) {
			for (int y = 0; y < Height; y++) {
				r.Blocks[x][y] = null;
			}
		}

		return r;
	}

	/**
	 * 
	 * Loads a region from a resource (src/game.world.regions)
	 * 
	 * @param Name
	 *            The name of the region (without '.reg')
	 * @return The region
	 */
	public static Region LoadRegionFromRessource(String Name) {
		return (Region) LoadObject(RegionLoader.class.getResourceAsStream(Name + ".reg"));
	}

	/**
	 * 
	 * Loads a region from a file
	 * 
	 * @param Path
	 *            The Path of the file
	 * @return The region
	 */
	public static Region LoadRegionFromFile(String Path) {
		return (Region) LoadObject(Path);
	}

	/**
	 * Loads an Object from a InputStream
	 * 
	 * @param I
	 *            The InputStream
	 * @return The Object to load
	 */
	public static Object LoadObject(InputStream I) {
		try {
			ObjectInputStream ois = new ObjectInputStream(I);
			return ois.readObject();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Loads an Object from a file
	 * 
	 * @param path
	 *            The path of the file
	 * @return The Object to load
	 */
	public static Object LoadObject(String path) {
		try {
			FileInputStream fin = new FileInputStream(path);
			@SuppressWarnings("resource")
			ObjectInputStream ois = new ObjectInputStream(fin);
			return ois.readObject();
		} catch (Exception e) {
			return null;
		}
	}

}
