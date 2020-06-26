package core.routines;

import core.main.Handler;
import core.main.Main;
import core.main.Object;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static core.utils.Constants.userHome;

public class SquareRoutine {

	private JSONParser jsonParser;
	private Object o1, o2, o3, o4;
	private Object[] dots;

	private Handler handler;

	private int numObjects;

	public SquareRoutine(Handler handler, Main main) {
		this.handler = handler;
		jsonParser = new JSONParser();

		try(FileReader reader = new FileReader(userHome + "/IdeaProjects/Pursuit-Curves/src/modules/assets/routines/square-routine.json")){

			JSONArray array = (JSONArray) jsonParser.parse(reader);

			numObjects = array.size();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		dots = new Object[4];
		o1 = new Object(handler, main, numObjects);
		o2 = new Object(handler, main, numObjects);
		o3 = new Object(handler, main, numObjects);
		o4 = new Object(handler, main, numObjects);
		dots[0] = o1;
		dots[1] = o2;
		dots[2] = o3;
		dots[3] = o4;
		loadDots();

		for(Object o: dots) {
			handler.addObject(o);
		}
	}

	public void render(Graphics g) {
		drawBox(g);
	}

	public void loadDots() {
		try(FileReader reader = new FileReader(userHome + "/IdeaProjects/Pursuit-Curves/src/modules/assets/routines/square-routine.json")) {

			JSONArray array = (JSONArray) jsonParser.parse(reader);

			for(int i = 0; i < array.size(); i++) {
				JSONObject dot = (JSONObject) array.get(i);
				dots[i].setX(Float.parseFloat((String) dot.get("x")));
				dots[i].setY(Float.parseFloat((String) dot.get("y")));
				dots[i].setID(Integer.parseInt((String) dot.get("id")));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void drawBox(Graphics g) {
		g.setColor(Color.WHITE);

		g.drawLine(500, 312, 1000, 312);
		g.drawLine(1000, 312, 1000, 812);
		g.drawLine(500, 812, 1000, 812);
		g.drawLine(500, 312, 500, 812);
	}
}
