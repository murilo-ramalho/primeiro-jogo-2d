package minizelda;

import java.awt.*;

public class Bullet extends Rectangle {
    public int Dir = 1;
    public int speed = 8;
    public int frames = 0;
    public Bullet(int x, int y,int dir){
        super(x+16,y+16,10,10);
        this.Dir = Dir;
    }
    public void tick(){
        x+=speed*Dir;
        frames++;
        if (frames==120){
            Player.bullets.remove(this);
            return;
        }
    }
    public void render(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval(x,y,width,height);
    }
}
