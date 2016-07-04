package game.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Calendar;
import java.util.Date;

import game.Item.Sword;
import game.entity.Entity;
import game.world.GameWorld;
import maths.Dimension2f;
import maths.Point2f;
import maths.Rectangle;

public class TestSword extends Sword{

	public TestSword(){
		breakLevel = 1;
	}
	
	int cooldown;
	Date Time;
	
	
	@Override
	public void Render(Graphics2D g, GameWorld world, Object[] params) {
		if(RenderBounds != null && cooldown > 30){
			UpdateBounds(world, User, params);
			g.setColor(Color.red);
			g.drawLine((int)RenderBounds.Location.X, (int)RenderBounds.Location.Y,(int)RenderBounds.Location.X + (int)RenderBounds.Size.Width, (int)RenderBounds.Location.Y + (int)RenderBounds.Size.Height);
		}
	}

	Rectangle RenderBounds;
	
	@Override
	public void StartUseing(GameWorld world, Object user, Object[] args) {
		super.StartUseing(world, user, args);
		cooldown = 45;
		Time = Calendar.getInstance().getTime();
	}
	
	@Override
	public void Tick(GameWorld world, Object[] args){
		
		timeout -= timeout > 0 ? 1 : 0;
		cooldown -= cooldown > 0 ? 1 : 0;

		if(cooldown == 0 && itemUsed){
			itemUsed = false;
			EndUseing(world, User, args);
		}
	}
	 
	@Override
	public Rectangle getHitBounds(GameWorld world, Object user, Object[] args) {
		
		if(timeout == 0){
			timeout = 30;
		}
		
		return UpdateBounds(world, user, args).withDivide(world.region.BlockSize);
		
	}

	Rectangle UpdateBounds(GameWorld world, Object user, Object[] args){
		if(user instanceof Entity){
			Entity entity = (Entity)user;
			float looking = entity.Looking;
			Point location = entity.LocationOnScreen(world.region);
			Dimension size = entity.SizeOnScreen(world.region);
			
			while(looking >= 360){
				looking -= 360;
			}
			
			double Y = Math.cos(Math.toRadians(looking)) * 40; //40  is lenght of the sword
			double X = Math.pow(Math.pow(40, 2) - Math.pow(Y, 2), 0.5); //a² + b² = c² -> b = (c² - a²)^0.5 
			
			
			//calculate starting point of hitbox
			
			Point2f StartingPoint;
			float temp = 1;
			
			if(looking < 90){
				StartingPoint = new Point2f(location.x + size.width / 2, location.y);
				temp = -1;
			} else if(looking < 180){
				StartingPoint = new Point2f(location.x + size.width, location.y + size.height / 2);
			} else if(looking < 270){
				StartingPoint = new Point2f(location.x + size.width / 2, location.y + size.height);
				temp = -1;
			} else {
				StartingPoint = new Point2f(location.x, location.y + size.height / 2);
				temp = -1;
			}
			
			
			RenderBounds = new Rectangle(StartingPoint, new Dimension2f(temp * (float)X, temp * (float)Y));
			
			RenderBounds.normalize();
			
			return RenderBounds;
			
		}
		return null;
	}
	
	@Override
	public boolean canItemBeUsed(GameWorld world, Object user, Object[] args) {
		return cooldown == 0;
	}

	@Override
	public void UseItemTick(GameWorld world, Object user, Object[] args) {
		timeout -= timeout > 0 ? 1 : 0;
	}

	@Override
	public void EndUseing(GameWorld world, Object user, Object[] args) {
		if(cooldown == 0){
			
		}
	}

}
