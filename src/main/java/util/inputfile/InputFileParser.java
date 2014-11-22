package util.inputfile;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import util.inputfile.parsetypes.Simulation;

import java.io.File;

public class InputFileParser {
    public InputFileParser(File file) throws Exception {
        Serializer serializer = new Persister();
        Simulation sym = serializer.read(Simulation.class, file);
        System.out.println(sym);
    }
}
