package game.world;

import java.awt.Graphics2D;

import game.block.Block;
import game.interfaces.Renderable;
import game.interfaces.Tickable;
import world.World;

public class GameWorld extends World{

	private static final long serialVersionUID = 955592560605788155L;
	
	/**
	 * The current Region of the world
	 */
	public Region region;
	
	@Override
	public void postInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Tick() {
		for(Block[] b : region.Blocks){
			for(Block bl : b){
				if(bl != null){
					if(bl instanceof Tickable){
						((Tickable) bl).Tick(this, null);
					}
				}
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
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

}
