package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_FinalDoor extends SuperObject {

    public OBJ_FinalDoor() {

        name = "FinalDoor";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        collision = true;
    }
}
