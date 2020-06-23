package core.routines;

// load json
// tick each dot

import core.main.Handler;
import core.main.Object;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SquareRoutine {

	private JSONParser jsonParser;
	private Object o1, o2, o3, o4;

	private Object[] dots;

	private Handler handler;


	public SquareRoutine(Handler handler) {
		this.handler = handler;
		dots = new Object[4];
		o1 = new Object(handler);
		o2 = new Object(handler);
		o3 = new Object(handler);
		o4 = new Object(handler);
		dots[0] = o1;
		dots[1] = o2;
		dots[2] = o3;
		dots[3] = o4;
		jsonParser = new JSONParser();
		loadDots();

		for(Object o: dots) {
			handler.addObject(o);
		}
	}

	public void loadDots() {
		try(FileReader reader = new FileReader("E:\\programming\\IntelleJ_Projects\\Pursuit-Curves\\src\\modules\\assets\\routines\\square-routine.json")) {

			//java.lang.Object obj = jsonParser.parse(reader);
			JSONArray array = (JSONArray) jsonParser.parse(reader);

			for(int i = 0; i < array.size(); i++) {
				JSONObject dot = (JSONObject) array.get(i);
				dots[i].setX(Float.parseFloat((String) dot.get("x")));
				dots[i].setY(Float.parseFloat((String) dot.get("y")));
				dots[i].setID(Integer.parseInt((String) dot.get("id")));
			}

			//JSONArray dots = (JSONArray) obj;

			//dots.forEach(dot -> assignValues(dots));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void tick() {

	}

}
