package view;

import controller.Controller;
import model.Model;

import java.util.Observable;
import java.util.Observer;

/**
 * <p> TODO: this
 * </p>
 * <p> Класс представления. Представление читает {@link model.Model модель} данных, визуализирует ее и
 * сообщает {@link controller.Controller контроллеру} о действиях пользователя.
 * </p>
 * @author Mike Sorokin
 */
public class View extends Observable implements Observer {

    /**
     * Создает новое представление и регистриует его в {@link model.Model модели}.
     */
    public View() {
        Model.model.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        //TODO: this
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Устанавливает {@link controller.Controller контроллер}, который это представление будет оновлять.
     * @param controller {@link controller.Controller контроллер}, который это представление будет оновлять.
     */
    public void setController(Controller controller) {
        this.addObserver(controller);
    }
}
