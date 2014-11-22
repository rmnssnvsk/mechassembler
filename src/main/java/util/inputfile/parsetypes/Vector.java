package util.inputfile.parsetypes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by roman-sosnovsky on 22.11.14.
 */
@Root
public class Vector {
    @Element(name = "x")
    public float x;
    @Element(name = "y")
    public float y;
    @Element(name = "z")
    public float z;

    @Override
    public String toString() {
        return "Vector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
