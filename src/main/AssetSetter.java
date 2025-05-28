package main;

import object.OBJ_Key; // Assuming this is your Key class extending SuperObject

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){

        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 6 * gp.tileSize; // Set X (column)
        gp.obj[0].worldY = 8 * gp.tileSize; // Set Y (row) - THIS IS THE CHANGE

        gp.obj[1] =  new OBJ_Key();
        gp.obj[1].worldX = 6 * gp.tileSize;  // Set X (column)
        gp.obj[1].worldY = 17 * gp.tileSize; // Set Y (row) - THIS IS THE CHANGE
    }
}