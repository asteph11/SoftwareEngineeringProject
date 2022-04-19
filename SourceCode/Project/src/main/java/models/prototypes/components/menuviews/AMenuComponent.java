package models.prototypes.components.menuviews;

import models.environments.EnvironmentsHandler;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.sprites.SpriteSheet;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AMenuComponent implements IUpdatable, IDrawable {

    protected SpriteSheet spritesheet;

    protected ImageScale scaleType = ImageScale.FILL_XY;

    public enum ImageScale {
        FIT_CENTERED,
        CENTER_CROP,
        FILL_XY
    }

    protected Color foregroundColor = new Color(0, 0, 0);
    protected Color backgroundColor = new Color(0, 0, 0, 0);
    protected BufferedImage backgroundImage;
    protected BufferedImage tint;

    // The Parent menu model which holds all pages and subpages.
    private final AMenuEnvironment parentMenuEnvironment;

    // Text to be displayed.
    // Will be removed if we add actual images in place of awt views.graphics.
    protected String text = "";

    protected int x, y; // Coordinates on the 2D space, relative to the default screen size
    protected int w, h; // The width of the component

    protected boolean isFocused = false;
    protected boolean isPressed = false;
    protected boolean playSound = true;

    public AMenuComponent(AMenuEnvironment parentMenuEnvironment) {
        this.parentMenuEnvironment = parentMenuEnvironment;
    }

    protected AMenuEnvironment getParentEnvironment() {
        return parentMenuEnvironment;
    }

    protected EnvironmentsHandler getEnvironmentHandler() {
        return parentMenuEnvironment.getParentEnvironmentsHandler();
    }

    protected boolean isInBounds(float mx, float my) {
        mx /= Config.scaledW;
        my /= Config.scaledH;

        boolean horizBound = (mx >= x && mx <= (x + w));
        boolean vertBound = (my >= y && my <= (y + h));

        setIsFocused(horizBound && vertBound);

        return horizBound && vertBound;
    }

    protected void setIsFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }

    public abstract boolean onClick(float x, float y);

    @Override
    public void update(float delta) {
        isFocused = false;
        isPressed = false;

        registerInput();
        updateSpriteSheet(delta);
    }

    public void updateSpriteSheet(float delta) {
        if(spritesheet != null) {
            if (backgroundImage == null && isFocused) {
                spritesheet.update(delta);
            } else {
                spritesheet.reset();
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        /*
        g.setColor(Color.RED);
        g.drawRect(
                (int)(Config.scaledW * x),
                (int)(Config.scaledW * y),
                (int)(Config.scaledH * w),
                (int)(Config.scaledH * h));
        */
    }

    public int right() {
        return x + w;
    }

    public abstract void registerInput();

    public void setBackgroundImage(BufferedImage backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void setImageScaling(ImageScale scaleType) {
        this.scaleType = scaleType;
    }

    public void reset() {
        if(spritesheet != null) {
            spritesheet.reset();
        }
        isFocused = false;
        isPressed = false;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSpritesheet(SpriteSheet spritesheet) {
        this.spritesheet = spritesheet;
    }

    public void setPlaySound(boolean canPlaySound) {
        playSound = canPlaySound;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }
}