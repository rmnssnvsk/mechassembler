package view;

import com.bulletphysics.linearmath.Transform;
import javafx.scene.transform.MatrixType;
import model.Body;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.io.BufferedReader;
import java.nio.FloatBuffer;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * Класс представления. Представление умеет визуализировать симуляцию.
 *
 * @author Mike Sorokin
 */
public class View {
    public boolean stopRequested = false;

    /**
     * Создает и инициализирует представление.
     */
    public View(DisplayMode displayMode, boolean fullscreen, boolean vSync, boolean resizable, String title) {
        try {
            if (!fullscreen)
                Display.setDisplayMode(displayMode);
            Display.setFullscreen(fullscreen);
            Display.setVSyncEnabled(vSync);
            Display.setResizable(resizable);
            Display.setTitle(title);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(60, Display.getWidth() / (float) Display.getHeight(), .0001f, 1000);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        glTranslatef(0, -5, -50);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
    }

    /**
     * Удаляет объекты, используемые представлением, причем после этого оно будет непригодно к исползованию.
     * Обязательно вызывать перед завершением работы с этим представлением.
     */
    public void delete() {
        Display.destroy();
    }

    /**
     * Визуализирует симуляцию.
     * @param bodies тела в сим уляции (возвращаются методом {@link model.Model#getBodies()})
     */
    public void show(List<Body> bodies) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        bodies.stream().forEach(body -> {
            glPushMatrix();
            float[] mat = new float[16];
            body.getRigidBody().getMotionState().getWorldTransform(new Transform()).getOpenGLMatrix(mat);
            FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
            buffer.put(mat);
            buffer.flip();
            glMultMatrix(buffer);
            body.getList().call();
            glEnd();
            glPopMatrix();
        });
        Display.update();
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Display.isCloseRequested()) {
            stopRequested = true;
        }
    }

    public void setStopRequested(boolean stopRequested) {
        this.stopRequested = stopRequested;
    }

    public boolean isStopRequested() {
        return stopRequested;
    }
}
