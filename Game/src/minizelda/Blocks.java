package minizelda;

import java.awt.*;

public class Blocks extends Rectangle {
    public Blocks(int x, int y){
        super(x,y,32,32);
    }
    public void Render(Graphics g){
        g.drawImage(SpriteSheet.tilewall,x,y,32,32,null);
    }
}
