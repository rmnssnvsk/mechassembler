package model.builder;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;
import model.Body;
import util.DisplayList;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created on 10/21/14.
 *
 * @author Mike Sorokin
 */
public class BodyBuilder {
    private float mass = 1;
    private float restitution = .1f;
    private float friction = 1;
    private Vector3f pos = new Vector3f(0, 0, 0);
    private Vector3f rot = new Vector3f(0, 0, 0);
    private CollisionShape shape = new BoxShape(new Vector3f(1, 1, 1));
    private DisplayList list = new DisplayList(() -> {});

    public BodyBuilder setMass(float mass) {
        this.mass = mass;
        return this;
    }

    public BodyBuilder setRestitution(float restitution) {
        this.restitution = restitution;
        return this;
    }

    public BodyBuilder setFriction(float friction) {
        this.friction = friction;
        return this;
    }

    public BodyBuilder setPos(Vector3f pos) {
        this.pos = pos;
        return this;
    }

    public BodyBuilder setPosX(float x) {
        this.pos.x = x;
        return this;
    }

    public BodyBuilder setPosY(float y) {
        this.pos.y = y;
        return this;
    }

    public BodyBuilder setPosZ(float z) {
        this.pos.z = z;
        return this;
    }

    public BodyBuilder setRot(Vector3f rot) {
        this.rot = rot;
        return this;
    }

    public BodyBuilder setRotX(float x) {
        //FIXME
        this.rot.x = x;
        return this;
    }

    public BodyBuilder setRotY(float y) {
        //FIXME
        this.rot.y = y;
        return this;
    }

    public BodyBuilder setRotZ(float z) {
        //FIXME
        this.rot.z = z;
        return this;
    }

    public BodyBuilder setCollisionShape(CollisionShape shape) {
        this.shape = shape;
        return this;
    }

    public BodyBuilder setDisplayList(Runnable list) {
        this.list = new DisplayList(list);
        return this;
    }

    public Body build() {
        Vector3f inertia = new Vector3f(0, 0, 0);
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
