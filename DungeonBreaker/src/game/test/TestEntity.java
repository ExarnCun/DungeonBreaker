package game.test;

import game.entity.Entity;
import game.interfaces.Tickable;
import game.world.GameWorld;
import image.ImageLoader;
import maths.Dimension2f;
import maths.Point2f;

public class TestEntity extends Entity implements Tickable{

	public TestEntity(Point2f location, Dimension2f size) {
		super(location, size);
		Texture = ImageLoader.LoadImage("1");
	}

	@Override
	public void onCollide(GameWorld world, Object collider, Object[] args) {
		
	}

	@Override
	public void Tick(GameWorld world, Object[] params) {
		this.move(0.01f, -0.01f, world, null);
	}

}
