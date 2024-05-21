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
                if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                    e.collisionOn = true;
                }
                else{
                    e.collisionOn = false;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWordY + e.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                    e.collisionOn = true;
                }
                else{
                    e.collisionOn = false;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWordX - e.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                    e.collisionOn = true;
                }
                else{
                    e.collisionOn = false;
                }
                break;
            case "right":
                entityRightCol = (entityRightWordX + e.speed) / gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision) {
                    e.collisionOn = true;
                }
                else{
                    e.collisionOn = false;
                }
                break;
        }
    }
    public int checkObject(Entity e, boolean player) {
        int index = 999;
        e.bounds.x = e.x;
        e.bounds.y = e.y;
        for(int i=0; i < gp.obj.length; i++){
            if(gp.obj[i] != null){
                //System.out.println(e.bounds.intersects(gp.obj[i].bounds));
                if(e.bounds.intersects(gp.obj[i].bounds)){
                   // System.out.println(gp.obj[i].bounds.x + " " + gp.obj[i].bounds.y);
                    if(gp.obj[i].collision){
                        e.collisionOn = false;
                    }
                    if(player) {
                        index = i;
                    }
                }
            }
        }
        e.bounds.x = e.boundX;
        e.bounds.y = e.boundsY;
        return index;

    }
}
