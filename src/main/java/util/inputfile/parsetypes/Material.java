package util.inputfile.parsetypes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by roman-sosnovsky on 22.11.14.
 */
@Root
public class Material {
    @Element
    String name;
    @Element
    Vector ambient;
    @Element
    Vector diffuse;
    @Element
    Vector specular;
    @Element
    float shininess;

    @Override
    public String toString() {
        return "Material{" +
                "name='" + name + '\'' +
                ", ambient=" + ambient +
                ", diffuse=" + diffuse +
                ", specular=" + specular +
                ", shininess=" + shininess +
                '}';
    }
}
