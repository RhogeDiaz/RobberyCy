package main;

import entity.Entity;

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
}