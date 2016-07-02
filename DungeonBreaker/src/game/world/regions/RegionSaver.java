package game.world.regions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import game.world.Region;

/**
 * Saves a region to a file or to a resource
 */
public class RegionSaver {

	/**
	 * 
	 * Saves a region to a resource (src/game.world.regions)
	 * 
	 * @param region The region to save
	 * @param Name The name of the region (without '.reg')
	 */
	public static void SaveRegionToRessource(Region region, String Name){
		try {
			String dir = RegionSaver.class.getResource("/").getFile();
			OutputStream os = new FileOutputStream(dir + Name + ".reg");
			SaveObject(os, region);
		} catch (Exception e) {
			//TODO: Handle Exception
		}
	}
	
	/**
	 * 
	 * Saves a region to a file
	 * 
	 * @param region The region to save
	 * @param Path The Path of the file
	 */
	public static void SaveRegionToFile(Region region, String Path){
		SaveObject(Path, region);
	}

	
	/**
	 * Saves an Object to a OutputStream
	 * 
	 * @param O The OutputStream
	 * @param data The Object to save
	 */
	public static void SaveObject(OutputStream O, Object data){
		try {
			 ObjectOutputStream oos = new ObjectOutputStream(O);
			 oos.writeObject(data);
			 oos.close();
			 O.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
       
	}
	
	/**
	 * Saves an Object to a file
	 * @param path The path of the file
	 * @param data The Object to save
	 */
	public static void SaveObject(String path, Object data){
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.close();
			fos.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
