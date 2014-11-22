package util.inputfile.parsetypes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by roman-sosnovsky on 22.11.14.
 */
@Root
public class Texture {
    @Element
    public String name;
    @Element(name = "filename")
    public String fileName;

    @Override
    public String toString() {
        return "Texture{" +
                "name='" + name + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}
