package core.main;

import java.awt.*;

public class Marker extends GameObject {

	public Marker(float x, float y, int id) {
		super(x, y, id);
	}

	@Override
	public void tick() {
		x += 0;
		y += 0;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);

		g.drawOval((int) x, (int) y,2,2);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
}
