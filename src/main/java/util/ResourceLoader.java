package util;

import java.io.*;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by roman-sosnovsky on 22.11.14.
 */
public class ResourceLoader {
    private URL res;

    public ResourceLoader(String src) {
        res = getClass().getClassLoader().getResource(src);
    }

    public File getFile() throws IOException {
        InputStream resStream = res.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(resStream));
        File fileToLoad = File.createTempFile(new Random().nextLong() + "", ".tmp");
        System.out.println("Created tmp file " + fileToLoad.getName());
        PrintWriter outFile = new PrintWriter(fileToLoad);

        String line;
        while ((line = reader.readLine()) != null) {
            outFile.println(line);
        }

        resStream.close();
        outFile.close();
        return fileToLoad;
    }

    public URL getURL() {
        return res;
    }
}
