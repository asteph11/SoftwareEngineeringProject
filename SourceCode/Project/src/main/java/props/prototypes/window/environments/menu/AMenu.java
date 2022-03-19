package props.prototypes.window.environments.menu;

import props.prototypes.window.environments.AEnvironment;
import props.prototypes.window.environments.menu.components.AMenuButton;

import java.awt.*;
import java.util.ArrayList;

public abstract class AMenu extends AEnvironment {

    protected AMenuModel parentMenuModel;

    public final ArrayList<AMenu> pages = new ArrayList<>();
    public ArrayList<AMenuButton> buttons = new ArrayList<>();
    public MenuBundle bundle = new MenuBundle();

    public AMenu(AMenuModel parentModel) {

        parentMenuModel = parentModel;

        keyController = parentModel.getKeyController();
        mouseController = parentModel.getMouseController();
    }

    @Override
    public void draw(Graphics g) {

        for(AMenuButton b: buttons) {
            b.draw(g);
        }

    }

    @Override
    public void update(float delta) {
        System.out.println(buttons.size());
        for(AMenuButton b: buttons) {
            b.registerInput();
            b.update(delta);
        }

    }
}
