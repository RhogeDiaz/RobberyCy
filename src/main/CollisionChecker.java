package main;

import entity.Entity;
import object.SuperObject;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x +  entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y +  entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX /gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        int maxColIndex = gp.maxScreenCol - 1;
        int maxRowIndex = gp.maxScreenRow - 1;

        switch(entity.getDirection()) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / gp.tileSize;

                // Clamping to ensure indices are within map bounds
                entityLeftCol = Math.min(Math.max(entityLeftCol, 0), maxColIndex);
                entityRightCol = Math.min(Math.max(entityRightCol, 0), maxColIndex);
                entityTopRow = Math.min(Math.max(entityTopRow, 0), maxRowIndex);

                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gp.tileSize;

                // Clamping to ensure indices are within map bounds
                entityLeftCol = Math.min(Math.max(entityLeftCol, 0), maxColIndex);
                entityRightCol = Math.min(Math.max(entityRightCol, 0), maxColIndex);
                entityBottomRow = Math.min(Math.max(entityBottomRow, 0), maxRowIndex);

                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.getSpeed()) / gp.tileSize;

                // Clamping to ensure indices are within map bounds
                entityLeftCol = Math.min(Math.max(entityLeftCol, 0), maxColIndex);
                entityTopRow = Math.min(Math.max(entityTopRow, 0), maxRowIndex);
                entityBottomRow = Math.min(Math.max(entityBottomRow, 0), maxRowIndex);

                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.getSpeed()) / gp.tileSize;

                // Clamping to ensure indices are within map bounds
                entityRightCol = Math.min(Math.max(entityRightCol, 0), maxColIndex);
                entityTopRow = Math.min(Math.max(entityTopRow, 0), maxRowIndex);
                entityBottomRow = Math.min(Math.max(entityBottomRow, 0), maxRowIndex);

                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            default:
        }
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.currentMapObjects.size(); i++) {
            SuperObject obj = gp.currentMapObjects.get(i);

            if (obj != null) {
                entity.solidArea.x = entity.worldX + entity.solidAreaDefaultX;
                entity.solidArea.y = entity.worldY + entity.solidAreaDefaultY;
                obj.solidArea.x = obj.worldX + obj.solidAreaDefaultX;
                obj.solidArea.y = obj.worldY + obj.solidAreaDefaultY;

                // Simulate entity's next position before checking collision
                // This is the correct place to apply directional movement for collision prediction
                int entitySolidAreaXtemp = entity.solidArea.x; // Store current position before simulating
                int entitySolidAreaYtemp = entity.solidArea.y;

                switch (entity.getDirection()) {
                    case "up":    entity.solidArea.y -= entity.getSpeed(); break;
                    case "down":  entity.solidArea.y += entity.getSpeed(); break;
                    case "left":  entity.solidArea.x -= entity.getSpeed(); break;
                    case "right": entity.solidArea.x += entity.getSpeed(); break;
                }

                if (entity.solidArea.intersects(obj.solidArea)) {
                    // IMPORTANT: Only set collisionOn to true IF the object is intended to block movement
                    if (obj.collision == true) { // <-- This condition is key!
                        entity.collisionOn = true;
                    }

                    if (player == true) {
                        index = i; // Still return index for interaction even if obj.collision is false
                    }
                }

                // Reset entity's solid area to its *original* position for the next check,
                // or if it was modified for a different object check.
                // Also reset obj's solid area
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                obj.solidArea.x = obj.solidAreaDefaultX;
                obj.solidArea.y = obj.solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkObstacleCollision(Entity entity) {
        for (entity.Obstacle obs : gp.currentMapObstacles) {
            // Set solid areas to world position
            entity.solidArea.x = entity.worldX + entity.solidAreaDefaultX;
            entity.solidArea.y = entity.worldY + entity.solidAreaDefaultY;
            obs.solidArea.x = obs.worldX + obs.solidAreaDefaultX;
            obs.solidArea.y = obs.worldY + obs.solidAreaDefaultY;

            if (entity.solidArea.intersects(obs.solidArea)) {
                // Reset solid areas before returning
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                obs.solidArea.x = obs.solidAreaDefaultX;
                obs.solidArea.y = obs.solidAreaDefaultY;
                return true;
            }

            // Reset solid areas for next check
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            obs.solidArea.x = obs.solidAreaDefaultX;
            obs.solidArea.y = obs.solidAreaDefaultY;
        }
        return false;
    }
}