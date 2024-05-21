package main;
import background.TileManager;
import entity.Blinky;
import entity.Player;
import object.Point;

public class AssetSetter {
    GamePanel gp;
    TileManager tm;
    Player player;
    public AssetSetter(GamePanel gp, TileManager tm, Player player) {
        this.gp = gp;
        this.tm = tm;
        this.player = player;

    }
    public void setObject() {
        int k = 0;
        for(int i = 0; i < gp.maxScreenCol; i++) {
            for (int j = 0; j < gp.maxScreenRow; j++) {
                if (tm.mapTileNum[i][j] == 0) {
                    gp.obj[k] = new Point();
                    gp.obj[k].wordX = i * gp.tileSize;
                    gp.obj[k].wordY = j * gp.tileSize;
                    gp.obj[k].bounds.x = gp.obj[k].wordX;
                    gp.obj[k].bounds.y = gp.obj[k].wordY;
                    k++;
                    //System.out.println(k);
                }
            }
        }
    }
    public void setNPC(){
         gp.npc[0] = new Blinky(gp,player);
         gp.npc[0].x = gp.tileSize*13;
         gp.npc[0].y = gp.tileSize*13;

    }
}
