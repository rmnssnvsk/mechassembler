package model.builder;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;
import formsdialogs.setparamsofobjectdialog.SetParamsOfObjectDialog;
import model.Body;
import util.DisplayList;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.LinkedList;

/**
 * Created on 12/16/14.
 *
 * @author Mike Sorokin
 */
public class DefaultBodyBuilder extends AbstractBodyBuilder {
    protected float mass = 1;
    protected float restitution = .1f;
    protected float friction = 1;
    protected Vector3f pos = new Vector3f(0, 0, 0);
    protected Vector3f rot = new Vector3f(0, 0, 0);
    protected Vector3f impulse = new Vector3f(0, 0, 0);
    protected CollisionShape shape = new BoxShape(new Vector3f(1, 1, 1));
    protected DisplayList list = new DisplayList(() -> {
    });
    protected LinkedList<Param> changeableParams = new LinkedList<>();

    public DefaultBodyBuilder(String id) {
        super(id);
    }

    public float getMass() {
        return mass;
    }

    public DefaultBodyBuilder setMass(float mass) {
        this.mass = mass;
        return this;
    }

    public float getRestitution() {
        return restitution;
    }

    public DefaultBodyBuilder setRestitution(float restitution) {
        this.restitution = restitution;
        return this;
    }

    public float getFriction() {
        return friction;
    }

    public DefaultBodyBuilder setFriction(float friction) {
        this.friction = friction;
        return this;
    }

    public Vector3f getPos() {
        return pos;
    }

    public DefaultBodyBuilder setPos(Vector3f pos) {
        this.pos = pos;
        return this;
    }

    public float getPosX() {
        return pos.x;
    }

    public DefaultBodyBuilder setPosX(float x) {
        this.pos.x = x;
        return this;
    }

    public float getPosY() {
        return pos.y;
    }

    public DefaultBodyBuilder setPosY(float y) {
        this.pos.y = y;
        return this;
    }

    public float getPosZ() {
        return pos.z;
    }

    public DefaultBodyBuilder setPosZ(float z) {
        this.pos.z = z;
        return this;
    }

    public Vector3f getRot() {
        return rot;
    }

    public DefaultBodyBuilder setRot(Vector3f rot) {
        this.rot = rot;
        return this;
    }

    public float getRotX() {
        return rot.x;
    }

    public DefaultBodyBuilder setRotX(float x) {
        //FIXME
        this.rot.x = x;
        return this;
    }

    public float getRotY() {
        return rot.y;
    }

    public DefaultBodyBuilder setRotY(float y) {
        //FIXME
        this.rot.y = y;
        return this;
    }

    public float getRotZ() {
        return rot.z;
    }

    public DefaultBodyBuilder setRotZ(float z) {
        //FIXME
        this.rot.z = z;
        return this;
    }

    public Vector3f getImpulse() {
        return impulse;
    }

    public DefaultBodyBuilder setImpulse(Vector3f impulse) {
        this.impulse = impulse;
        return this;
    }

    public float getImpulseX() {
        return impulse.x;
    }

    public DefaultBodyBuilder setImpulseX(float x) {
        this.impulse.x = x;
        return this;
    }

    public float getImpulseY() {
        return impulse.y;
    }

    public DefaultBodyBuilder setImpulseY(float y) {
        this.impulse.y = y;
        return this;
    }

    public float getImpulseZ() {
        return impulse.z;
    }

    public DefaultBodyBuilder setImpulseZ(float z) {
        this.impulse.z = z;
        return this;
    }

    public CollisionShape getCollisionShape() {
        return shape;
    }

    public DefaultBodyBuilder setCollisionShape(CollisionShape shape) {
        this.shape = shape;
        return this;
    }

    public DisplayList getDisplayList() {
        return list;
    }

    public DefaultBodyBuilder setDisplayList(Runnable list) {
        this.list = new DisplayList(list);
        return this;
    }

    public DefaultBodyBuilder addChangeableParam(String name, float low, float hig) {
        changeableParams.add(new Param(name, low, hig));
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
                list,
                id
        );
    }

    @Override
    public void change() {
        if (changeableParams.size() > 0) {
            new SetParamsOfObjectDialog(changeableParams, this);
        }
    }
}
