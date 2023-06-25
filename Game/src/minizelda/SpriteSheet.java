package minizelda;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    public static BufferedImage spritesheet;
    public static BufferedImage[] player_front;
    public static BufferedImage[] inimigo_front;
    public static BufferedImage tilewall;
    public SpriteSheet(){
        try {
            spritesheet = ImageIO.read(getClass().getResource("/spritesheet.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        player_front = new BufferedImage[2];
        player_front[0] = SpriteSheet.getSprite(1,11,16,16);
        player_front[1] = SpriteSheet.getSprite(19,11,16,16);

        inimigo_front = new BufferedImage[2];
        inimigo_front[0] = SpriteSheet.getSprite(164,11,16,16);
        inimigo_front[1] = SpriteSheet.getSprite(183,11,16,16);

        tilewall = SpriteSheet.getSprite(313,186,16,16);
    }
    public static BufferedImage getSprite(int x,int y, int witdh, int height){
        return spritesheet.getSubimage(x,y,witdh,height);
    }
}
