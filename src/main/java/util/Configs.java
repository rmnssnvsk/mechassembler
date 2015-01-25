package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created on 17.05.2014.
 *
 * @author Mike Sorokin
 */
public class Configs {
    private static HashMap<String, String> params;

    static {
        params = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new ResourceLoader().getFile("settings.cfg")));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.matches("\\s*#.*")) {
                    continue;
                }
                line = line.trim();
                if (line.matches("[a-zA-Z0-9_]*\\s*=\\s*.*")) {
                    String[] param = line.split(" *= *");
                    params.put(param[0], param[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setParam(String name, String value) {
        params.put(name, value);
    }

    public static String getParam(String name) {
        return params.get(name);
    }

    public static int getParamI(String name) {
        return Integer.valueOf(params.get(name));
    }

    public static float getParamF(String name) {
        return Float.valueOf(params.get(name));
    }
}
