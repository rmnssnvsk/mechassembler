package util;

import java.io.*;
import java.net.URL;
import java.util.*;

/**
 * Created on 22.11.14.
 *
 * @author Roman Sosnovsky
 */
public class ResourceLoader {
    public ResourceLoader() {

    }

    private URL res;

    public URL getURL(String src) {
        if (res == null) {
            res = getClass().getClassLoader().getResource(src);
        }
        return res;
    }

    public File getFile(String src) {
        try {
            InputStream resStream = getURL(src).openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(resStream));
            File fileToLoad = File.createTempFile("mechassembler", null);
            PrintWriter outFile = new PrintWriter(fileToLoad);

            String line;
            while ((line = reader.readLine()) != null) {
                outFile.println(line);
            }

            resStream.close();
            outFile.close();
            return fileToLoad;
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
    // PROG DIR ============================================================

    private File progDir = new File(System.getProperty("user.dir") + File.separatorChar + ".mechassembler");

    private File getProgDir() {
        if(!checkProgDir()) {
            createProgDir();
        }

        return progDir;
    }

    private boolean checkProgDir() {
        System.out.println("home dir is " + progDir.getPath());
        return progDir.exists() && progDir.isDirectory() && progDir.canRead() && progDir.canWrite();
    }

    private void createProgDir() {
        new File(System.getProperty("user.dir") + File.separatorChar + ".mechassembler").mkdir();
        System.out.println("home dir created in " + progDir.getPath());
    }
    */

    // LEVELS

    public List<String> getLevelsList() {
        List<String> levelsList = new LinkedList<>();
        try {
            Scanner in = new Scanner(new ResourceLoader().getFile("res-levels-list"));
            while (in.hasNextLine()) {
                levelsList.add(in.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return levelsList;
    }
}