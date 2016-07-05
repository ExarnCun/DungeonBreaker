package game.test;

import game.Item.Item;
import game.block.Block;
import game.world.GameWorld;
import image.ImageLoader;

public class BaumBlock extends Block {

	public BaumBlock(){
		
		this.hasCollision = false;
		this.Texture = ImageLoader.LoadImage("1");
		
	}
	
	@Override
	public void OnEnter(GameWorld world, Object collider) {
		
	}

	@Override
	public void OnItemUse(GameWorld world, Object entity, Item item) {
		
	}

	@Override
	public void OnBreak(GameWorld world, Object entity, Item item) {
		
	}

}
