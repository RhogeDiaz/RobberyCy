package object;

import javax.imageio.ImageIO;
import java.io.IOException;

// In object/OBJ_Chest.java
public class OBJ_Chest extends SuperObject {
    public OBJ_Chest() {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/chest1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = false; // Keep as false if you don't want collision
    }
}