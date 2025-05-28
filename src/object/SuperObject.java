package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision;
    public int worldX, worldY; // These are directly screen/pixel coordinates for fixed map

    public void draw(Graphics2D g2 , GamePanel gp) {
        g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
    }
}