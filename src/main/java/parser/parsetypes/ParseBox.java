package parser.parsetypes;

import org.simpleframework.xml.Element;

/**
 * Created on 22.11.14.
 *
 * @author Roman Sosnovsky
 */

@Element(name = "box")
public class ParseBox extends ParseBody {
    @Element(required = false)
    public String texture;
    @Element(required = false)
    public String material;
    @Element(required = false)
    public ParseVectorXYZ size;
    @Element(required = false)
    public ParseVectorRGB color;

    @Override
    public String toString() {
        return "Box{" +
                "id='" + id + '\'' +
                ", mass=" + mass +
                ", restitution=" + restitution +
                ", friction=" + friction +
                ", position=" + position +
                ", rotation=" + rotation +
                ", impulse=" + impulse +
                ", changeableParams=" + changeableParams +
                ", texture='" + texture + '\'' +
                ", material='" + material + '\'' +
                ", size=" + size +
                ", color=" + color +
                '}';
    }

}