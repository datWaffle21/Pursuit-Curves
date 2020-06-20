package core.main;

import core.utils.Constants;

import java.awt.*;

public class Object extends GameObject {

	private int target; // id of the pursuee

	private Handler handler;
	private Main main;

	public Object(float x, float y, int id, Handler handler, Main main) {
		super(x, y, id);
		this.handler = handler;
		this.main = main;
		target = id == 3 ? 0 : id + 1;
	}

	@Override
	public void tick() {
		// System.out.println("ID: " + id + " TARGET: " + target);
		float x2 = getXById(target);
		float y2 = getYById(target);
		float mag = (float) Math.sqrt(Math.pow((x2-x),2) + Math.pow(y2-y,2));

		float dirX = (x2-x) / mag;
		float dirY = (y2-y) / mag;

		velX = dirX * Constants.SPEED;
		velY = dirY * Constants.SPEED;

		Marker marker = new Marker(x, y, -1);
		handler.addObject(marker);

		x += velX;
		y += velY;

		collisionCheck();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);

		g.drawOval((int) x, (int) y,2,2);
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

	public int getId() {
		return this.id;
	}
}
