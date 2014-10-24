package model.builder;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;
import model.Body;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Quadric;
import org.lwjgl.util.glu.Sphere;
import util.DisplayList;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created on 10/22/14.
 *
 * @author Mike Sorokin
 */
public class BoxBodyBuilder {
    private float mass = 1;
    private float restitution = .1f;
    private float friction = .1f;
    private Vector3f size = new Vector3f(1, 1, 1);
    private Vector3f pos = new Vector3f(0, 0, 0);
    private Vector3f rot = new Vector3f(0, 0, 0);
    private Vector3f color = new Vector3f(.5f, .5f, .5f);

    public BoxBodyBuilder setMass(float mass) {
        this.mass = mass;
        return this;
    }

    public BoxBodyBuilder setRestitution(float restitution) {
        this.restitution = restitution;
        return this;
    }

    public BoxBodyBuilder setFriction(float friction) {
        this.friction = friction;
        return this;
    }

    public BoxBodyBuilder setSize(Vector3f size) {
        this.size = size;
        return this;
    }

    public BoxBodyBuilder setSizeX(float x) {
        this.size.x = x;
        return this;
    }

    public BoxBodyBuilder setSizeY(float y) {
        this.size.y = y;
        return this;
    }

    public BoxBodyBuilder setSizeZ(float z) {
        this.size.z = z;
        return this;
    }

    public BoxBodyBuilder setPos(Vector3f pos) {
        this.pos = pos;
        return this;
    }

    public BoxBodyBuilder setPosX(float x) {
        this.pos.x = x;
        return this;
    }

    public BoxBodyBuilder setPosY(float y) {
        this.pos.y = y;
        return this;
    }

    public BoxBodyBuilder setPosZ(float z) {
        this.pos.z = z;
        return this;
    }

    public BoxBodyBuilder setRot(Vector3f rot) {
        this.rot = rot;
        return this;
    }

    public BoxBodyBuilder setRotX(float x) {
        //FIXME
        this.rot.x = x;
        return this;
    }

    public BoxBodyBuilder setRotY(float y) {
        //FIXME
        this.rot.y = y;
        return this;
    }

    public BoxBodyBuilder setRotZ(float z) {
        //FIXME
        this.rot.z = z;
        return this;
    }


    public BoxBodyBuilder setColor(Vector3f color) {
        this.color = color;
        return this;
    }

    public BoxBodyBuilder setColorR(float r) {
        this.color.x = r;
        return this;
    }

    public BoxBodyBuilder setColorG(float g) {
        this.color.y = g;
        return this;
    }

    public BoxBodyBuilder setColorZ(float b) {
        this.color.z = b;
        return this;
    }

    public Body build() {
        Vector3f inertia = new Vector3f(0, 0, 0);
        CollisionShape shape = new BoxShape(size);
        DisplayList list = new DisplayList(() -> {
            GL11.glBegin(GL11.GL_QUADS);
            GL11.glColor3f(color.x, color.y, color.z);
            GL11.glNormal3f(0, 0, -1);
            GL11.glVertex3f(-size.x, -size.y, -size.z);
            GL11.glVertex3f(-size.x, +size.y, -size.z);
            GL11.glVertex3f(+size.x, +size.y, -size.z);
            GL11.glVertex3f(+size.x, -size.y, -size.z);
            GL11.glNormal3f(0, 0, +1);
            GL11.glVertex3f(-size.x, -size.y, +size.z);
            GL11.glVertex3f(-size.x, +size.y, +size.z);
            GL11.glVertex3f(+size.x, +size.y, +size.z);
            GL11.glVertex3f(+size.x, -size.y, +size.z);
            GL11.glNormal3f(0, -1, 0);
            GL11.glVertex3f(-size.x, -size.y, -size.z);
            GL11.glVertex3f(-size.x, -size.y, +size.z);
            GL11.glVertex3f(+size.x, -size.y, +size.z);
            GL11.glVertex3f(+size.x, -size.y, -size.z);
            GL11.glNormal3f(0, +1, 0);
            GL11.glVertex3f(-size.x, +size.y, -size.z);
            GL11.glVertex3f(-size.x, +size.y, +size.z);
            GL11.glVertex3f(+size.x, +size.y, +size.z);
            GL11.glVertex3f(+size.x, +size.y, -size.z);
            GL11.glNormal3f(-1, 0, 0);
            GL11.glVertex3f(-size.x, -size.y, -size.z);
            GL11.glVertex3f(-size.x, -size.y, +size.z);
            GL11.glVertex3f(-size.x, +size.y, +size.z);
            GL11.glVertex3f(-size.x, +size.y, -size.z);
            GL11.glNormal3f(+1, 0, 0);
            GL11.glVertex3f(+size.x, -size.y, -size.z);
            GL11.glVertex3f(+size.x, -size.y, +size.z);
            GL11.glVertex3f(+size.x, +size.y, +size.z);
            GL11.glVertex3f(+size.x, +size.y, -size.z);
            GL11.glEnd();
        });
        shape.calculateLocalInertia(mass, inertia);
        RigidBodyConstructionInfo info = new RigidBodyConstructionInfo(
                mass,
                new DefaultMotionState(new Transform(new Matrix4f(
                        new Quat4f(rot.x, rot.y, rot.z, 1),
                        pos,
                        1
                ))),
                shape,
                inertia
        );
        info.restitution = restitution;
        info.friction = friction;
        return new Body(
                new RigidBody(info),
                list
        );
    }
}
