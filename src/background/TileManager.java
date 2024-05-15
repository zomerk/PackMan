package background;

import main.GamePanel;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tiles;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[27];
        tiles[0] = new Tile();
        tiles[0].collision = false;
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadTileImage();

    }
    public void loadTileImage() {
        try{
            InputStream is = getClass().getResourceAsStream("/resources/images/map/map.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while(col < gp.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void getTileImage(){
        for(int i = 1; i <= 26; i++){
            try {
                tiles[i] = new Tile();
                tiles[i].image = ImageIO.read(getClass().getResourceAsStream("/resources/images/background/" + i + ".png"));

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public void draw(Graphics2D g2){
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];
            if(tileNum == 0 || tileNum == -1){

            }
            else {
                g2.drawImage(tiles[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            }
            col++;
            x+=gp.tileSize;
            if(col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y+=gp.tileSize;
            }
        }
    }
}
