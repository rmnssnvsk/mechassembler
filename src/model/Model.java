package model;

import java.util.Observable;
import java.util.Observer;

/**
 * <p> TODO: this
 * </p>
 * <p>Синглтон модели. Содержит в себе всю информацию о модели и умеет обновлять
 * ее с помощью метода <code>update(t)</code>.
 * </p>
 *
 * @author Mike Sorokin
 */
public class Model extends Observable {
    public static final Model model = new Model();

    private Model() {
        //TODO: this
    }

    /**
     * Обновляет состояние модели.
     * @param t промежуток времени в секундах.
     */
    public void update(double t) {
        //TODO: this
        this.setChanged();
    }
}
