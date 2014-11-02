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

    public MaterialBuilder setAmbient(Vector4f ambient) {
        this.ambient = ambient;
        return this;
    }

    public MaterialBuilder setDiffuse(Vector4f diffuse) {
        this.diffuse = diffuse;
        return this;
    }

    public MaterialBuilder setSpecular(Vector4f specular) {
        this.specular = specular;
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
