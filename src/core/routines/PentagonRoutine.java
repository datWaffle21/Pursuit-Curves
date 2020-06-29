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

public class PentagonRoutine {

    private JSONParser jsonParser;
    private Object o1, o2, o3, o4, o5;
    private Object[] dots;

    private Handler handler;

    private int numObjects;

    public void render(Graphics g) {
        drawPentagram(g);
    }

    public PentagonRoutine(Handler handler, Main main) {
        this.handler = handler;
        jsonParser = new JSONParser();

        try(FileReader reader = new FileReader(userHome + "/IdeaProjects/Pursuit-Curves/src/modules/assets/routines/pentagon-routine.json")) {
            JSONArray array = (JSONArray) jsonParser.parse(reader);

            numObjects = array.size();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


        dots = new Object[5];
        o1 = new Object(handler, main, numObjects);
        o2 = new Object(handler, main, numObjects);
        o3 = new Object(handler, main, numObjects);
        o4 = new Object(handler, main, numObjects);
        o5 = new Object(handler, main, numObjects);
        dots[0] = o1;
        dots[1] = o2;
        dots[2] = o3;
        dots[3] = o4;
        dots[4] = o5;

        loadDots();

        for(Object o : dots) {
            handler.addObject(o);
        }
    }

    private void drawPentagram(Graphics g) {
        g.drawLine(750, 312, 988, 485);
        g.drawLine(988, 485, 897, 764);
        g.drawLine(897, 764, 603, 764);
        g.drawLine(603, 764, 512, 485);
        g.drawLine(512, 485, 750, 312);
    }

    public void loadDots() {
        try(FileReader reader = new FileReader(userHome + "/IdeaProjects/Pursuit-Curves/src/modules/assets/routines/pentagon-routine.json")) {

            JSONArray array = (JSONArray) jsonParser.parse(reader);

            for(int i = 0; i < array.size(); i++) {
                JSONObject dot = (JSONObject) array.get(i);
                System.out.println("X: " + Float.parseFloat((String) dot.get("x")));
                System.out.println("Y: " + Float.parseFloat((String) dot.get("y")));
                System.out.println("ID: " + Float.parseFloat((String) dot.get("id")));
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



}
