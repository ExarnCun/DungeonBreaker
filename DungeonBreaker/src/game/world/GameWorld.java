package game.world;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.jnativehook.DefaultDispatchService;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import game.Checked;
import game.block.Block;
import game.interfaces.Collisionable;
import game.interfaces.Renderable;
import game.interfaces.Tickable;
import maths.Rectangle;
import world.World;

public class GameWorld extends World implements NativeKeyListener{

	/**
	 * 
	 * <b>Constructor</b>
	 * 
	 * @param ResolutionX Horizontal resolution of the game
	 * @param ResolutionY Vertical resolution of the game
	 */
	@Checked(true)
	public GameWorld(int ResolutionX, int ResolutionY)  {
		super(ResolutionX, ResolutionY);
		
	}
		

	private static final long serialVersionUID = 955592560605788155L;
	
	/**
	 * Pressed keys
	 */
	public boolean[] PressedKeys = new boolean[300];
	
	/**
	 * The current Region of the world
	 */
	@Checked(true)
	public Region region;
	
	/**
	 * Objects added to the world (like Entities etc. BUT NO BLOCKS!)
	 */
	public List<Object> Objects = new ArrayList<Object>();
	
	/**
	 * Objects that will be removed next Tick
	 */
	public List<Object> toRemove = new ArrayList<Object>();
	
	/**
	 * Objects that will be added next Tick
	 */
	public List<Object> toAdd = new ArrayList<Object>();
	
	/**
	 * 
	 * 
	 * @param bounds the rectangle
	 * @return the number of objects / blocks with collision within the rectangle
	 */
	public int collision(Rectangle bounds){
		int ret = 0;
		for(Block[] b : region.Blocks){
			for(Block bl : b){
				if(bl != null){
					if(bl.hasCollision && bl.getBounds().intersects(bounds)){
						ret += 1;
					}
				}
			}
		}
		for(Object o : Objects){
			if(o instanceof Collisionable){
				if(((Collisionable) o).getCollisionBounds().intersects(bounds)){
					ret += 1;
				}
			}
		}
		return ret;
	}
	
	
	/**
	 * 
	 * @param bounds the rectangle
	 * @return objects within the rectangle
	 */
	public List<Object> getObjects(Rectangle bounds){
		List<Object> ret = new ArrayList<Object>();
		for(Object o : Objects){
			if(o instanceof Collisionable){
				if(((Collisionable) o).getCollisionBounds().intersects(bounds)){
					ret.add(o);
				}
			}
		}
		return ret;
	}
	
	
	/**
	 * 
	 * @param bounds the rectangle
	 * @return indices of blocks within the rectangle
	 */
	public List<Point> getBlocks(Rectangle bounds){
		List<Point> ret = new ArrayList<Point>();
		
		for(int x = 0; x < region.Width; x ++){
			for(int y = 0; y < region.Height; y++){
				if(region.Blocks[x][y] != null){
					if(region.Blocks[x][y].getBounds().intersects(bounds)){
						ret.add(new Point(x, y));
					}
				}
			}
		}
		
		return ret;
	}
	
	@Override
	public void postInit() {
		GlobalScreen.setEventDispatcher(new DefaultDispatchService());
		LogManager.getLogManager().reset();
		Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
		logger.setLevel(Level.OFF);
		try {
			GlobalScreen.registerNativeHook();
			
		} catch (NativeHookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void start(){
		super.start();
		GlobalScreen.addNativeKeyListener(this);
	}

	@Override
	public void Tick() {
		
		for(Object o : toAdd){
			Objects.add(o);
		}
		for(Object o : toRemove){
			Objects.remove(o);
		}
		toAdd.clear();
		toRemove.clear();
		
		for(Object o : Objects){
			if(o instanceof Tickable){
				((Tickable) o).Tick(this, null);
			}
		}
		
		for(Block[] b : region.Blocks){
			for(Block bl : b){
				if(bl != null){
					if(bl.hasCollision){
						for(Object o : Objects){
							if(o instanceof Collisionable){
								if(((Collisionable) o).getCollisionBounds().intersects(bl.getBounds())){
									((Collisionable) o).onCollide(this, bl, null);
									bl.OnEnter(this, o);
								}
							}
						}
					}
				}
				if(bl instanceof Tickable){
					((Tickable) bl).Tick(this, null);
				}
			}
		}
		
	}

	@Override
	public void render(Graphics2D g) {
		
		try {
			for(Object o : Objects){
				if(o instanceof Renderable){
					((Renderable) o).Render(GGraphics, this, null);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		for(Block[] b : region.Blocks){
			for(Block bl : b){
				if(bl != null){
					if(bl instanceof Renderable){
						((Renderable) bl).Render(GGraphics, this, null);
					}
				}
			}
		}
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		PressedKeys[e.getKeyCode()] = true;
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		PressedKeys[e.getKeyCode()] = false;
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		
	}

}
