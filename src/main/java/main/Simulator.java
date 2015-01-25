package main;

import controller.Controller;
import model.Level;
import model.Model;
import util.parser.LevelParser;
import view.Camera;
import view.View;
import view.builder.CameraBuilder;
import view.builder.ViewBuilder;

import javax.vecmath.Vector3f;
import java.io.File;

/**
 * Created by roman-sosnovsky on 20.01.15.
 */
public class Simulator {
    File file;

    public Simulator(File file) {
        this.file = file;
    }

    public void start() {
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

        Level level = new LevelParser().parse(file);
        Model model = new Model(camera, level);
        Controller controller = new Controller(model, view);
        model.addObserver(controller);
        view.addObserver(controller);

        model.start();

        model.delete();
        view.delete();

        System.gc();
    }
}
