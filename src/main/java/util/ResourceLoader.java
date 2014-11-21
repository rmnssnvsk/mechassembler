package util;

import java.io.File;
import java.net.URL;

/**
 * Created by roman-sosnovsky on 22.11.14.
 */
public class ResourceLoader {
    private URL res;

    public ResourceLoader(String src) {
        res = getClass().getClassLoader().getResource(src);
    }

    public File getFile() {
        return new File(res.getFile());
    }

    public URL getURL() {
        return res;
    }
}
