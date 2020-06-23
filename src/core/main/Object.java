package core.main;

import core.utils.Constants;

import java.awt.*;

public class Object extends GameObject {

	private int target = 1000; // id of the pursuee ----- default value is 1000 since there will never be 1000 dots

	int c = 2;

	int numObjects = -1;

	private Handler handler;
	private Main main;

	float dirX, dirY;

	public Object(float x, float y, int id, Handler handler, Main main) {
		super(x, y, id);
		this.handler = handler;
		this.main = main;
	}

	public Object(Handler handler, Main main, int numObjects) {
		this.handler = handler;
		this.main = main;
		this.numObjects = numObjects - 1;
	}

	@Override
	public void tick() {
		if(target == 1000) {
			target = this.id == numObjects ? 0 : id + 1;
		}
		float x2 = getXById(target);
		float y2 = getYById(target);
		float mag = (float) Math.sqrt(Math.pow((x2-x),2) + Math.pow(y2-y,2));

		dirX = (x2-x) / mag;
		dirY = (y2-y) / mag;

		velX = dirX * Constants.SPEED;
		velY = dirY * Constants.SPEED;


		if (c == 2) {
			Marker marker = new Marker(x, y, -1);
			handler.addObject(marker);
			c = 0;
		}

		x += velX;
		y += velY;

		collisionCheck();
		c++;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);

		g.drawOval((int) x, (int) y,2,2);
		g.setColor(Color.RED);
		g.drawLine((int) x, (int) y, (int) (x + (dirX * 500)), (int) (y + (dirY * 500)));
	}

	private float getXById(int id) {
		for(GameObject o: handler.getObjects()) {
			if(o.getID() == id) {
				return o.getX();
			}
		}
		return -1f;
	}

	private float getYById(int id) {
		for(GameObject o: handler.getObjects()) {
			if(o.getID() == id) {
				return o.getY();
			}
		}
		return -1f;
	}

	private void collisionCheck() {
		if((x == getXById(target)) && (y == getYById(target))) main.running = false;
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
}
