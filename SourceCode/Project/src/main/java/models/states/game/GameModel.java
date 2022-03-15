package models.states.game;

import models.camera.Camera;
import models.controls.ControlsModel;
import models.controls.game.GameMouseControls;
import models.data.PreferenceData;
import models.states.AEnvironment;
import props.objects.gameactors.TestActor;
import props.objects.gameactors.TestCharacter;
import props.prototypes.actor.AActor;
import props.prototypes.level.prop.ALevelProp;
import utils.controllers.AMouseController;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * TODO: Add description
 */
public class GameModel extends AEnvironment {

    private ControlsModel controlsViewModel;

    private LevelList levelModel;

    private final ArrayList<AActor> gameObjects = new ArrayList<>();

    /**
     * Initializes Game Model with values
     *
     * @param controlsViewModel - The controls View Model for the Game state
     * @param levelModel - The Level Model that contains all levels
     */
    public void init(ControlsModel controlsViewModel, LevelList levelModel) {

        setControlsViewModel(controlsViewModel);
        setLevelModel(levelModel);

        // Main Test Character
        gameObjects.add(new TestCharacter(
                controlsViewModel,
                200, 50,
                55, 70,
                0, 0,
                true
        ));
    }

    /**
     *
     * @param controlsViewModel - The controls View Model for the Game state
     */
    public void setControlsViewModel(ControlsModel controlsViewModel) {
        this.controlsViewModel = controlsViewModel;
    }

    /**
     *
     * @param levelModel - The Level Model that contains all levels
     */
    public void setLevelModel(LevelList levelModel) {
        this.levelModel = levelModel;
    }

    /**
     *
     * @param actor - The actor added to the list of current Game Objects
     */
    public void addGameObject(AActor actor) {
        gameObjects.add(actor);
    }

    /**
     *
     * @param delta - The ratio of current framerate against standard update frequency
     */
    public synchronized void update(float delta) {

        // ======
        // Performance testing by adding Numerous Objects via Mouse Input
        testAddingActors(delta);
        // ======

        // Check Game Object Collisions with Level Props
        checkCollisions(delta);
        // Update the Game Objects
        updateGameObjects(delta);

    }

    /**
     *
     * @param delta - The ratio of current framerate against standard update frequency
     */
    private void testAddingActors(float delta) {
        AMouseController mouseController = controlsViewModel.getMouseController();
        if (mouseController instanceof GameMouseControls) {

            GameMouseControls gmc = (GameMouseControls) mouseController;

            if (gmc.isLeftPressed()) {
                int count = (int) (10 / delta);
                if (count < 1) {
                    count = 1;
                }
                for (int i = 0; i < count; i++) {
                    addGameObject(
                            new TestActor(
                                    (float) ((-Camera.x /PreferenceData.scaledW) + (gmc.getPos()[0]/PreferenceData.scaledW)),
                                    (float) ((-Camera.y /PreferenceData.scaledW) + (gmc.getPos()[1]/PreferenceData.scaledH)),
                                    50f,
                                    50f,
                                    new Random().nextFloat(-5, 5),
                                    new Random().nextFloat(-5, 5),
                                    true
                            )
                    );
                }
            }
        }
    }

    /**
     *
     * @param delta - The ratio of current framerate against standard update frequency
     */
    public void updateGameObjects(float delta) {

        // Update all Actors
        for (AActor gameObject : gameObjects) {

            // Update TestActors
            if (gameObject instanceof TestActor a) {
                a.update(delta);
            }

            // Update Characters
            if (gameObject instanceof TestCharacter tc) {
                tc.control(delta);
                tc.update(delta);
            }

        }

    }

    /**
     * Check Prop collisions against Actors
     * @param delta - The ratio of current framerate against standard update frequency
     */
    private void checkCollisions(float delta) {
        for (ALevelProp p : levelModel.getLevelProps()) {
            for (AActor a : gameObjects) {
                p.hasCollision(a, delta);
            }
        }
    }

    /**
     *
     * @param g - Graphics object of the parent Canvas
     */
    public synchronized void renderGameObjects(Graphics g) {

        // Render Game Actors
        for (AActor gameObject : gameObjects) {
            if (gameObject instanceof TestCharacter o) {
                o.draw(g);
            }

            if (gameObject instanceof TestActor o) {
                o.draw(g);
            }
        }

        // Render Level Props
        levelModel.render(g);
    }
}