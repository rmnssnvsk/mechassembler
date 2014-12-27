package parser.parsetypes;

import org.simpleframework.xml.Element;

/**
 * Created on 12/27/14.
 *
 * @author Mike Sorokin
 */
@Element(name = "camera")
public class ParseCamera {
    @Element(required = false)
    public ParseVectorXYZ position;
    @Element(required = false)
    public ParseVectorXYZ rotation;
    @Element(required = false)
    public Float fov;
    @Element(required = false)
    public Float aspect;
    @Element(required = false)
    public Float z_near;
    @Element(required = false)
    public Float z_far;
    @Element(required = false)
    public Boolean controllable;

    @Override
    public String toString() {
        return "Camera{" +
                "position=" + position +
                ", rotation=" + rotation +
                ", fov=" + fov +
                ", aspect=" + aspect +
                ", z_near=" + z_near +
                ", z_far=" + z_far +
                ", controllable=" + controllable +
                '}';
    }
}
