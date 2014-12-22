package model.builder;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;
import model.Body;
import model.Material;
import org.newdawn.slick.opengl.Texture;
import util.DisplayList;
import util.TextureLoader;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

import static java.lang.Math.*;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 10/22/14.
 *
 * @author Mike Sorokin
 */
public class SphereBodyBuilder extends DefaultBodyBuilder {
    protected float mass = 1;
    protected float restitution = .1f;
    protected float friction = 1;
    protected Vector3f pos = new Vector3f(0, 0, 0);
    protected Vector3f rot = new Vector3f(0, 0, 0);
    protected Vector3f impulse = new Vector3f(0, 0, 0);
    private float radius = 1;
    private float angularDamping = .3f;
    private Texture texture = TextureLoader.NO_TEXTURE;
    private Material material = new MaterialBuilder().build();
    private Vector3f color = new Vector3f(1, 1, 1);

    public SphereBodyBuilder(String id) {
        super(id);
    }

    public float getMass() {
        return mass;
    }

    public SphereBodyBuilder setMass(float mass) {
        this.mass = mass;
        return this;
    }

    public float getRestitution() {
        return restitution;
    }

    public SphereBodyBuilder setRestitution(float restitution) {
        this.restitution = restitution;
        return this;
    }

    public float getFriction() {
        return friction;
    }

    public SphereBodyBuilder setFriction(float friction) {
        this.friction = friction;
        return this;
    }

    public Vector3f getPos() {
        return pos;
    }

    public SphereBodyBuilder setPos(Vector3f pos) {
        this.pos = pos;
        return this;
    }

    public float getPosX() {
        return pos.x;
    }

    public SphereBodyBuilder setPosX(float x) {
        this.pos.x = x;
        return this;
    }

    public float getPosY() {
        return pos.y;
    }

    public SphereBodyBuilder setPosY(float y) {
        this.pos.y = y;
        return this;
    }

    public float getPosZ() {
        return pos.z;
    }

    public SphereBodyBuilder setPosZ(float z) {
        this.pos.z = z;
        return this;
    }

    public Vector3f getRot() {
        return rot;
    }

    public SphereBodyBuilder setRot(Vector3f rot) {
        this.rot = rot;
        return this;
    }

    public float getRotX() {
        return rot.x;
    }

    public SphereBodyBuilder setRotX(float x) {
        //FIXME
        this.rot.x = x;
        return this;
    }

    public float getRotY() {
        return rot.y;
    }

    public SphereBodyBuilder setRotY(float y) {
        //FIXME
        this.rot.y = y;
        return this;
    }

    public float getRotZ() {
        return rot.z;
    }

    public SphereBodyBuilder setRotZ(float z) {
        //FIXME
        this.rot.z = z;
        return this;
    }

    public Vector3f getImpulse() {
        return impulse;
    }

    public SphereBodyBuilder setImpulse(Vector3f impulse) {
        this.impulse = impulse;
        return this;
    }

    public float getImpulseX() {
        return impulse.x;
    }

    public SphereBodyBuilder setImpulseX(float x) {
        this.impulse.x = x;
        return this;
    }

    public float getImpulseY() {
        return impulse.y;
    }

    public SphereBodyBuilder setImpulseY(float y) {
        this.impulse.y = y;
        return this;
    }

    public float getImpulseZ() {
        return impulse.z;
    }

    public SphereBodyBuilder setImpulseZ(float z) {
        this.impulse.z = z;
        return this;
    }

    public float getAngularDamping() {
        return angularDamping;
    }

    public SphereBodyBuilder setAngularDamping(float angularDamping) {
        this.angularDamping = angularDamping;
        return this;
    }

    public float getRadius() {
        return radius;
    }

    public SphereBodyBuilder setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public Vector3f getColor() {
        return color;
    }

    public SphereBodyBuilder setColor(Vector3f color) {
        this.color = color;
        return this;
    }

    public float getColorR() {
        return color.x;
    }

    public SphereBodyBuilder setColorR(float r) {
        this.color.x = r;
        return this;
    }

    public float getColorG() {
        return color.y;
    }

    public SphereBodyBuilder setColorG(float g) {
        this.color.y = g;
        return this;
    }

    public float getColorB() {
        return color.z;
    }

    public SphereBodyBuilder setColorB(float b) {
        this.color.z = b;
        return this;
    }

    public Texture getTexture() {
        return texture;
    }

    public SphereBodyBuilder setTexture(Texture texture) {
        this.texture = texture;
        return this;
    }

    public Material getMaterial() {
        return material;
    }

    public SphereBodyBuilder setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Body build() {
        Vector3f inertia = new Vector3f(0, 0, 0);
        CollisionShape shape = new SphereShape(radius);
        DisplayList list = new DisplayList(() -> {
            material.apply();
            glColor3f(color.x, color.y, color.z);
            glBindTexture(GL_TEXTURE_2D, texture.getTextureID());
            glBegin(GL_QUADS);
            int heightPolygons = 40;
            int widthPolygons = 40;
            for (float beta = 0; beta < 180; beta += 180f / heightPolygons) {
                for (float alpha = 0; alpha < 360; alpha += 360f / widthPolygons) {
                    float x, y, z;
                    x = (float) (sin(toRadians(beta)) * cos(toRadians(alpha)));
                    y = (float) (cos(toRadians(beta)));
                    z = (float) (sin(toRadians(beta)) * sin(toRadians(alpha)));
                    glTexCoord2f(alpha / 360, beta / 180);
                    glNormal3f(x, y, z);
                    glVertex3f(x * radius, y * radius, z * radius);
                    x = (float) (sin(toRadians(beta + 180f / heightPolygons)) * cos(toRadians(alpha)));
                    y = (float) (cos(toRadians(beta + 180f / heightPolygons)));
                    z = (float) (sin(toRadians(beta + 180f / heightPolygons)) * sin(toRadians(alpha)));
                    glTexCoord2f(alpha / 360, (beta + 180f / heightPolygons) / 180);
                    glNormal3f(x, y, z);
                    glVertex3f(x * radius, y * radius, z * radius);
                    x = (float) (sin(toRadians(beta + 180f / heightPolygons)) * cos(toRadians(alpha + 360f / widthPolygons)));
                    y = (float) (cos(toRadians(beta + 180f / heightPolygons)));
                    z = (float) (sin(toRadians(beta + 180f / heightPolygons)) * sin(toRadians(alpha + 360f / widthPolygons)));
                    glTexCoord2f((alpha + 360f / widthPolygons) / 360, (beta + 180f / heightPolygons) / 180);
                    glNormal3f(x, y, z);
                    glVertex3f(x * radius, y * radius, z * radius);
                    x = (float) (sin(toRadians(beta)) * cos(toRadians(alpha + 360f / widthPolygons)));
                    y = (float) (cos(toRadians(beta)));
                    z = (float) (sin(toRadians(beta)) * sin(toRadians(alpha + 360f / widthPolygons)));
                    glTexCoord2f((alpha + 360f / widthPolygons) / 360, beta / 180);
                    glNormal3f(x, y, z);
                    glVertex3f(x * radius, y * radius, z * radius);
                }
            }
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
        info.angularDamping = angularDamping;
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
