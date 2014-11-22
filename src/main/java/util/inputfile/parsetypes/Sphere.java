package util.inputfile.parsetypes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by roman-sosnovsky on 22.11.14.
 */
@Root
public class Sphere {
    @Element
    public float mass;
    @Element
    public float restitution;
    @Element
    public float friction;
    @Element
    public float radius;
    @Element
    public float angularDamping;
    @Element
    public String texture;
    @Element
    public String material;
    @Element
    public Vector rotation;
    @Element
    public Vector coordinate;
    @Element
    public Vector color;

    @Override
    public String toString() {
        return "Sphere{" +
                "mass=" + mass +
                ", restitution=" + restitution +
                ", friction=" + friction +
                ", radius=" + radius +
                ", angularDamping=" + angularDamping +
                ", texture='" + texture + '\'' +
                ", material='" + material + '\'' +
                ", coordinate=" + coordinate +
                ", rotation=" + rotation +
                ", color=" + color +
                '}';
    }

}
