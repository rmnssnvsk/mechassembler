package util.parser.parsetypes;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "simulation")
public class ParseSimulation {
    @Element
    public ParseVectorXYZ gravity;

    @ElementList
    public List<ParseTexture> textures;

    @ElementList
    public List<ParseMaterial> materials;

    @Element(required = false)
    public ParseGoal goal;

    @Element
    public BodyList bodies;

    @Override
    public String toString() {
        return "Simulation{" +
                "gravity=" + gravity +
                ", textures=" + textures +
                ", materials=" + materials +
                ", goal=" + goal +
                ", bodies=" + bodies +
                '}';
    }

    public static class BodyList {
        @ElementListUnion({
                @ElementList(entry = "sphere", type = ParseSphere.class, inline = true),
                @ElementList(entry = "box", type = ParseBox.class, inline = true),
                @ElementList(entry = "plane", type = ParsePlane.class, inline = true),
                @ElementList(entry = "objmodel", type = ParseOBJModel.class, inline = true)
        })
        public List<ParseBody> bodies;

        @Override
        public String toString() {
            return "BodyList{" +
                    "bodies=" + bodies +
                    '}';
        }
    }
}