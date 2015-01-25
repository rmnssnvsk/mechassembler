package util.parser.parsetypes;

import org.simpleframework.xml.Element;

/**
 * Created on 12/20/14.
 *
 * @author Mike Sorokin
 */
@Element(name = "param")
public class ParseParam {
    @Element
    public String name;
    @Element
    public Float low;
    @Element
    public Float hig;

    @Override
    public String toString() {
        return "Param{" +
                "filename='" + name + '\'' +
                ", low=" + low +
                ", hig=" + hig +
                '}';
    }
}
