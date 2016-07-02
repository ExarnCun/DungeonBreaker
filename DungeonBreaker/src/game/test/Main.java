package game.test;

import game.world.GameWorld;
import game.world.regions.RegionLoader;
import maths.Dimension2f;
import maths.Point2f;

public class Main extends GameWorld{

	public Main(int ResolutionX, int ResolutionY) {
		super(ResolutionX, ResolutionY);
	}

	private static final long serialVersionUID = 8591208932639256496L;

	public static void main(String[] args) {
		Main m = new Main(1920, 1080);
		m.setSize(1920,1080);
		m.setUndecorated(true);
		m.region = RegionLoader.CreateRegion(40, 40, 1920 / 40);
		m.region.Blocks[1][1] = new TestBlock(1, 1);
		m.toAdd.add(new TestEntity(new Point2f(2, 20), new Dimension2f(2,2)));
		m.setVisible(true);
		m.start();
	}

}
