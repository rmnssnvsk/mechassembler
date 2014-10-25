package main;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import controller.Controller;
import model.Body;
import model.Model;
import model.builder.BoxBodyBuilder;
import model.builder.SphereBodyBuilder;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import model.builder.BodyBuilder;
import util.Program;
import view.Camera;
import view.builder.CameraBuilder;
import view.builder.ViewBuilder;
import view.View;

import javax.vecmath.Vector3f;

/**
 * <p>
 *     Главный класс. Создает модель, представление, контроллер, инициализирует и использует их.
 * </p><p>
 *     Если исрользуются нативные библиотеки, то необходимо указать параметр запуска
 *     <code>-Djava.library.path=lib/native</code>
 * </p>
 *
 *
 * @author Mike Sorokin
 */

public class Main {

    /**
     * Главный метод. Если исрользуются нативные библиотеки, то необходимо указать параметр запуска
     * <code>-Djava.library.path=lib/native</code>
     * @param args Аргументы, с которыми была вызвана программа.
     */
    public static void main(String[] args) {
        Camera camera = new CameraBuilder()
                .setPos(new Vector3f(0, -30, -30))
                .setRot(new Vector3f(60, 90, 0))
                .setAspect(1366 / 768f)
                .setControllable(true)
                .build();
        Model model = new Model(camera);
        View view = new ViewBuilder()
                .setX(1366)
                .setY(768)
                .setFullscreen(true)
                .setMouseGrabbed(true)
                .setTitle("Mechassembler")
                .setCamera(camera)
                .setProgram("light")
                .build();
        Controller controller = new Controller(model, view);
        Body ball = new SphereBodyBuilder()
                .setMass(1)
                .setRestitution(.5f)
                .setFriction(.7f)
                .setPosY(10)
                .setRadius(3)
                .setColor(new Vector3f(1, 0, 0))
                .build();
        Body ball2 = new SphereBodyBuilder()
                .setMass(1)
                .setRestitution(.5f)
                .setFriction(.7f)
                .setPosX(5)
                .setPosY(20)
                .setPosZ(1)
                .setRadius(3)
                .setColor(new Vector3f(0, 1, 0))
                .build();
        Body plane = new BodyBuilder()
                .setMass(0)
                .setRestitution(.75f)
                .setFriction(.7f)
                .setCollisionShape(new StaticPlaneShape(new Vector3f(0, 1, 0), 0))
                .setDisplayList(() -> {
                    GL11.glBegin(GL11.GL_QUADS);
                    GL11.glColor3f(.7f, .7f, .7f);
                    GL11.glNormal3f(0, 1, 0);
                    GL11.glVertex3f(-50, 0, -50);
                    GL11.glVertex3f(50, 0, -50);
                    GL11.glVertex3f(50, 0, 50);
                    GL11.glVertex3f(-50, 0, 50);
                    GL11.glEnd();
                })
                .build();
        Body box = new BoxBodyBuilder()
                .setMass(1)
                .setFriction(1)
                .setRestitution(.1f)
                .setPosX(5)
                .setPosY(2)
                .setSize(new Vector3f(5, 2, 1))
                .setColor(new Vector3f(0, 0, 1))
                .build();
        controller.addBody(ball);
        controller.addBody(ball2);
        controller.addBody(box);
        controller.addBody(plane);
        controller.startSimulation();
        controller.delete();
    }
}
