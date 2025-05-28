package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door_Blue extends SuperObject {

    public OBJ_Door_Blue() {

        name = "Door Blue";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door1_1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
