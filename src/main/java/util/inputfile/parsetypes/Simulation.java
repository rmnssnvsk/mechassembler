package util.inputfile.parsetypes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

import java.util.Arrays;
import java.util.List;

@Root
public class Simulation {
    @Element(name = "gravity")
    Vector gravity;

    @ElementList
    List<Texture> textures;

    @ElementList
    List<Material> materials;

    @ElementListUnion({
            @ElementList(entry = "sphere", type = Sphere.class, inline = true),
            @ElementList(entry = "box", type = Box.class, inline = true)
    })
    List<Object> bodies;

    @Override
    public String toString() {
        return "Simulation{" +
                "gravity=" + gravity +
                ", textures=" + textures +
                ", materials=" + materials +
                '}';
    }
}