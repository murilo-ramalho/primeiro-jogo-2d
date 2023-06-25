package minizelda;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Inimigo extends Rectangle{
    public int right=1,up=0,down=0,left=0;
    public int spd = 2;
    public static List<Bullet> bullets = new ArrayList<>();
    public boolean shoot = false;
    public int curAnimatio = 0;
    public int curFrames = 0, taregetFrames = 15;
    public int di = 1;
    public Inimigo(int x,int y){
        super(x,y,32,32);
    }
    public void perseguirplayer(){
        Player p = Game.player;
        if (x<p.x && World.isFree(x+spd,y)){
            if(new Random().nextInt(100)<50)
                x+=spd;
        } else if (x>p.x && World.isFree(x-spd,y)){
            if(new Random().nextInt(100)<50)
                x-=spd;
        }
        if (y<p.y && World.isFree(x,y+spd)){
            if(new Random().nextInt(100)<50)
                y+=spd;
        } else if (y>p.y && World.isFree(x,y-spd)) {
            if(new Random().nextInt(100)<50)
                y-=spd;
        }
    }
    public void Tick(){
        boolean moved = true;
        perseguirplayer();

        if (shoot){
            shoot = false;
            bullets.add(new Bullet(x,y,di));
        }
        for (int i = 0; i > bullets.size(); i++) {
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
