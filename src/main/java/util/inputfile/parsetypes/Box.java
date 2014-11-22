package util.inputfile.parsetypes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by roman-sosnovsky on 22.11.14.
 */
@Root
public class Box {
    @Element
    public float mass;
    @Element
    public float restitution;
    @Element
    public float friction;
    @Element
    public String texture;
    @Element
    public String material;
    @Element
    public Vector coordinate;
    @Element
    public Vector rotation;
    @Element
    public Vector size;
    @Element
    public Vector color;

    @Override
    public String toString() {
        return "Box{" +
                "mass=" + mass +
                ", restitution=" + restitution +
                ", friction=" + friction +
                ", texture='" + texture + '\'' +
                ", material='" + material + '\'' +
                ", coordinate=" + coordinate +
                ", rotation=" + rotation +
                ", size=" + size +
                ", color=" + color +
                '}';
    }
}