package model.builder;

import model.Material;

import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;

/**
 * Created on 10/27/14.
 *
 * @author Mike Sorokin
 */
public class MaterialBuilder {
    private Vector4f ambient = new Vector4f(1, 1, 1, 1);
    private Vector4f diffuse = new Vector4f(1, 1, 1, 1);
    private Vector4f specular = new Vector4f(1, 1, 1, 1);
    private float shininess = 128;

    public MaterialBuilder setAmbient(Vector3f ambient) {
        this.ambient = new Vector4f(ambient.x, ambient.y, ambient.z, 1);
        return this;
    }

    public MaterialBuilder setDiffuse(Vector3f diffuse) {
        this.diffuse = new Vector4f(diffuse.x, diffuse.y, diffuse.z, 1);
        return this;
    }

    public MaterialBuilder setSpecular(Vector3f specular) {
        this.specular = new Vector4f(specular.x, specular.y, specular.z, 1);
        return this;
    }

    public MaterialBuilder setShininess(float shininess) {
        this.shininess = shininess;
        return this;
    }

    public Material build() {
        return new Material(ambient, diffuse, specular, shininess);
    }
}
