package parser.parsetypes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created on 22.11.14.
 *
 * @author Roman Sosnovsky
 */
@Root
public class ParseTexture {
    @Element
    public String name;
    @Element
    public String filename;

    @Override
    public String toString() {
        return "Texture{" +
                "filename='" + name + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
