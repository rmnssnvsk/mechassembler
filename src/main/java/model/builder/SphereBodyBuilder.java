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
import static java.lang.Math.toRadians;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created on 10/22/14.
 *
 * @author Mike Sorokin
 */
public class SphereBodyBuilder {
    private float mass = 1;
    private float restitution = .1f;
    private float friction = .1f;
    private float radius = 1;
    private float angularDamping = .3f;
    private Texture texture = TextureLoader.NO_TEXTURE;
    private Material material = new MaterialBuilder().build();
    private Vector3f pos = new Vector3f(0, 0, 0);
    private Vector3f rot = new Vector3f(0, 0, 0);
    private Vector3f color = new Vector3f(1, 1, 1);

    public SphereBodyBuilder setMass(float mass) {
        this.mass = mass;
        return this;
    }

    public SphereBodyBuilder setRestitution(float restitution) {
        this.restitution = restitution;
        return this;
    }

    public SphereBodyBuilder setFriction(float friction) {
        this.friction = friction;
        return this;
    }

    public SphereBodyBuilder setAngularDamping(float angularDamping) {
        this.angularDamping = angularDamping;
        return this;
    }

    public SphereBodyBuilder setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public SphereBodyBuilder setPos(Vector3f pos) {
        this.pos = pos;
        return this;
    }

    public SphereBodyBuilder setPosX(float x) {
        this.pos.x = x;
        return this;
    }

    public SphereBodyBuilder setPosY(float y) {
        this.pos.y = y;
        return this;
    }

    public SphereBodyBuilder setPosZ(float z) {
        this.pos.z = z;
        return this;
    }

    public SphereBodyBuilder setRot(Vector3f rot) {
        this.rot = rot;
        return this;
    }

    public SphereBodyBuilder setRotX(float x) {
        //FIXME
        this.rot.x = x;
        return this;
    }

    public SphereBodyBuilder setRotY(float y) {
        //FIXME
        this.rot.y = y;
        return this;
    }

    public SphereBodyBuilder setRotZ(float z) {
        //FIXME
        this.rot.z = z;
        return this;
    }

    public SphereBodyBuilder setColor(Vector3f color) {
        this.color = color;
        return this;
    }

    public SphereBodyBuilder setColorR(float r) {
        this.color.x = r;
        return this;
    }

    public SphereBodyBuilder setColorG(float g) {
        this.color.y = g;
        return this;
    }

    public SphereBodyBuilder setColorZ(float b) {
        this.color.z = b;
        return this;
    }

    public SphereBodyBuilder setTexture(Texture texture) {
        this.texture = texture;
        return this;
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
        return new Body(
                new RigidBody(info),
                list
        );
    }
}
