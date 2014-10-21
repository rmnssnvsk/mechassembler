package main;

import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.collision.shapes.StridingMeshInterface;
import com.bulletphysics.dynamics.RigidBody;
import controller.Controller;
import model.Body;
import model.Model;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import util.DisplayList;
import util.builder.BodyBuilder;
import util.builder.ViewBuilder;
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
        Model model = new Model();
        View view = new ViewBuilder()
                .setX(1366)
                .setY(768)
                .setFullscreen(true)
                .setTitle("Mechassembler")
                .build();
        Controller controller = new Controller(model, view);
        Body ball = new BodyBuilder()
                .setMass(1)
                .setRestitution(.5f)
                .setFriction(.7f)
                .setPosY(10)
                .setCollisionShape(new SphereShape(3))
                .setDisplayList(() -> {
                    GL11.glColor3f(0, 1, 0);
                    Sphere sphere = new Sphere();
                    sphere.setDrawStyle(GLU.GLU_SILHOUETTE);
                    sphere.draw(3, 20, 20);
                })
                .build();
        Body ball2 = new BodyBuilder()
                .setMass(1)
                .setRestitution(.5f)
                .setFriction(.7f)
                .setPosY(20)
                .setPosX(5)
                .setPosZ(1)
                .setCollisionShape(new SphereShape(3))
                .setDisplayList(() -> {
                    GL11.glColor3f(1, 0, 0);
                    Sphere sphere = new Sphere();
                    sphere.setDrawStyle(GLU.GLU_SILHOUETTE);
                    sphere.draw(3, 20, 20);
                })
                .build();
        Vector3f nml = new Vector3f(3, 1, 0);
        nml.normalize();
        Body plane = new BodyBuilder()
                .setMass(0)
                .setRestitution(.75f)
                .setFriction(.7f)
                .setCollisionShape(new StaticPlaneShape(nml, 0))
                .setDisplayList(() -> {
                    GL11.glBegin(GL11.GL_QUADS);
                    GL11.glColor3f(.5f, .5f, .5f);
                    GL11.glVertex3f(-50, 50, -50);
                    GL11.glColor3f(.6f, .6f, .6f);
                    GL11.glVertex3f(50, -50, -50);
                    GL11.glColor3f(.7f, .7f, .7f);
                    GL11.glVertex3f(50, -50, 50);
                    GL11.glColor3f(.8f, .8f, .8f);
                    GL11.glVertex3f(-50, 50, 50);
                    GL11.glEnd();
                })
                .build();
        controller.addBody(ball);
        controller.addBody(ball2);
        controller.addBody(plane);
        controller.startSimulation();
        controller.delete();
    }
}
