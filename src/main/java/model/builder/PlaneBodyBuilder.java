package model.builder;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;
import model.Body;
import model.Material;
import util.DisplayList;
import util.TextureLoader;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 12/20/14.
 *
 * @author Mike Sorokin
 */
public class PlaneBodyBuilder extends DefaultBodyBuilder {
    protected float mass = 1;
    protected float restitution = .1f;
    protected float friction = 1;
    protected Vector3f pos = new Vector3f(0, 0, 0);
    protected Vector3f rot = new Vector3f(0, 0, 0);
    protected Vector3f impulse = new Vector3f(0, 0, 0);
    private Vector3f color = new Vector3f(1, 1, 1);
    private Material material = new MaterialBuilder().build();
    private Vector3f normal = new Vector3f(0, 1, 0);
    private float size = 50;
    private float dst = 0;

    public PlaneBodyBuilder(String id) {
        super(id);
    }

    public float getMass() {
        return mass;
    }

    public PlaneBodyBuilder setMass(float mass) {
        this.mass = mass;
        return this;
    }

    public float getRestitution() {
        return restitution;
    }

    public PlaneBodyBuilder setRestitution(float restitution) {
        this.restitution = restitution;
        return this;
    }

    public float getFriction() {
        return friction;
    }

    public PlaneBodyBuilder setFriction(float friction) {
        this.friction = friction;
        return this;
    }

    public Vector3f getPos() {
        return pos;
    }

    public PlaneBodyBuilder setPos(Vector3f pos) {
        this.pos = pos;
        return this;
    }

    public float getPosX() {
        return pos.x;
    }

    public PlaneBodyBuilder setPosX(float x) {
        this.pos.x = x;
        return this;
    }

    public float getPosY() {
        return pos.y;
    }

    public PlaneBodyBuilder setPosY(float y) {
        this.pos.y = y;
        return this;
    }

    public float getPosZ() {
        return pos.z;
    }

    public PlaneBodyBuilder setPosZ(float z) {
        this.pos.z = z;
        return this;
    }

    public Vector3f getRot() {
        return rot;
    }

    public PlaneBodyBuilder setRot(Vector3f rot) {
        this.rot = rot;
        return this;
    }

    public float getRotX() {
        return rot.x;
    }

    public PlaneBodyBuilder setRotX(float x) {
        //FIXME
        this.rot.x = x;
        return this;
    }

    public float getRotY() {
        return rot.y;
    }

    public PlaneBodyBuilder setRotY(float y) {
        //FIXME
        this.rot.y = y;
        return this;
    }

    public float getRotZ() {
        return rot.z;
    }

    public PlaneBodyBuilder setRotZ(float z) {
        //FIXME
        this.rot.z = z;
        return this;
    }

    public Vector3f getImpulse() {
        return impulse;
    }

    public PlaneBodyBuilder setImpulse(Vector3f impulse) {
        this.impulse = impulse;
        return this;
    }

    public float getImpulseX() {
        return impulse.x;
    }

    public PlaneBodyBuilder setImpulseX(float x) {
        this.impulse.x = x;
        return this;
    }

    public float getImpulseY() {
        return impulse.y;
    }

    public PlaneBodyBuilder setImpulseY(float y) {
        this.impulse.y = y;
        return this;
    }

    public float getImpulseZ() {
        return impulse.z;
    }

    public PlaneBodyBuilder setImpulseZ(float z) {
        this.impulse.z = z;
        return this;
    }

    public Vector3f getColor() {
        return color;
    }

    public PlaneBodyBuilder setColor(Vector3f color) {
        this.color = color;
        return this;
    }

    public float getColorR() {
        return color.x;
    }

    public PlaneBodyBuilder setColorR(float r) {
        this.color.x = r;
        return this;
    }

    public float getColorG() {
        return color.y;
    }

    public PlaneBodyBuilder setColorG(float g) {
        this.color.y = g;
        return this;
    }

    public float getColorB() {
        return color.z;
    }

    public PlaneBodyBuilder setColorB(float b) {
        this.color.z = b;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public PlaneBodyBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Vector3f getNormal() {
        return normal;
    }

    public PlaneBodyBuilder setNormal(Vector3f normal) {
        this.normal = normal;
        return this;
    }

    public float getNormalX() {
        return normal.x;
    }

    public PlaneBodyBuilder setNormalX(float x) {
        this.normal.x = x;
        return this;
    }

    public float getNormalY() {
        return normal.y;
    }

    public PlaneBodyBuilder setNormalY(float y) {
        this.normal.y = y;
        return this;
    }

    public float getNormalZ() {
        return normal.z;
    }

    public PlaneBodyBuilder setNormalZ(float z) {
        this.normal.z = z;
        return this;
    }

    public float getSize() {
        return size;
    }

    public PlaneBodyBuilder setSize(float size) {
        this.size = size;
        return this;
    }

    public float getDst() {
        return dst;
    }

    public PlaneBodyBuilder setDst(float dst) {
        this.dst = dst;
        return this;
    }

    public Body build() {
        Vector3f inertia = new Vector3f(0, 0, 0);
        CollisionShape shape = new StaticPlaneShape(normal, dst);
        DisplayList list = new DisplayList(() -> {
            material.apply();
            glColor3f(color.x, color.y, color.z);
            glBindTexture(GL_TEXTURE_2D, TextureLoader.load("").getTextureID());
            glBegin(GL_QUADS);
            glNormal3f(normal.x, normal.y, normal.z);
            glVertex3f(-size, 0, -size);
            glVertex3f(+size, 0, -size);
            glVertex3f(+size, 0, +size);
            glVertex3f(-size, 0, +size);
            glEnd();
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
        RigidBody rigidBody = new RigidBody(info);
        rigidBody.setUserPointer(this);
        rigidBody.applyCentralImpulse(impulse);
        return new Body(
                rigidBody,
                list,
                id
        );
    }
}
