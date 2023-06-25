package minizelda;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Inimigo extends Rectangle{
    public int right=1,up=0,down=0,left=0;
    public int spd = 4;
    public static List<Bullet> bullets = new ArrayList<>();
    public boolean shoot = false;
    public int curAnimatio = 0;
    public int curFrames = 0, taregetFrames = 15;
    public int di = 1;
    public Inimigo(int x,int y){
        super(x,y,32,32);
    }
    public void Tick(){
        boolean moved = true;
        if (right==1){
            x++;
        }

        if (shoot){
            shoot = false;
            bullets.add(new Bullet(x,y,di));
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).tick();
        }
        if (moved) {
            curFrames++;
            if (curFrames == taregetFrames) {
                curFrames = 0;
                curAnimatio++;
                if (curAnimatio == SpriteSheet.inimigo_front.length) {
                    curAnimatio = 0;
                }
            }
        }

    }
    public void Render(Graphics g){
        g.drawImage(SpriteSheet.inimigo_front[curAnimatio],x,y,32,32,null);
        for (int i = 0 ; i < bullets.size(); i++){
            bullets.get(i).render(g);
        }
    }
}
