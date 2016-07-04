package game.test;

import game.Controls;
import game.Item.Item;
import game.entity.EntityPlayer;
import game.world.GameWorld;
import image.ImageLoader;
import maths.Dimension2f;
import maths.Point2f;

public class TestPlayer extends EntityPlayer {

	public float speed = 0.1f;

	public TestPlayer(Point2f location, Dimension2f size) {
		super(location, size);
		Texture = ImageLoader.LoadImage("1");
		this.Items.add(new TestSword());
	}

	@Override
	public void onCollide(GameWorld world, Object collider, Object[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkInput(GameWorld world, Object[] args) {

		// TODO fix input if 2 or more keys are pressed

		if (world.PressedKeys[Controls.KEY_UP]) {
			move(0, -speed, world, args);
			Looking = 0;
		}
		if (world.PressedKeys[Controls.KEY_DOWN]) {
			move(0, speed, world, args);
			Looking = 180;
		}
		if (world.PressedKeys[Controls.KEY_RIGHT]) {
			move(speed, 0, world, args);
			Looking = 90;
		}
		if (world.PressedKeys[Controls.KEY_LEFT]) {
			move(-speed, 0, world, args);
			Looking = 270;
		}
		if (world.PressedKeys[Controls.KEY_SWORD]){
			Items.get(0).UseItem(world, this, args);
		}
	}

	@Override
	public void onHit(GameWorld world, Object sender, Item item, double damage) {
		// TODO Auto-generated method stub

	}

}
