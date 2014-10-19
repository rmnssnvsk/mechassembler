package main;

import controller.Controller;
import view.View;

/**
 * Created on 10/18/14.
 *
 * @author Mike Sorokin
 */

public class Main {
    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        controller.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        controller.stop();
    }
}
