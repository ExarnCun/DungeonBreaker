package game;

import java.awt.Toolkit;

import javax.swing.JFrame;

import org.jnativehook.keyboard.NativeKeyEvent;

/**
 * this class contains informations like keybindings etc.
 */
public class Controls {
	
	
	//Display-settings
	
	/**
	 * whether the game is in fullscreen mode or not
	 */
	public static boolean FULLSCREEN = true;
	
	/**
	 * window-state of the game
	 */
	public static int WINDOWSTATE = JFrame.MAXIMIZED_BOTH;
	
	/**
	 * horizontal size of the game
	 */
	public static int WIDTH = Toolkit.getDefaultToolkit().getScreenSize().width;
	
	/**
	 * vertical size of the game
	 */
	public static int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	
	/**
	 * horizontal resolution
	 */
	public static int XRESOLUTION = 1280;
	
	/**
	 * vertical resolution
	 */
	public static int YRESOLUTION = 720;
	
	/**
	 * Updates per second
	 */
	public static int UPS = 30;
	
	/**
	 * Renderings per second
	 */
	public static int RPS = 60;
	
	
	//Key-bindings
	
	/**
	 * Key to move up
	 */
	public static int KEY_UP = NativeKeyEvent.VC_W;
	
	/**
	 * Key to move down
	 */
	public static int KEY_DOWN = NativeKeyEvent.VC_W;
	
	/**
	 * Key to move right
	 */
	public static int KEY_RIGHT = NativeKeyEvent.VC_D;
	
	/**
	 * Key to move left
	 */
	public static int KEY_LEFT = NativeKeyEvent.VC_A;
	
	/**
	 * Key to use item
	 */
	public static int KEY_ITEM = NativeKeyEvent.VC_SPACE;
	
	/**
	 * Key to switch between items
	 */
	public static int KEY_SWITCH = NativeKeyEvent.VC_SHIFT_L;
	
	/**
	 * Key to use weapon
	 */
	public static int KEY_SWORD = NativeKeyEvent.VC_ENTER;
	
	
	
}
