package model;

import javax.vecmath.Vector4f;

import static org.lwjgl.opengl.GL11.*;
import static util.FlippedFloatBuffer.asFloatBuffer;

/**
 * Created on 10/27/14.
 *
 * @author Mike Sorokin
 */
public class Material {
    private final Vector4f ambient, diffuse, specular;
    private final float shininess;

    public Material(Vector4f ambient, Vector4f diffuse, Vector4f specular, float shininess) {
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
    }

    public void apply() {
        glMaterial(GL_FRONT, GL_AMBIENT, asFloatBuffer(ambient.x, ambient.y, ambient.z, ambient.w));
        glMaterial(GL_FRONT, GL_DIFFUSE, asFloatBuffer(diffuse.x, diffuse.y, diffuse.z, diffuse.w));
        glMaterial(GL_FRONT, GL_SPECULAR, asFloatBuffer(specular.x, specular.y, specular.z, specular.w));
        glMaterialf(GL_FRONT, GL_SHININESS, shininess);
    }
}
