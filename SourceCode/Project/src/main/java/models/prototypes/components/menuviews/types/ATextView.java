package models.prototypes.components.menuviews.types;

import models.prototypes.components.menuviews.AMenuComponent;
import models.prototypes.environments.menu.AMenuEnvironment;
import models.utils.config.Config;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * <p>The ATextView component simply should draw text within the component.</p>
 * @author Andrew Stephens
 */
public abstract class ATextView extends AMenuComponent implements IDrawable, IUpdatable {

    /**
     * <p>Initializes the AButtonView</p>
     * @param parentMenuModel The AMenuEnvironment containing the Menu that contains this component.
     * @param x The horizontal position of the component
     * @param y The vertical position of the component
     * @param w The width of the component
     * @param h The height of the component
     * @param text The text to be drawn to this text view
     */
    public ATextView(AMenuEnvironment parentMenuModel, int x, int y, int w, int h, String text) {
        super(parentMenuModel, x, y, w, h, text);
    }

    public ATextView(AMenuEnvironment parentEnvironment,
                     int x, int y, int w, int h, String text,
                     Color backgroundColor) {
        super(parentEnvironment, x, y, w, h, text);

        setBackgroundColor(backgroundColor);
    }

    public ATextView(AMenuEnvironment parentEnvironment,
                     int x, int y, int w, int h, String text,
                     Color foregroundColor, Color backgroundColor) {
        super(parentEnvironment, x, y, w, h, text);

        setForegroundColor(foregroundColor);
        setBackgroundColor(backgroundColor);
    }

    @Override
    public void playSound() {
        // STUB
    }

    /**
     * <p>Shorts the input registration.</p>
     */
    @Override
    public void registerInput() {
        // STUB
    }

    @Override
    public void reset() {
        playSound = true;
        super.reset();
    }

    @Override
    public boolean onClick(float x, float y) {
        return false;
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);

        float sW = Config.scaledW, sH = Config.scaledH;

        g.setColor(backgroundColor);
        g.fillRect((int)(x*sW), (int)(y*sH), (int)(w*sW), (int)(h*sH));

        g.setColor(foregroundColor);
        int strHeight = h;
        g.setFont(
                getParentEnvironment().getResources().getFont("bahnschrift")
                        .deriveFont(AffineTransform.getScaleInstance(.8, 1))
                        .deriveFont(Font.BOLD, strHeight * sH));

        int strWidth = g.getFontMetrics().stringWidth(text.toUpperCase());
        while(strWidth > w * sW) {
            strHeight--;
            g.setFont(
                    getParentEnvironment().getResources().getFont("bahnschrift")
                            .deriveFont(AffineTransform.getScaleInstance(.8, 1))
                            .deriveFont(Font.BOLD, strHeight * sW));

            strWidth = g.getFontMetrics().stringWidth(text);
        }
        int strHeightDiff = g.getFontMetrics().getAscent();
        g.drawString(
                text,
                (int)((x * sW) + (w * sW * .5) - (strWidth * .5)),
                (int)((y * sH) + strHeightDiff));
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

}
