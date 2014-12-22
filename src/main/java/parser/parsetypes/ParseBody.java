package parser.parsetypes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

/**
 * Created on 12/20/14.
 *
 * @author Mike Sorokin
 */
public class ParseBody {
    @Element
    public String id;
    @Element(required = false)
    public Float mass;
    @Element(required = false)
    public Float restitution;
    @Element(required = false)
    public Float friction;
    @Element(required = false)
    public ParseVectorXYZ position;
    @Element(required = false)
    public ParseVectorXYZ rotation;
    @Element(required = false)
    public ParseVectorXYZ impulse;
    @ElementList(name = "changeable_params", required = false)
    public List<ParseParam> changeableParams;

    @Override
    public String toString() {
        return "Body{" +
                "id='" + id + '\'' +
                ", mass=" + mass +
                ", restitution=" + restitution +
                ", friction=" + friction +
                ", position=" + position +
                ", rotation=" + rotation +
                ", impulse=" + impulse +
                ", changeableParams=" + changeableParams +
                '}';
    }
}
