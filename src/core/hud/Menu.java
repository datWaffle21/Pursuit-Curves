package core.hud;

import core.main.Handler;
import core.main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

public class Menu extends MouseAdapter {

    private Main main;
    private Handler handler;

    private int mx, my;

    public Menu(Main main, Handler handler) {
        this.main = main;
        this.handler = handler;

    }

    public void tick() {
        Point cursor = MouseInfo.getPointerInfo().getLocation();
        SwingUtilities.convertPointFromScreen(cursor, main);
        mx = cursor.x;
        my = cursor.y;
    }

    public void render(Graphics g) {

        Font big = new Font("ariel", Font.BOLD, 75);
        Font med = new Font("ariel", Font.BOLD, 30);
        Font sml = new Font("ariel", Font.BOLD, 20);

        if(main.state == Main.STATE.Menu) {
            g.setFont(big);
            g.setColor(Color.WHITE);
            g.drawString("Pursuit Curves", 300, 135);
        }
    }
}
