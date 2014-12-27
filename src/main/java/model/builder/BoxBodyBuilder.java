package model.builder;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;
import model.Body;
import model.Material;
import org.lwjgl.opengl.GL11;
import util.DisplayList;
import util.TextureLoader;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 10/22/14.
 *
 * @author Mike Sorokin
 */
public class BoxBodyBuilder extends DefaultBodyBuilder {
    protected float mass = 1;
    protected float restitution = .1f;
    protected float friction = 1;
    protected Vector3f pos = new Vector3f(0, 0, 0);
    protected Vector3f rot = new Vector3f(0, 0, 0);
    protected Vector3f impulse = new Vector3f(0, 0, 0);
    private String texture = "";
    private Material material = new MaterialBuilder().build();
    private Vector3f size = new Vector3f(1, 1, 1);
    private Vector3f color = new Vector3f(1, 1, 1);

    public BoxBodyBuilder(String id) {
        super(id);
    }

    public float getMass() {
        return mass;
    }

    public BoxBodyBuilder setMass(float mass) {
        this.mass = mass;
        return this;
    }

    public float getRestitution() {
        return restitution;
    }

    public BoxBodyBuilder setRestitution(float restitution) {
        this.restitution = restitution;
        return this;
    }

    public float getFriction() {
        return friction;
    }

    public BoxBodyBuilder setFriction(float friction) {
        this.friction = friction;
        return this;
    }

    public Vector3f getPos() {
        return pos;
    }

    public BoxBodyBuilder setPos(Vector3f pos) {
        this.pos = pos;
        return this;
    }

    public float getPosX() {
        return pos.x;
    }

    public BoxBodyBuilder setPosX(float x) {
        this.pos.x = x;
        return this;
    }

    public float getPosY() {
        return pos.y;
    }

    public BoxBodyBuilder setPosY(float y) {
        this.pos.y = y;
        return this;
    }

    public float getPosZ() {
        return pos.z;
    }

    public BoxBodyBuilder setPosZ(float z) {
        this.pos.z = z;
        return this;
    }

    public Vector3f getRot() {
        return rot;
    }

    public BoxBodyBuilder setRot(Vector3f rot) {
        this.rot = rot;
        return this;
    }

    public float getRotX() {
        return rot.x;
    }

    public BoxBodyBuilder setRotX(float x) {
        //FIXME
        this.rot.x = x;
        return this;
    }

    public float getRotY() {
        return rot.y;
    }

    public BoxBodyBuilder setRotY(float y) {
        //FIXME
        this.rot.y = y;
        return this;
    }

    public float getRotZ() {
        return rot.z;
    }

    public BoxBodyBuilder setRotZ(float z) {
        //FIXME
        this.rot.z = z;
        return this;
    }

    public Vector3f getImpulse() {
        return impulse;
    }

    public BoxBodyBuilder setImpulse(Vector3f impulse) {
        this.impulse = impulse;
        return this;
    }

    public float getImpulseX() {
        return impulse.x;
    }

    public BoxBodyBuilder setImpulseX(float x) {
        this.impulse.x = x;
        return this;
    }

    public float getImpulseY() {
        return impulse.y;
    }

    public BoxBodyBuilder setImpulseY(float y) {
        this.impulse.y = y;
        return this;
    }

    public float getImpulseZ() {
        return impulse.z;
    }

    public BoxBodyBuilder setImpulseZ(float z) {
        this.impulse.z = z;
        return this;
    }

    public Vector3f getSize() {
        return size;
    }

    public BoxBodyBuilder setSize(Vector3f size) {
        this.size = size;
        return this;
    }

    public float getSizeX() {
        return size.x;
    }

    public BoxBodyBuilder setSizeX(float x) {
        this.size.x = x;
        return this;
    }

    public float getSizeY() {
        return size.y;
    }

    public BoxBodyBuilder setSizeY(float y) {
        this.size.y = y;
        return this;
    }

    public float getSizeZ() {
        return size.z;
    }

    public BoxBodyBuilder setSizeZ(float z) {
        this.size.z = z;
        return this;
    }

    public Vector3f getColor() {
        return color;
    }

    public BoxBodyBuilder setColor(Vector3f color) {
        this.color = color;
        return this;
    }

    public float getColorR() {
        return color.x;
    }

    public BoxBodyBuilder setColorR(float r) {
        this.color.x = r;
        return this;
    }

    public float getColorG() {
        return color.y;
    }

    public BoxBodyBuilder setColorG(float g) {
        this.color.y = g;
        return this;
    }

    public float getColorB() {
        return color.z;
    }

    public BoxBodyBuilder setColorB(float b) {
        this.color.z = b;
        return this;
    }

    public String getTexture() {
        return texture;
    }

    public BoxBodyBuilder setTexture(String texture) {
        this.texture = texture;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public BoxBodyBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Body build() {
        Vector3f inertia = new Vector3f(0, 0, 0);
        CollisionShape shape = new BoxShape(size);
        DisplayList list = new DisplayList(() -> {
            material.apply();
            glBindTexture(GL_TEXTURE_2D, TextureLoader.load(texture).getTextureID());
            glBegin(GL11.GL_QUADS);
            glColor3f(color.x, color.y, color.z);
            glNormal3f(0, 0, -1);
            glTexCoord2f(0, 0);
            glVertex3f(-size.x, -size.y, -size.z);
            glTexCoord2f(0, 1);
            glVertex3f(-size.x, +size.y, -size.z);
            glTexCoord2f(1, 1);
            glVertex3f(+size.x, +size.y, -size.z);
            glTexCoord2f(1, 0);
            glVertex3f(+size.x, -size.y, -size.z);
            glNormal3f(0, 0, +1);
            glTexCoord2f(0, 0);
            glVertex3f(-size.x, -size.y, +size.z);
            glTexCoord2f(0, 1);
            glVertex3f(-size.x, +size.y, +size.z);
            glTexCoord2f(1, 1);
            glVertex3f(+size.x, +size.y, +size.z);
            glTexCoord2f(1, 0);
            glVertex3f(+size.x, -size.y, +size.z);
            glNormal3f(0, -1, 0);
            glTexCoord2f(0, 0);
            glVertex3f(-size.x, -size.y, -size.z);
            glTexCoord2f(0, 1);
            glVertex3f(-size.x, -size.y, +size.z);
            glTexCoord2f(1, 1);
            glVertex3f(+size.x, -size.y, +size.z);
            glTexCoord2f(1, 0);
            glVertex3f(+size.x, -size.y, -size.z);
            glNormal3f(0, +1, 0);
            glTexCoord2f(0, 0);
            glVertex3f(-size.x, +size.y, -size.z);
            glTexCoord2f(0, 1);
            glVertex3f(-size.x, +size.y, +size.z);
            glTexCoord2f(1, 1);
            glVertex3f(+size.x, +size.y, +size.z);
            glTexCoord2f(1, 0);
            glVertex3f(+size.x, +size.y, -size.z);
            glNormal3f(-1, 0, 0);
            glTexCoord2f(0, 0);
            glVertex3f(-size.x, -size.y, -size.z);
            glTexCoord2f(0, 1);
            glVertex3f(-size.x, -size.y, +size.z);
            glTexCoord2f(1, 1);
            glVertex3f(-size.x, +size.y, +size.z);
            glTexCoord2f(1, 0);
            glVertex3f(-size.x, +size.y, -size.z);
            glNormal3f(+1, 0, 0);
            glTexCoord2f(0, 0);
            glVertex3f(+size.x, -size.y, -size.z);
            glTexCoord2f(0, 1);
            glVertex3f(+size.x, -size.y, +size.z);
            glTexCoord2f(1, 1);
            glVertex3f(+size.x, +size.y, +size.z);
            glTexCoord2f(1, 0);
            glVertex3f(+size.x, +size.y, -size.z);
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
