package main;

import controller.Controller;
import model.GoalListener;
import model.Level;
import model.Model;
import parser.LevelParser;
import util.ResourceLoader;
import view.Camera;
import view.View;
import view.builder.CameraBuilder;
import view.builder.ViewBuilder;

import javax.vecmath.Vector3f;

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

        Level level = LevelParser.parse(new ResourceLoader("levels/level1.lvl").getFile());

        Model model = new Model(camera, level);

        Controller controller = new Controller(model, view);
        GoalListener listener = new GoalListener("ball", "bucket", 2, 3);
        model.addObserver(controller);
        model.addObserver(listener);
        view.addObserver(controller);
        listener.addObserver(controller);

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
