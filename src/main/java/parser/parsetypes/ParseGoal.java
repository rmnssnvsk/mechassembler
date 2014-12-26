package parser.parsetypes;

import org.simpleframework.xml.Element;

/**
 * Created on 12/26/14.
 *
 * @author Mike Sorokin
 */
@Element(name = "goal")
public class ParseGoal {
    @Element
    public String id1;
    @Element
    public String id2;
    @Element
    public Float distance;
    @Element
    public Float time;

    @Override
    public String toString() {
        return "Goal{" +
                "id1='" + id1 + '\'' +
                ", id2='" + id2 + '\'' +
                ", distance=" + distance +
                ", time=" + time +
                '}';
    }
}
