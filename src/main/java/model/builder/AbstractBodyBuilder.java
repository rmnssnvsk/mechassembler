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
public abstract class AbstractBodyBuilder {
    public final String id;
    protected float mass = 1;
    protected float restitution = .1f;
    protected float friction = 1;
    protected Vector3f pos = new Vector3f(0, 0, 0);
    protected Vector3f rot = new Vector3f(0, 0, 0);
    protected Vector3f impulse = new Vector3f(0, 0, 0);
    protected CollisionShape shape = new BoxShape(new Vector3f(1, 1, 1));
    protected Runnable list = () -> {};

    public AbstractBodyBuilder(String id) {
        this.id = id;
    }

    public float getMass() {
        return mass;
    }

    public AbstractBodyBuilder setMass(float mass) {
        this.mass = mass;
        return this;
    }

    public float getRestitution() {
        return restitution;
    }

    public AbstractBodyBuilder setRestitution(float restitution) {
        this.restitution = restitution;
        return this;
    }

    public float getFriction() {
        return friction;
    }

    public AbstractBodyBuilder setFriction(float friction) {
        this.friction = friction;
        return this;
    }

    public Vector3f getPos() {
        return pos;
    }

    public AbstractBodyBuilder setPos(Vector3f pos) {
        this.pos = pos;
        return this;
    }

    public float getPosX() {
        return pos.x;
    }

    public AbstractBodyBuilder setPosX(float x) {
        this.pos.x = x;
        return this;
    }

    public float getPosY() {
        return pos.y;
    }

    public AbstractBodyBuilder setPosY(float y) {
        this.pos.y = y;
        return this;
    }

    public float getPosZ() {
        return pos.z;
    }

    public AbstractBodyBuilder setPosZ(float z) {
        this.pos.z = z;
        return this;
    }

    public Vector3f getRot() {
        return rot;
    }

    public AbstractBodyBuilder setRot(Vector3f rot) {
        this.rot = rot;
        return this;
    }

    public float getRotX() {
        return rot.x;
    }

    public AbstractBodyBuilder setRotX(float x) {
        //FIXME
        this.rot.x = x;
        return this;
    }

    public float getRotY() {
        return rot.y;
    }

    public AbstractBodyBuilder setRotY(float y) {
        //FIXME
        this.rot.y = y;
        return this;
    }

    public float getRotZ() {
        return rot.z;
    }

    public AbstractBodyBuilder setRotZ(float z) {
        //FIXME
        this.rot.z = z;
        return this;
    }

    public Vector3f getImpulse() {
        return impulse;
    }

    public AbstractBodyBuilder setImpulse(Vector3f impulse) {
        this.impulse = impulse;
        return this;
    }

    public float getImpulseX() {
        return impulse.x;
    }

    public AbstractBodyBuilder setImpulseX(float x) {
        this.impulse.x = x;
        return this;
    }

    public float getImpulseY() {
        return impulse.y;
    }

    public AbstractBodyBuilder setImpulseY(float y) {
        this.impulse.y = y;
        return this;
    }

    public float getImpulseZ() {
        return impulse.z;
    }

    public AbstractBodyBuilder setImpulseZ(float z) {
        this.impulse.z = z;
        return this;
    }

    public CollisionShape getCollisionShape() {
        return shape;
    }

    public AbstractBodyBuilder setCollisionShape(CollisionShape shape) {
        this.shape = shape;
        return this;
    }

    public Runnable getDisplayList() {
        return list;
    }

    public AbstractBodyBuilder setDisplayList(Runnable list) {
        this.list = list;
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
        RigidBody rigidBody = new RigidBody(info);
        rigidBody.setUserPointer(this);
        rigidBody.applyCentralImpulse(impulse);
        return new Body(
                rigidBody,
                new DisplayList(list),
                id
        );
    }

    public abstract void change();
}
