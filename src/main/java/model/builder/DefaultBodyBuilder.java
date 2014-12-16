package model.builder;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;

/**
 * Created on 12/16/14.
 *
 * @author Mike Sorokin
 */
public class DefaultBodyBuilder extends AbstractBodyBuilder {
    protected LinkedList<Param> changeableParams = new LinkedList<>();

    public DefaultBodyBuilder(String id) {
        super(id);
    }

    public DefaultBodyBuilder addChangeableParam(String name, float low, float hig) {
        changeableParams.add(new Param(name, low, hig));
        return this;
    }

    @Override
    public void change() {
        for (Param p : changeableParams) {
            try {
                float f;
                while (true) {
                    String change = JOptionPane.showInputDialog(
                            null,
                            String.format("%s? [%.2f - %.2f]", p.name, p.low, p.hig),
                            Float.toString((Float) this.getClass().getDeclaredMethod(p.getterName).invoke(this))
                    );
                    try {
                        f = Float.parseFloat(change);
                        if (p.low <= f && f <= p.hig) {
                            break;
                        }
                    } catch (NullPointerException | NumberFormatException ignored) {
                    }
                }
                this.getClass().getDeclaredMethod(p.setterName, float.class).invoke(this, f);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
