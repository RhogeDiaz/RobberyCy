package entity;

import java.awt.image.BufferedImage;

abstract class Entity {
    public int x, y;
    public int speed;

    abstract void setDefaultValues(); 
    abstract void update();
    abstract void draw(java.awt.Graphics2D g2); 

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction; // Direction of the entity (up, down, left, right)

    public int spriteCounter = 0; // Counter for sprite animation
    public int spriteNum = 1; // Current sprite number (1 or 2)
}
