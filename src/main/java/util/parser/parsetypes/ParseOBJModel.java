package util.parser.parsetypes;

/**
 * Created on 12/20/14.
 *
 * @author Mike Sorokin
 */

import org.simpleframework.xml.Element;

@Element(name = "objmodel")
public class ParseOBJModel extends ParseBody {
    @Element
    public String filename;

    @Override
    public String toString() {
        return "OBJModel{" +
                "id='" + id + '\'' +
                ", mass=" + mass +
                ", restitution=" + restitution +
                ", friction=" + friction +
                ", position=" + position +
                ", rotation=" + rotation +
                ", impulse=" + impulse +
                ", changeableParams=" + changeableParams +
                ", filename='" + filename + '\'' +
                '}';
    }
}