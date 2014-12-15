package main;

import com.bulletphysics.collision.shapes.*;
import controller.Controller;
import model.Body;
import model.Level;
import model.Material;
import model.Model;
import model.builder.BodyBuilder;
import model.builder.BoxBodyBuilder;
import model.builder.MaterialBuilder;
import model.builder.SphereBodyBuilder;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import util.OBJModelLoader;
import util.TextureLoader;
import view.Camera;
import view.View;
import view.builder.CameraBuilder;
import view.builder.ViewBuilder;

import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import java.io.IOException;
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
                .setPos(new Vector3f(0, 30, 30))
                .setRot(new Vector3f(45, 0, 0))
                .setAspect(1366 / 768f)
                .setControllable(true)
                .build();
        View view = new ViewBuilder()
                .setX(1366)
                .setY(768)
                .setFullscreen(true)
                .setMouseGrabbed(true)
                .setTitle("Mechassembler")
                .setCamera(camera)
                .setProgram("light")
                .build();
        Texture ballTexture = TextureLoader.load("basketball");
        Material ballMaterial = new MaterialBuilder()
                .setSpecular(new Vector4f(.01f, .01f, .01f, 1))
                .build();
        Material plainMaterial = new MaterialBuilder()
                .setSpecular(new Vector4f(.05f, .05f, .05f, 1))
                .build();
        BodyBuilder ball = new SphereBodyBuilder()
                .setMass(.5f)
                .setRestitution(.5f)
                .setFriction(.7f)
                .setPosY(10)
                .setPosX(-20)
                .setRadius(1.5f)
                .setTexture(ballTexture)
                .setMaterial(ballMaterial)
                .setImpulse(new Vector3f(4, 4, 0));
        BodyBuilder box = new BoxBodyBuilder()
                .setMass(0)
                .setRestitution(10)
                .setSize(new Vector3f(3, .1f, 1))
                .setColor(new Vector3f(1, 0, 0))
                .setPosY(10)
                .setPosX(-10);
        BodyBuilder plane = new BodyBuilder()
                .setMass(0)
                .setRestitution(.75f)
                .setFriction(.7f)
                .setCollisionShape(new StaticPlaneShape(new Vector3f(0, 1, 0), 0))
                .setDisplayList(() -> {
                    plainMaterial.apply();
                    GL11.glBindTexture(GL11.GL_TEXTURE_2D, TextureLoader.NO_TEXTURE.getTextureID());
                    GL11.glColor3f(.7f, 1, .7f);
                    GL11.glBegin(GL11.GL_QUADS);
                    GL11.glNormal3f(0, 1, 0);
                    GL11.glVertex3f(-50, 0, -50);
                    GL11.glVertex3f(50, 0, -50);
                    GL11.glVertex3f(50, 0, 50);
                    GL11.glVertex3f(-50, 0, 50);
                    GL11.glEnd();
                });

        Level level = new Level();
        level.addBody(ball);
        level.addBody(plane);
        level.addBody(box);
        try {
            BodyBuilder body = OBJModelLoader.load("bucket");
            level.addBody(body);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Model model = new Model(camera, level);

        Controller controller = new Controller(model, view);
        model.addObserver(controller);
        view.addObserver(controller);

        model.start();
        model.delete();
        view.delete();
    }
}
