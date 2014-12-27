package main;

import controller.Controller;
import model.Level;
import model.Model;
import parser.LevelParser;
import util.ResourceLoader;
import view.View;
import view.builder.ViewBuilder;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import java.io.File;


/**
 * <p>
 * Главный класс. Создает модель, представление, контроллер, инициализирует и использует их.
 * </p><p>
 * Если исрользуются нативные библиотеки, то необходимо указать параметр запуска
 * <code>-Djava.library.path=lib/native</code>
 * </p>
 *
 * @author Mike Sorokin
 */

public class Main {

    public Main() {
        String levelName;
        {
            String[] levelList = new File(new ResourceLoader("levels").getURL().getFile()).list();
            if (levelList == null || levelList.length == 0) {
                JOptionPane.showMessageDialog(null, "No any levels found.");
                return;
            }
            for (int i = 0; i < levelList.length; i++) {
                levelList[i] = levelList[i].replaceAll("\\.xml", "");
            }
            Icon icon = new MetalIconFactory.TreeFolderIcon();
            levelName = (String) JOptionPane.showInputDialog(null, "Select level:", "Mechassembler", JOptionPane.QUESTION_MESSAGE, icon, levelList, levelList[0]);
            if (levelName == null) {
                return;
            }
        }
        Level level = LevelParser.parse(new ResourceLoader("levels/" + levelName + ".xml").getFile());
        View view = new ViewBuilder()
                .setMouseGrabbed(true)
                .setTitle("Mechassembler")
                .setCamera(level.getCamera())
                .setVertexShaderName("shaders/light.vert")
                .setFragmentShaderName("shaders/light.frag")
                .build();
        Model model = new Model(level);
        Controller controller = new Controller(model, view);
        model.addObserver(controller);
        view.addObserver(controller);

        model.start();
        model.delete();
        view.delete();
    }

    /**
     * Главный метод. Если исрользуются нативные библиотеки, то необходимо указать параметр запуска
     * <code>-Djava.library.path=lib/native</code>
     *
     * @param args Аргументы, с которыми была вызвана программа.
     */
    public static void main(String[] args) {
        new Main();
    }
}
