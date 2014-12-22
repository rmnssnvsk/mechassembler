package parser.parsetypes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created on 22.11.14.
 *
 * @author Roman Sosnovsky
 */
@Root
public class ParseMaterial {
    @Element
    public String name;
    @Element(required = false)
    public ParseVectorXYZ ambient;
    @Element(required = false)
    public ParseVectorXYZ diffuse;
    @Element(required = false)
    public ParseVectorXYZ specular;
    @Element(required = false)
    public Float shininess;

    @Override
    public String toString() {
        return "Material{" +
                "filename='" + name + '\'' +
                ", ambient=" + ambient +
                ", diffuse=" + diffuse +
                ", specular=" + specular +
                ", shininess=" + shininess +
                '}';
    }
}
