package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile(Entity e) {
        int entityLeftWordX = e.x + e.bounds.x;
        int entityRightWordX = e.x + e.bounds.x + e.bounds.width;
        int entityTopWordY = e.y + e.bounds.y;
        int entityBottomWordY = e.y + e.bounds.y + e.bounds.height;
        int entityLeftCol = entityLeftWordX/gp.tileSize;
        int entityRightCol = entityRightWordX/gp.tileSize;
        int entityTopRow = entityTopWordY/gp.tileSize;
        int entityBottomRow = entityBottomWordY/gp.tileSize;

        int tileNum1, tileNum2;
        switch (e.direction) {
            case "up":
                entityTopRow = (entityTopWordY - e.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true) {
                    e.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityTopWordY + e.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true) {
                    e.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWordX - e.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true) {
                    e.collisionOn = true;
                }
                break;
            case "right":
                entityLeftCol = (entityRightWordX + e.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tiles[tileNum1].collision == true || gp.tileManager.tiles[tileNum2].collision == true) {
                    e.collisionOn = true;
                }
                break;
        }
    }
}
