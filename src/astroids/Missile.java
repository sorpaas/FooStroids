package astroids;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Missile extends Objects {
	//boolean fires = true;
	public static double speed = 7;
	public Craft sender;

	
	public Missile(double[] pos, double[] vel, Craft sender){
		this.pos = pos;
		this.vel = vel;
		this.type = Space.Types.MISSILE;
		this.color = Color.white;
		super.radius = 1;
		super.priority = 0;
		this.sender = sender;
		
	}
	
	@Override
	public void update(Space s) {
		move();

	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.white);
		g.setStroke(new BasicStroke(3));
		g.drawLine((int)pos[0], (int)pos[1], (int)pos[0]+(int)vel[0], (int)pos[1]+(int)vel[1]);
		g.setColor(color);
		g.setStroke(new BasicStroke(2));
		g.drawLine((int)pos[0], (int)pos[1], (int)pos[0]+(int)vel[0], (int)pos[1]+(int)vel[1]);

	}
	
	@Override
	public void collide(Objects obj) {
		if(Vu.eclidianDistance(this.getPos(), obj.getPos()) <= (this.radius+obj.radius) && this.hashCode() != obj.hashCode()){
			switch (obj.type) {
				case SHIP:
					this.kill_me = true;
					break;
					
				case ASTEROID:
					this.kill_me = true;
					break;
				
				default:
					break;
			}
		}
	}
	
	@Override
	public void wallCollide(double[] normal) {
		kill_me = true;
	}

}
