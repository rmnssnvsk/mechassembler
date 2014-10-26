package view;

import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.linearmath.Transform;
import model.Body;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL20;
import util.Program;
import view.event.ViewEvent;
import view.event.CloseRequestedViewEvent;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL20.*;

/**
 * Класс представления. Представление умеет визуализировать симуляцию.
 *
 * @author Mike Sorokin
 */
public class View extends Observable {
    private Camera camera;
    private Program program = null;

    public View(DisplayMode displayMode, boolean fullscreen, boolean vSync, boolean resizable, boolean mouseGrabbed, String title, Camera camera, String program) {
        try {
            if (!fullscreen)
                Display.setDisplayMode(displayMode);
            Display.setFullscreen(fullscreen);
            Display.setVSyncEnabled(vSync);
            Display.setResizable(resizable);
            Mouse.setGrabbed(mouseGrabbed);
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
        glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(0, 0, 60, 1));
        glMaterial(GL_FRONT, GL_AMBIENT, asFloatBuffer(1, 1, 1, 1));
        glMaterial(GL_FRONT, GL_DIFFUSE, asFloatBuffer(1, 1, 1, 1));
        glMaterial(GL_FRONT, GL_SPECULAR, asFloatBuffer(1, 1, 1, 1));
        glMaterialf(GL_FRONT, GL_SHININESS, 128);
        glDepthFunc(GL_LESS);
        glShadeModel(GL_SMOOTH);
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
     * Удаляет объекты, используемые представлением, причем после этого оно будет непригодно к использованию.
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
        FloatBuffer viewMatrixReadBuffer = BufferUtils.createFloatBuffer(16);
        glGetFloat(GL_MODELVIEW_MATRIX, viewMatrixReadBuffer);
        float[] viewMatrix = new float[16];
        viewMatrixReadBuffer.get(viewMatrix);
        FloatBuffer viewMatrixWriteBuffer = BufferUtils.createFloatBuffer(16);
        viewMatrixWriteBuffer.put(viewMatrix);
        viewMatrixWriteBuffer.flip();
        glUniformMatrix4(glGetUniformLocation(program.program, "viewMatrix"), false, viewMatrixWriteBuffer);
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
        List<ViewEvent> events = new ArrayList<>();
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Display.isCloseRequested()) {
            events.add(new CloseRequestedViewEvent(this));
        }
        setChanged();
        notifyObservers(events);
    }

    public Camera getCamera() {
        return camera;
    }
}
