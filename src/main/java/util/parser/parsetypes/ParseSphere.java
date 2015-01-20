package util.parser.parsetypes;

import org.simpleframework.xml.Element;

/**
 * Created on 22.11.14.
 *
 * @author Roman Sosnovsky
 */

@Element(name = "sphere")
public class ParseSphere extends ParseBody {
    @Element(required = false)
    public Float radius;
    @Element(name = "angular_damping", required = false)
    public Float angularDamping;
    @Element(required = false)
    public String texture;
    @Element(required = false)
    public String material;
    @Element(required = false)
    public ParseVectorRGB color;

    @Override
    public String toString() {
        return "Sphere{" +
                "id='" + id + '\'' +
                ", mass=" + mass +
                ", restitution=" + restitution +
                ", friction=" + friction +
                ", position=" + position +
                ", rotation=" + rotation +
                ", impulse=" + impulse +
                ", changeableParams=" + changeableParams +
                ", radius=" + radius +
                ", angularDamping=" + angularDamping +
                ", texture='" + texture + '\'' +
                ", material='" + material + '\'' +
                ", color=" + color +
                '}';
    }

}
