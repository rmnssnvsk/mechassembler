package parser.parsetypes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created on 22.11.14.
 *
 * @author Roman Sosnovsky
 */
@Root
public class ParseVectorXYZ {
    @Element
    public Float x;
    @Element
    public Float y;
    @Element
    public Float z;

    @Override
    public String toString() {
        return "Vector{" +
                "r=" + x +
                ", g=" + y +
                ", b=" + z +
                '}';
    }
}
