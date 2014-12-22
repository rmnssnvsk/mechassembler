package model.builder;

import java.util.Arrays;

/**
 * Created on 12/15/14.
 *
 * @author Mike Sorokin
 */
public class Param {
    public final String name;
    public final float low, hig;
    public final String setterName, getterName;

    public Param(String name, float low, float hig) {
        this.name = name;
        this.low = low;
        this.hig = hig;
        StringBuilder gsName = new StringBuilder();
        for (String part : name.split("\\.")) {
            part = Character.toUpperCase(part.charAt(0)) + part.substring(1);
            gsName.append(part);
        }
        setterName = "set" + gsName.toString();
        getterName = "get" + gsName.toString();
    }
}
