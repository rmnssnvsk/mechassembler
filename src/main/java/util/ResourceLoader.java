package util;

import java.io.*;
import java.net.URL;
import java.util.Random;

/**
 * Created on 22.11.14.
 * @author Roman Sosnovsky
 */
public class ResourceLoader {
    private URL res;

    public ResourceLoader(String src) {
        res = getClass().getClassLoader().getResource(src);
    }

    public File getFile() {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public URL getURL() {
        return res;
    }
}
