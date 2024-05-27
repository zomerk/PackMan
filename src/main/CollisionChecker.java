package main;

import entity.Entity;

import java.awt.*;

/**
 * The CollisionChecker class is responsible for checking collisions between entities
 * and other objects within the game world.
 */
public class CollisionChecker {
    GamePanel gp;
    /**
     * Constructor for the CollisionChecker class.
     * @param gp The GamePanel instance.
     */
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    /**
     * Checks for collisions between an entity and tiles based on its current direction.
     * @param e The entity to check for collisions.
     */
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
    /**
     * Checks for collisions between an entity and objects.
     * @param e The entity to check for collisions.
     * @param player A boolean value indicating whether the entity is a player.
     * @return The index of the collided object.
     */
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
    /**
     * Checks for collisions between an entity and other entities.
     * @param e The entity to check for collisions.
     * @param target An array of entities to check against.
     * @return The index of the collided entity.
     */
    public int checkEntity(Entity e, Entity[] target) {
        int index = 999;
        e.bounds.x = e.x;
        e.bounds.y = e.y;
        for(int i=0; i < target.length; i++){
            if(target[i] != null){
                target[i].bounds.x = target[i].x;
                target[i].bounds.y = target[i].y;
                if(e.bounds.intersects(target[i].bounds)){
                    //e.collisionOn = true;
                    target[i].dead = true;
                    index = i;
                }
                target[i].bounds = new Rectangle(2,2,26,26);
            }
        }
        e.bounds.x = e.boundX;
        e.bounds.y = e.boundsY;
        return index;
    }
    /**
     * Checks for collisions between an entity and hearts.
     * @param e The entity to check for collisions.
     * @param player A boolean value indicating whether the entity is a player.
     * @return The index of the collided heart.
     */
    public int checkHeart(Entity e, boolean player) {
        int index = 999;
        e.bounds.x = e.x;
        e.bounds.y = e.y;
        for(int i=0; i < gp.heart.length; i++){
            if(gp.heart[i] != null){
                //System.out.println(e.bounds.intersects(gp.obj[i].bounds));
                if(e.bounds.intersects(gp.heart[i].bounds)){
                    // System.out.println(gp.obj[i].bounds.x + " " + gp.obj[i].bounds.y);
                    if(gp.heart[i].collision){
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
