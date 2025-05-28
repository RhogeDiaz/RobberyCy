package entity;

import java.awt.*;

public class Obstacle extends Entity {
    private int startX, startY;
    private int distance; // in blocks
    private int traveled = 0;
    private int directionSign = 1; // 1 or -1
    private boolean horizontal; // true: horizontal, false: vertical
    private int blockSize;

    public Obstacle(int x, int y, int distance, boolean horizontal, int blockSize) {
        this.worldX = x;
        this.worldY = y;
        this.startX = x;
        this.startY = y;
        this.distance = distance;
        this.horizontal = horizontal;
        this.blockSize = blockSize;
        this.speed = 2; // pixels per update, adjust as needed
        this.direction = "right";
        this.solidArea = new Rectangle(0, 0, blockSize, blockSize);
        this.solidAreaDefaultX = 0;
        this.solidAreaDefaultY = 0;
    }

    @Override
    public void setDefaultValues() {
        this.worldX = startX;
        this.worldY = startY;
        this.traveled = 0;
        this.directionSign = 1;
    }

    @Override
    public void update() {
        int move = speed * directionSign;
        if (horizontal) {
            worldX += move;
            traveled += Math.abs(move);
            if (traveled >= distance * blockSize) {
                directionSign *= -1;
                traveled = 0;
            }
        } else {
            worldY += move;
            traveled += Math.abs(move);
            if (traveled >= distance * blockSize) {
                directionSign *= -1;
                traveled = 0;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillRect(worldX, worldY, blockSize, blockSize);
    }
}
