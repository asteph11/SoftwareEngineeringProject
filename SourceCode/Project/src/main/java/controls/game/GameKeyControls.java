package controls.game;

import models.prototypes.controls.AControls;
import models.prototypes.controls.AKeyController;

import java.awt.event.KeyEvent;

/**
 * Dedicated GameEnvironment Controls class that extends the AKeyController.
 */
public class GameKeyControls extends AKeyController {

    /**
     * <p>Creates the GameKeyControls and references the parent AControls</p>
     * @param controlsViewModel - the AControls parent model
     * @author Andrew Stephens
     */
    public GameKeyControls(AControls controlsViewModel) {
        super(controlsViewModel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                controlsModel.setAction(GameControls.Actions.ESCAPE, true);
            }
            case KeyEvent.VK_A -> {
                controlsModel.setDirectional(GameControls.Directionals.LEFT, true);
            }
            case KeyEvent.VK_D -> {
                controlsModel.setDirectional(GameControls.Directionals.RIGHT, true);
            }
            case KeyEvent.VK_W -> {
                controlsModel.setDirectional(GameControls.Directionals.UP, false);
            }
            case KeyEvent.VK_S -> {
                controlsModel.setDirectional(GameControls.Directionals.DOWN, false);
            }

            case KeyEvent.VK_SPACE -> {
                ((GameControls) controlsModel).setAbility(GameControls.Abilities.JUMP, true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> {
                controlsModel.setAction(GameControls.Actions.ESCAPE, false);
            }
            case KeyEvent.VK_A -> {
                controlsModel.setDirectional(GameControls.Directionals.LEFT, false);
            }
            case KeyEvent.VK_D -> {
                controlsModel.setDirectional(GameControls.Directionals.RIGHT, false);
            }
            case KeyEvent.VK_W -> {
                controlsModel.setDirectional(GameControls.Directionals.UP, false);
            }
            case KeyEvent.VK_S -> {
                controlsModel.setDirectional(GameControls.Directionals.DOWN, false);
            }
            case KeyEvent.VK_SPACE -> {
                ((GameControls) controlsModel).setAbility(GameControls.Abilities.JUMP, false);
            }
        }
    }

}
