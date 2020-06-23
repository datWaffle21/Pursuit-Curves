package core.main;

import core.routines.SquareRoutine;
import core.utils.Constants;
import core.utils.KeyInput;

import java.awt.*;
import java.awt.image.BufferStrategy;

/*
TODO:
    - In Object constructor, change target definition from "target = id == 3" to "target = id == numObjects" - This will ease the id assignment process for a variable amount of dots
    - Add menu to run pre-defined routines or create a menu to create a routine "in the program"... a create your own routine thingy
        - save routines as csv files or json (json looks better after 1 min of research)
    -
 */

public class Main extends Canvas implements Runnable {

    boolean running = false;
    public int showFrames = 0;

    private Thread thread;
    private KeyInput keyInput;
    private Handler handler;

    private SquareRoutine squareRoutine;

    int halfX = Constants.WIDTH / 2;
    int halfY = Constants.HEIGHT / 2;

    public Main() {
        handler = new Handler();

        keyInput = new KeyInput(handler);

        this.addKeyListener(keyInput);

        squareRoutine = new SquareRoutine(handler);

        new Window(Constants.WIDTH, Constants.HEIGHT, "Pursuit Curves", this);

    }

    synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        handler.tick();
        keyInput.tick();
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor((Color.BLACK));
        g.fillRect(0,0,Constants.WIDTH, Constants.HEIGHT);

        handler.render(g);
        drawBox(g);

        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }

            if (running) {
                render();
                frames++;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                showFrames = frames;
                frames = 0;
            }
        }
        stop();
    }

    public static float clamp(float var, float min, float max) {
        if (var >= max) {
            return var = max;
        } else if (var <= min) {
            return var = min;
        } else {
            return var;
        }
    }

    public void drawBox(Graphics g) {
        g.setColor(Color.WHITE);

        g.drawLine(halfX - 250, halfY - 250, halfX + 250, halfY - 250);
        g.drawLine(halfX + 250, halfY - 250, halfX + 250, halfY + 250);
        g.drawLine(halfX - 250, halfY + 250, halfX + 250, halfY + 250);
        g.drawLine(halfX - 250, halfY - 250, halfX - 250, halfY + 250);
    }


    public static void main(String[] args) {
        new Main();
    }

}
