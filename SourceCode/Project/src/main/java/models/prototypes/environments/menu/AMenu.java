package models.prototypes.environments.menu;

import controls.MenuControls;
import controls.menu.MenuKeyControls;
import models.prototypes.controls.AKeyController;
import models.prototypes.controls.AMouseController;
import models.prototypes.environments.menu.components.AMenuComponent;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.resources.Resources;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class AMenu implements IUpdatable, IDrawable {

    // The Parent Menu Model
    protected AMenuEnvironment parentMenuModel;
    // Controllers
    protected AKeyController keyController;
    protected AMouseController mouseController;
    // Background Image
    protected BufferedImage image_buttonRect;

    // The Menu Buttons.
    protected ArrayList<AMenuComponent> components = new ArrayList<>();

    // The Bundle of sub page AMenu objects.
    //public MenuBundle bundle = new MenuBundle();

    protected float centerW = Config.DEFAULT_WINDOW_WIDTH * .5f, centerH = Config.DEFAULT_WINDOW_HEIGHT * .5f;

    public AMenu(AMenuEnvironment parentMenuModel) {
        this.parentMenuModel = parentMenuModel;

        keyController = parentMenuModel.getKeyController();
        mouseController = parentMenuModel.getMouseController();
    }

    public boolean registerInput() {
        boolean isActivated = false;
        if (parentMenuModel.getKeyController() instanceof MenuKeyControls kc) {
            isActivated = kc.isAction(MenuControls.Actions.ESCAPE);
            if (isActivated) {
                kc.reset();
                parentMenuModel.pop();
            }
        }
        return isActivated;
    }

    public Resources getResources() {
        return parentMenuModel.getResources();
    }

    @Override
    public void draw(Graphics g) {

        for (AMenuComponent c : components) {
            c.draw(g);
        }

    }

    @Override
    public void update(float delta) {

        registerInput();

        for (AMenuComponent c : components) {
            c.update(delta);
        }

    }

    public void reset() {
        for(AMenuComponent c: components) {
            c.reset();
        }
    }
}