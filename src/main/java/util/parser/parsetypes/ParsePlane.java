package util.parser.parsetypes;

import org.simpleframework.xml.Element;

/**
 * Created on 12/21/14.
 *
 * @author Mike Sorokin
 */
@Element(name = "plane")
public class ParsePlane extends ParseBody {
    @Element(required = false)
    public ParseVectorRGB color;
    @Element(required = false)
    public String material;
    @Element(required = false)
    public ParseVectorXYZ normal;
    @Element(required = false)
    public Float size;
    @Element(required = false)
    public Float distance;

    @Override
    public String toString() {
        return "Plane{" +
                "id='" + id + '\'' +
                ", mass=" + mass +
                ", restitution=" + restitution +
                ", friction=" + friction +
                ", position=" + position +
                ", rotation=" + rotation +
                ", impulse=" + impulse +
                ", changeableParams=" + changeableParams +
                ", color=" + color +
                ", material='" + material + '\'' +
                ", normal=" + normal +
                ", size=" + size +
                ", distance=" + distance +
                '}';
    }
}
