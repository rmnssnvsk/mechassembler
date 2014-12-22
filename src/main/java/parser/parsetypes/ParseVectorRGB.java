package parser.parsetypes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created on 22.11.14.
 *
 * @author Roman Sosnovsky
 */
@Root
public class ParseVectorRGB {
    @Element
    public Float r;
    @Element
    public Float g;
    @Element
    public Float b;

    @Override
    public String toString() {
        return "Vector{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }
}
