package game.test;

import game.Checked;
import game.Item.Item;
import game.block.Block;
import game.interfaces.Tickable;
import game.world.GameWorld;
import image.ImageLoader;
import maths.Point2f;

public class TestBlock extends Block implements Tickable {

	public TestBlock(int x, int y) {
		Texture = ImageLoader.LoadImage("1");
		Location = new Point2f(x, y);
		breakLevel = 0;
	}

	@Override
	public void OnEnter(GameWorld world, Object entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void OnItemUse(GameWorld world, Object entity, Item item) {
		
	}

	@Checked(true)
	@Override
	public void Tick(GameWorld world, Object[] params) {

	}

	@Override
	public void OnBreak(GameWorld world, Object entity, Item item) {
		
	}

}
