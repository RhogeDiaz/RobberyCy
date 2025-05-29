// In AssetSetter.java
package main;

import object.*;
import entity.Obstacle;

import java.util.ArrayList; // Import ArrayList

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    // New method to set objects for all maps
    // In AssetSetter.java
    public void setObjectsForAllMaps(){

        for(ArrayList<SuperObject> list : gp.mapObjects.values()){
            list.clear();
        }

        // --- Objects for MAP1 ---
        ArrayList<SuperObject> map1Objs = gp.mapObjects.get("map1");
        OBJ_Chest Chest1_map1 = new OBJ_Chest();
        Chest1_map1.worldX = 6 * gp.tileSize;
        Chest1_map1.worldY = 8 * gp.tileSize;
        map1Objs.add(Chest1_map1);

        OBJ_Chest Chest2_map1 = new OBJ_Chest();
        Chest2_map1.worldX = 6 * gp.tileSize;
        Chest2_map1.worldY = 17 * gp.tileSize;
        map1Objs.add(Chest2_map1);

        OBJ_Chest Chest3_map1 = new OBJ_Chest();
        Chest3_map1.worldX = 19 * gp.tileSize;
        Chest3_map1.worldY = 12 * gp.tileSize;
        map1Objs.add(Chest3_map1);

        OBJ_Door Door1_map1 = new OBJ_Door();
        Door1_map1.worldX = 31 * gp.tileSize;
        Door1_map1.worldY = 12 * gp.tileSize;
        map1Objs.add(Door1_map1);

        OBJ_Door_Blue Door2_map1 = new OBJ_Door_Blue();
        Door2_map1.worldX = 27 * gp.tileSize;
        Door2_map1.worldY = 10 * gp.tileSize;
        map1Objs.add(Door2_map1);

        OBJ_Key Key1_map1 = new OBJ_Key();
        Key1_map1.worldX = 22 * gp.tileSize;
        Key1_map1.worldY = 6 * gp.tileSize;
        map1Objs.add(Key1_map1);

        ArrayList<SuperObject> map2Objs = gp.mapObjects.get("map2");
        OBJ_Chest Chest1_map2 = new OBJ_Chest();
        Chest1_map2.worldX = 10 * gp.tileSize;
        Chest1_map2.worldY = 6 * gp.tileSize;
        map2Objs.add(Chest1_map2);

        OBJ_Chest Chest2_map2 = new OBJ_Chest();
        Chest2_map2.worldX = 10 * gp.tileSize;
        Chest2_map2.worldY = 17 * gp.tileSize;
        map2Objs.add(Chest2_map2);

        OBJ_Chest Chest3_map2 = new OBJ_Chest();
        Chest3_map2.worldX = 25 * gp.tileSize;
        Chest3_map2.worldY = 13 * gp.tileSize;
        map2Objs.add(Chest3_map2);

        OBJ_Chest Chest4_map2 = new OBJ_Chest();
        Chest4_map2.worldX = 20 * gp.tileSize;
        Chest4_map2.worldY = 5 * gp.tileSize;
        map2Objs.add(Chest4_map2);

        OBJ_Door Door1_map2 = new OBJ_Door();
        Door1_map2.worldX = 31 * gp.tileSize;
        Door1_map2.worldY = 12 * gp.tileSize;
        map2Objs.add(Door1_map2);

        OBJ_Door_Blue Door2_map2 = new OBJ_Door_Blue();
        Door2_map2.worldX = 10 * gp.tileSize;
        Door2_map2.worldY = 10 * gp.tileSize;
        map2Objs.add(Door2_map2);

        OBJ_Door_Blue Door3_map2 = new OBJ_Door_Blue();
        Door3_map2.worldX = 6 * gp.tileSize;
        Door3_map2.worldY = 19 * gp.tileSize;
        map2Objs.add(Door3_map2);

        OBJ_Door_Blue Door4_map2 = new OBJ_Door_Blue();
        Door4_map2.worldX = 23 * gp.tileSize;
        Door4_map2.worldY = 7 * gp.tileSize;
        map2Objs.add(Door4_map2);

        OBJ_Door_Blue Door5_map2 = new OBJ_Door_Blue();
        Door5_map2.worldX = 21 * gp.tileSize;
        Door5_map2.worldY = 11 * gp.tileSize;
        map2Objs.add(Door5_map2);

        OBJ_Key Key1_map2 = new OBJ_Key();
        Key1_map2.worldX = 22 * gp.tileSize;
        Key1_map2.worldY = 6 * gp.tileSize;
        map2Objs.add(Key1_map2);

        OBJ_Key Key2_map2 = new OBJ_Key();
        Key2_map2.worldX = 2 * gp.tileSize;
        Key2_map2.worldY = 18 * gp.tileSize;
        map2Objs.add(Key2_map2);

        OBJ_Key Key3_map2 = new OBJ_Key();
        Key3_map2.worldX = 7 * gp.tileSize;
        Key3_map2.worldY = 17 * gp.tileSize;
        map2Objs.add(Key3_map2);

        OBJ_Key Key4_map2 = new OBJ_Key();
        Key4_map2.worldX = 6 * gp.tileSize;
        Key4_map2.worldY = 8 * gp.tileSize;
        map2Objs.add(Key4_map2);

        ArrayList<SuperObject> map3Objs = gp.mapObjects.get("map3");
        OBJ_Chest Chest1_map3 = new OBJ_Chest();
        Chest1_map3.worldX = 10 * gp.tileSize;
        Chest1_map3.worldY = 6 * gp.tileSize;
        map3Objs.add(Chest1_map3);

        OBJ_Chest Chest2_map3 = new OBJ_Chest();
        Chest2_map3.worldX = 10 * gp.tileSize;
        Chest2_map3.worldY = 2 * gp.tileSize;
        map3Objs.add(Chest2_map3);

        OBJ_Chest Chest3_map3 = new OBJ_Chest();
        Chest3_map3.worldX = 25 * gp.tileSize;
        Chest3_map3.worldY = 6 * gp.tileSize;
        map3Objs.add(Chest3_map3);

        OBJ_Chest Chest4_map3 = new OBJ_Chest();
        Chest4_map3.worldX = 25 * gp.tileSize;
        Chest4_map3.worldY = 22 * gp.tileSize;
        map3Objs.add(Chest4_map3);

        OBJ_Door Door1_map3 = new OBJ_Door();
        Door1_map3.worldX = 31 * gp.tileSize;
        Door1_map3.worldY = 12 * gp.tileSize;
        map3Objs.add(Door1_map3);

        OBJ_Door_Blue Door2_map3 = new OBJ_Door_Blue();
        Door2_map3.worldX = 13 * gp.tileSize;
        Door2_map3.worldY = 2 * gp.tileSize;
        map3Objs.add(Door2_map3);

        OBJ_Door_Blue Door3_map3 = new OBJ_Door_Blue();
        Door3_map3.worldX = 25 * gp.tileSize;
        Door3_map3.worldY = 12 * gp.tileSize;
        map3Objs.add(Door3_map3);

        OBJ_Door_Blue Door4_map3 = new OBJ_Door_Blue();
        Door4_map3.worldX = 25 * gp.tileSize;
        Door4_map3.worldY = 20 * gp.tileSize;
        map3Objs.add(Door4_map3);

        OBJ_Door_Blue Door5_map3 = new OBJ_Door_Blue();
        Door5_map3.worldX = 19 * gp.tileSize;
        Door5_map3.worldY = 18 * gp.tileSize;
        map3Objs.add(Door5_map3);

        OBJ_Door_Blue Door6_map3 = new OBJ_Door_Blue();
        Door6_map3.worldX = 23 * gp.tileSize;
        Door6_map3.worldY = 7 * gp.tileSize;
        map3Objs.add(Door6_map3);

        OBJ_Key Key1_map3 = new OBJ_Key();
        Key1_map3.worldX = 21 * gp.tileSize;
        Key1_map3.worldY = 9 * gp.tileSize;
        map3Objs.add(Key1_map3);

        OBJ_Key Key2_map3 = new OBJ_Key();
        Key2_map3.worldX = 21 * gp.tileSize;
        Key2_map3.worldY = 22 * gp.tileSize;
        map3Objs.add(Key2_map3);

        OBJ_Key Key3_map3 = new OBJ_Key();
        Key3_map3.worldX = 7 * gp.tileSize;
        Key3_map3.worldY = 13 * gp.tileSize;
        map3Objs.add(Key3_map3);

        OBJ_Key Key4_map3 = new OBJ_Key();
        Key4_map3.worldX = 9 * gp.tileSize;
        Key4_map3.worldY = 2 * gp.tileSize;
        map3Objs.add(Key4_map3);

        OBJ_Key Key5_map3 = new OBJ_Key();
        Key5_map3.worldX = 22 * gp.tileSize;
        Key5_map3.worldY = 6 * gp.tileSize;
        map3Objs.add(Key5_map3);
    }

    public void setObstaclesForAllMaps() {
        for (ArrayList<Obstacle> list : gp.mapObstacles.values()) {
            list.clear();
        }
        // --- Obstacles for MAP1 ---
        ArrayList<Obstacle> map1Obs = gp.mapObstacles.get("map1");
        map1Obs.add(new Obstacle(10 * gp.tileSize, 7 * gp.tileSize, 2, false, gp.tileSize)); // Example
        map1Obs.add(new Obstacle(11 * gp.tileSize, 10 * gp.tileSize, 2, true, gp.tileSize)); // Example
        map1Obs.add(new Obstacle(10 * gp.tileSize, 11 * gp.tileSize, 2, false, gp.tileSize)); // Example
        map1Obs.add(new Obstacle(18 * gp.tileSize, 5 * gp.tileSize, 2, false, gp.tileSize)); // Example

        // --- Obstacles for MAP2 ---
        ArrayList<Obstacle> map2Obs = gp.mapObstacles.get("map2");
        map2Obs.add(new Obstacle(11 * gp.tileSize, 9 * gp.tileSize, 3, false, gp.tileSize)); // Example
        map2Obs.add(new Obstacle(4 * gp.tileSize, 20 * gp.tileSize, 7, true, gp.tileSize)); // Example
        map2Obs.add(new Obstacle(19 * gp.tileSize, 8 * gp.tileSize, 8, true, gp.tileSize)); // Example

        // --- Obstacles for MAP3 ---
        ArrayList<Obstacle> map3Obs = gp.mapObstacles.get("map3");
        map3Obs.add(new Obstacle(21 * gp.tileSize, 17 * gp.tileSize, 5, false, gp.tileSize)); // Example
        map3Obs.add(new Obstacle(23 * gp.tileSize, 8 * gp.tileSize, 4, false, gp.tileSize)); // Example
        map3Obs.add(new Obstacle(7 * gp.tileSize, 5 * gp.tileSize, 6, false, gp.tileSize)); // Example
    }
}