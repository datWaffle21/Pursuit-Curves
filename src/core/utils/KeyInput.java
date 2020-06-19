package core.utils;

import core.main.Handler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    public void tick() {

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // add code here
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        // add code here
    }

}
