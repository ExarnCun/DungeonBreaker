package game.test;

import game.Checked;
import game.Item.Item;
import game.block.Block;
import game.entity.Entity;
import game.interfaces.Tickable;
import game.world.GameWorld;
import image.ImageLoader;
import maths.Point2f;

public class TestBlock extends Block implements Tickable{

	public TestBlock(int x, int y){
		Texture = ImageLoader.LoadImage("1");
		Location = new Point2f(x, y);
	}
	
	@Override
	public void OnEnter(GameWorld world, Object entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void OnItemUse(GameWorld world, Object entity, Item item) {
		// TODO Auto-generated method stub
		
	}

	@Checked(true)
	@Override
	public void Tick(GameWorld world, Object[] params) {
		
	}

	@Override
	public void OnBreak(GameWorld world, Object entity, Item item) {
		// TODO Auto-generated method stub
		
	}	

}
