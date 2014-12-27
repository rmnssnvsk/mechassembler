package view.builder;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import util.Configs;
import view.Camera;

import javax.vecmath.Vector3f;

import static java.lang.Math.*;

/**
 * Created on 10/22/14.
 *
 * @author Mike Sorokin
 */
public class CameraBuilder {
    private Vector3f pos = new Vector3f(0, 0, 0), rot = new Vector3f(0, 0, 0);
    private float fov = 60,
            aspect = Display.getDesktopDisplayMode().getWidth() / (float) Display.getDesktopDisplayMode().getHeight(),
            zNear = 1 / 1000f, zFar = 1000;
    private boolean controllable = true;

    public CameraBuilder setPos(Vector3f pos) {
        this.pos = pos;
        return this;
    }

    public CameraBuilder setPosX(float x) {
        this.pos.x = x;
        return this;
    }

    public CameraBuilder setPosY(float y) {
        this.pos.y = y;
        return this;
    }

    public CameraBuilder setPosZ(float z) {
        this.pos.z = z;
        return this;
    }

    public CameraBuilder setRot(Vector3f rot) {
        this.rot = rot;
        return this;
    }

    public CameraBuilder setRotX(float x) {
        this.rot.x = x;
        return this;
    }

    public CameraBuilder setRotY(float y) {
        this.rot.y = y;
        return this;
    }

    public CameraBuilder setRotZ(float z) {
        this.rot.z = z;
        return this;
    }

    public CameraBuilder setFOV(float fov) {
        this.fov = fov;
        return this;
    }

    public CameraBuilder setAspect(float aspect) {
        this.aspect = aspect;
        return this;
    }

    public CameraBuilder autoAspect() {
        this.aspect = Display.getWidth() / (float) Display.getHeight();
        return this;
    }

    public CameraBuilder setZNear(float zNear) {
        this.zNear = zNear;
        return this;
    }

    public CameraBuilder setZFar(float zFar) {
        this.zFar = zFar;
        return this;
    }

    public CameraBuilder setControllable(boolean controllable) {
        this.controllable = controllable;
        return this;
    }

    public Camera build() {
        if (controllable) {
            return new Camera(pos, rot, fov, aspect, zNear, zFar) {
                @Override
                public void update(float t) {
                    float speed = Configs.getParamF("playerSpeed") * t;
                    float mouse = Configs.getParamF("mouseSensitivity") * t;
                    if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                        pos.x += sin(toRadians(rot.y)) * speed;
                        pos.z -= cos(toRadians(rot.y)) * speed;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                        pos.x -= sin(toRadians(rot.y)) * speed;
                        pos.z += cos(toRadians(rot.y)) * speed;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                        pos.z -= sin(toRadians(rot.y)) * speed;
                        pos.x -= cos(toRadians(rot.y)) * speed;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                        pos.z += sin(toRadians(rot.y)) * speed;
                        pos.x += cos(toRadians(rot.y)) * speed;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                        pos.y += speed;
                    }
                    if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
                        pos.y -= speed;
                    }
                    rot.y += Mouse.getDX() * mouse;
                    rot.x -= Mouse.getDY() * mouse;
                    if (rot.x > 90)
                        rot.x = 90;
                    if (rot.x < -90)
                        rot.x = -90;
                }
            };
        } else {
            return new Camera(pos, rot, fov, aspect, zNear, zFar) {
                @Override
                public void update(float t) {
                    // Nothing
                }
            };
        }
    }
}
