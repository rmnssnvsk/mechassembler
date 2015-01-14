package main;

import controller.Controller;
import model.Level;
import model.Model;
import parser.LevelParser;
import util.ResourceLoader;
import view.Camera;
import view.View;
import view.builder.CameraBuilder;
import view.builder.ViewBuilder;

import javax.swing.*;
import javax.vecmath.Vector3f;
import java.io.File;
import java.util.Scanner;

/**
 * <p>
 * Главный класс. Создает модель, представление, контроллер, инициализирует и использует их.
 * </p><p>
 * Если исрользуются нативные библиотеки, то необходимо указать параметр запуска
 * <code>-Djava.library.path=lib/native</code>
 * </p>
 *
 * @author Mike Sorokin
 */

public class Main {

    public Main() {
        File lvlFile;
        while(true) {
            if ((lvlFile = new ResourceLoader("levels/" + JOptionPane.showInputDialog("Enter the name of the level!") + ".xml").getFile()) != null) {
                break;
            }
        }

        Camera camera = new CameraBuilder()
                .setPos(new Vector3f(0, 30, 30))
                .setRot(new Vector3f(45, 0, 0))
                .setAspect(1366 / 768f)
                .setControllable(true)
                .build();
        View view = new ViewBuilder()
                .setX(1366)
                .setY(768)
                .setMouseGrabbed(true)
                .setTitle("Mechassembler")
                .setCamera(camera)
                .setVertexShaderName("shaders/light.vert")
                .setFragmentShaderName("shaders/light.frag")
                .build();

        Level level = LevelParser.parse(lvlFile);
        Model model = new Model(camera, level);
        Controller controller = new Controller(model, view);
        model.addObserver(controller);
        view.addObserver(controller);

        model.start();
        model.delete();
        view.delete();
    }

    /**
     * Главный метод. Если исрользуются нативные библиотеки, то необходимо указать параметр запуска
     * <code>-Djava.library.path=lib/native</code>
     *
     * @param args Аргументы, с которыми была вызвана программа.
     */
    public static void main(String[] args) {
        new Main();
    }
}
