package main;

import com.bulletphysics.collision.shapes.StaticPlaneShape;
import controller.Controller;
import model.Body;
import model.Material;
import model.Model;
import model.builder.BodyBuilder;
import model.builder.BoxBodyBuilder;
import model.builder.MaterialBuilder;
import model.builder.SphereBodyBuilder;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import util.TextureLoader;
import view.Camera;
import view.View;
import view.builder.CameraBuilder;
import view.builder.ViewBuilder;

import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

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
        new Main();
    }

    public Main() {
        Camera camera = new CameraBuilder()
                .setPos(new Vector3f(0, 30, 60))
                .setRot(new Vector3f(30, 1, 0))
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
                .setDrawAxes(true)
                .build();
        Controller controller = new Controller(model, view);
        model.addObserver(controller);
        view.addObserver(controller);
        Texture ballTexture = TextureLoader.load("basketball");
        Texture boxTexture = TextureLoader.load("brick");
        Material plainMaterial = new MaterialBuilder()
                .setSpecular(new Vector4f(.05f, .05f, .05f, 1))
                .build();
        Body ball = new SphereBodyBuilder()
                .setRestitution(.5f)
                .setFriction(.7f)
                .setPosY(10)
                .setRadius(3)
                .setTexture(ballTexture)
                .build();
        Body ball2 = new SphereBodyBuilder()
                .setRestitution(.5f)
                .setFriction(.7f)
                .setPosX(5)
                .setPosY(20)
                .setPosZ(1)
                .setRadius(3)
                .setTexture(ballTexture)
                .build();
        Body plane = new BodyBuilder()
                .setMass(0)
                .setRestitution(.75f)
                .setFriction(.7f)
                .setCollisionShape(new StaticPlaneShape(new Vector3f(0, 1, 0), 0))
                .setDisplayList(() -> {
                    plainMaterial.apply();
                    GL11.glBindTexture(GL11.GL_TEXTURE_2D, TextureLoader.NO_TEXTURE.getTextureID());
                    GL11.glColor3f(1, 1, 1);
                    GL11.glBegin(GL11.GL_QUADS);
                    GL11.glNormal3f(0, 1, 0);
                    GL11.glVertex3f(-50, 0, -50);
                    GL11.glVertex3f(50, 0, -50);
                    GL11.glVertex3f(50, 0, 50);
                    GL11.glVertex3f(-50, 0, 50);
                    GL11.glEnd();
                })
                .build();
        Body box = new BoxBodyBuilder()
                .setFriction(1)
                .setRestitution(.1f)
                .setPosX(5)
                .setPosY(3)
                .setSize(new Vector3f(5, 2, 1))
                .setTexture(boxTexture)
                .setMaterial(plainMaterial)
                .build();
        model.addBody(ball);
        model.addBody(ball2);
        model.addBody(plane);
        model.addBody(box);
        model.start();
    }
}
