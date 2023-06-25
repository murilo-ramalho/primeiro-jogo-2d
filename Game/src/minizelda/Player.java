package minizelda;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle{
    public boolean right,up,down,left;
    public int spd = 4;
    public static List<Bullet> bullets = new ArrayList<>();
    public boolean shoot = false;
    public int curAnimatio = 0;
    public int curFrames = 0, taregetFrames = 15;
    public int di = 1;
    public Player(int x,int y){
    super(x,y,32,32);
    }
    public void Tick(){
        boolean moved = false;
        if (right && World.isFree(x+spd,y)){
            x+=spd;
            moved = true;
            di = 1;
        } else if (left && World.isFree(x-spd,y)) {
            x-=spd;
            moved = true;
            di = -1;
        }
        if (up && World.isFree(x,y-spd)){
            y-=spd;
            moved = true;

        } else if (down && World.isFree(x,y+spd)) {
            y+=spd;
            moved = true;
        }
        if (moved) {
            curFrames++;
            if (curFrames == taregetFrames) {
                curFrames = 0;
                curAnimatio++;
                if (curAnimatio == SpriteSheet.player_front.length) {
                    curAnimatio = 0;
                }
            }
        }
        if (shoot){
            shoot = false;
            bullets.add(new Bullet(x,y,di));
        }
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).tick();
        }

    }
    public void Render(Graphics g){
        g.drawImage(SpriteSheet.player_front[curAnimatio],x,y,32,32,null);
        for (int i = 0 ; i < bullets.size(); i++){
            bullets.get(i).render(g);
        }
    }
}
