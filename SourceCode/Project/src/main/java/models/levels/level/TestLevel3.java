package models.levels.level;

import models.actors.gameactors.props.platforms.PlatformProp;
import models.actors.gameactors.props.triggers.collectibles.key.LevelKey;
import models.actors.gameactors.props.triggers.interactibles.DoorTrigger;
import models.environments.game.GameEnvironment;
import models.prototypes.level.ALevel;
import models.prototypes.level.prop.trigger.prop.APropTrigger;


public class TestLevel3 extends ALevel {

    public TestLevel3(GameEnvironment gameModel) {
        super(gameModel);

        setStartOrigin(200, 50);

        build();

    }

    @Override
    public void build() {

        // Floor
        addProp(new PlatformProp(0, 980, 10000, 100, 0, 0, false));

        for(int i = 0; i < 10; i++) {
            addProp(new PlatformProp(i*20, 500+(-5*i*.8f), 20, 20, 0, 0, false));
        }

        // Keys
        addProp(new LevelKey(gameEnvironment, 1600, 930, 100, 50, 0, 0));
        addProp(new LevelKey(gameEnvironment, 2500, 930, 100, 50, 0, 0));
        addProp(new LevelKey(gameEnvironment, 500, 930, 100, 50, 0, 0));

        // Door
        door = new DoorTrigger(gameEnvironment, 2000, 830, 50, 100,
                0, 0, 1, false, false);
        addProp(door);

        // Door Listener
        addProp(new APropTrigger(gameEnvironment, 1800, 700, 450, 300,
                0, 0, 1,false, false) {
            @Override
            public void doAction() {
                //reactProp.onReact();
                door.onReact();
            }

        });

        super.build();
    }

    @Override
    public void reset() {
        super.reset();
    }
}