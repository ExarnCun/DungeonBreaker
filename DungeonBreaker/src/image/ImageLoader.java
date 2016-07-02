package image;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


/**
 * Used to load images from the image package (src/image)<br>
 * Images must be provided as portable network graphics ( = .png)
 */
public class ImageLoader {

	/**
	 * 
	 * Loads an image from the image package (src/image)
	 * 
	 * @param Name Name of the image file (without .png)
	 * @return The image
	 */
	public static BufferedImage LoadImage(String Name){
		try{
			return ImageIO.read(ImageLoader.class.getResourceAsStream(Name + ".png"));
		} catch(Exception e){
			return new BufferedImage(1, 1, BufferedImage.TYPE_3BYTE_BGR);
		}
	}
	
}
