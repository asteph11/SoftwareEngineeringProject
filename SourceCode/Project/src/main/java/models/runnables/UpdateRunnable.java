package models.runnables;

import models.prototypes.threading.ARunnable;
import models.prototypes.window.environments.AEnvironment;
import models.utils.config.ConfigData;

public class UpdateRunnable extends ARunnable {

    private AEnvironment environment;

    public void init(AEnvironment environment) {
        this.environment = environment;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime(), timer = System.currentTimeMillis();
        final short targetTicks = ConfigData.GAME_UPDATE_RATE;
        double ns = 1000000000 / (float) targetTicks, delta = 0;
        float updateRatio = 1;

                // GAME LOOP

        isRunning = true;
        while(isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            if(delta >= 1) {

                updateRatio = lastUpdates / (float) ConfigData.GAME_UPDATE_RATE;
                environment.update(updateRatio);
                updates++;

                delta--;
            }

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;

                lastUpdates = updates;
                updates = 0;

                System.out.println(System.currentTimeMillis() + " : " + lastUpdates);

            }

        }
    }
}
