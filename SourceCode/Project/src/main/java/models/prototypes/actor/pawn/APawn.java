package models.prototypes.actor.pawn;

import models.prototypes.actor.AActor;
import models.utils.drawables.IDrawable;
import models.utils.updates.IUpdatable;

import java.awt.*;

/**
 * A Simple Game Object which carries ability to move.
 * It is inherited by classes whos objects must physically move within the world.
 */
public abstract class APawn extends AActor implements IDrawable, IUpdatable {

    protected APawn(float x, float y, float w, float h, float vx, float vy, boolean hasGravity) {
        super(x, y, w, h, vx, vy, hasGravity);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        x += vX;
        y += vY;

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }

}