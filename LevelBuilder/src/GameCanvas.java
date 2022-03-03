package graphics.ui;

import viewmodels.game.GameViewModel;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {

    private GameViewModel gameModel;

    private boolean isUpdated = true;

    public GameCanvas() {
        // TODO: Nothing yet
    }

    public void init(GameViewModel gameModel) {
        this.gameModel = gameModel;
    }

    public void update(double updateRate) {
        //System.out.println("Updating");
        gameModel.update(updateRate);
        isUpdated = true;
    }

    public void render() {
        //System.out.println("Rendering");
        repaint();
        isUpdated = false;
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fill background
        g2d.setColor(new Color(255, 255, 255));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw test objects
        gameModel.renderGameObjects(g);

    }

    public boolean isRenderReady() {
        return isUpdated;
    }
}