package core.routines;

// load squareroutine json
// draw square
// tick each dot

import java.awt.*;

import core.main.Object;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SquareRoutine {

	Object o1, o2, o3, o4;

	JSONParser jsonParser = new JSONParser();


	public SquareRoutine() {
		loadDots();
	}

	public void loadDots() {

	}

	public void drawSquare(Graphics g) {
		g.setColor(Color.WHITE);

		g.drawLine(500, 312, 1000, 312);
		g.drawLine(1000, 312, 1000, 812);
		g.drawLine(500, 812, 1000, 812);
		g.drawLine(500, 312, 500, 812);
	}

	public void tick() {

	}

}
