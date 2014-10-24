package view;

import com.bulletphysics.linearmath.Transform;
import com.sun.istack.internal.Nullable;
import model.Body;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import util.DisplayList;
import util.Program;

import java.nio.FloatBuffer;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

/**
 * Класс представления. Представление умеет визуализировать симуляцию.
 *
 * @author Mike Sorokin
 */
public class View {
    private boolean stopRequested = false;
    private Camera camera;
    private Program program = null;

    /**
     * Создает и инициализирует представление.
     */
    public View(DisplayMode displayMode, boolean fullscreen, boolean vSync, boolean resizable, String title, Camera camera, String program) {
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
        this.camera = camera;
        camera.applyProjectionMatrix();
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
        glLight(GL_LIGHT0, GL_AMBIENT, asFloatBuffer(.3f, .3f, .3f, 1));
        glLight(GL_LIGHT0, GL_DIFFUSE, asFloatBuffer(1, 1, 1, 1));
        glLight(GL_LIGHT0, GL_SPECULAR, asFloatBuffer(10, 10, 10, 1));
        glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(100, 10, 10, 1));
        glMaterial(GL_FRONT, GL_AMBIENT, asFloatBuffer(1, 1, 1, 1));
        glMaterial(GL_FRONT, GL_DIFFUSE, asFloatBuffer(1, 1, 1, 1));
        glMaterial(GL_FRONT, GL_SPECULAR, asFloatBuffer(1, 1, 1, 1));
        glMaterialf(GL_FRONT, GL_SHININESS, 128);
        glDepthFunc(GL_LESS);
        if (program == null) {
            Program.useDefault();
        } else {
            this.program = new Program(program);
            this.program.use();
        }
    }

    private FloatBuffer asFloatBuffer(float... floats) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(floats.length);
        buffer.put(floats);
        buffer.flip();
        return buffer;
    }

    /**
     * Удаляет объекты, используемые представлением, причем после этого оно будет непригодно к исползованию.
     * Обязательно вызывать перед завершением работы с этим представлением.
     */
    public void delete() {
        if (program != null)
            program.delete();
        Display.destroy();
    }

    /**
     * Визуализирует симуляцию.
     *
     * @param bodies тела в симуляции (возвращаются методом {@link model.Model#getBodies()})
     */
    public void show(List<Body> bodies) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glLoadIdentity();
        camera.applyViewMatrix();
        bodies.stream().forEach(body -> {
            glPushMatrix();
            float[] mat = new float[16];
            body.getRigidBody().getMotionState().getWorldTransform(new Transform()).getOpenGLMatrix(mat);
            FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
            buffer.put(mat);
            buffer.flip();
            glMultMatrix(buffer);
            body.getList().call();
            glPopMatrix();
        });
        Display.update();
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Display.isCloseRequested()) {
            stopRequested = true;
        }
    }

    public boolean isStopRequested() {
        return stopRequested;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setStopRequested(boolean stopRequested) {
        this.stopRequested = stopRequested;
    }
}
