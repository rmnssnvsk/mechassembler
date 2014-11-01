package view;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;

import javax.vecmath.Vector3f;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 10/21/14.
 *
 * @author Mike Sorokin
 */
public abstract class Camera {
    private Vector3f pos, rot;
    private float fov, aspect, zNear, zFar;

    public Camera(Vector3f pos, Vector3f rot, float fov, float aspect, float zNear, float zFar) {
        this.pos = pos;
        this.rot = rot;
        this.fov = fov;
        this.aspect = aspect;
        this.zNear = zNear;
        this.zFar = zFar;
    }

    public void applyProjectionMatrix() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(fov, aspect, zNear, zFar);
        glMatrixMode(GL_MODELVIEW);
    }
    
    public void applyViewMatrix() {
        glRotatef(rot.x, 1, 0, 0);
        glRotatef(rot.y, 0, 1, 0);
        glRotatef(rot.z, 0, 0, 1);
        glTranslatef(-pos.x, -pos.y, -pos.z);
    }

    public abstract void update(float t);
}
