package minizelda;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {
    public static List<Blocks> blocos = new ArrayList<Blocks>();
    public World(){
        for (int xx = 0; xx < 19; xx++){
            blocos.add(new Blocks(xx*32,0));
        }
        for (int xx = 0; xx < 19; xx++){
            blocos.add(new Blocks(xx*32,480-32));
        }
        for (int yy = 0; yy < 15; yy++){
            blocos.add(new Blocks(0,yy*32));
        }
        for (int yy = 0; yy < 15; yy++){
            blocos.add(new Blocks(640-32,yy*32));
        }

        for (int cc = 0;cc < 5;cc++){
            blocos.add(new Blocks(250-32,cc*32+100));
            blocos.add(new Blocks(cc*32+150,200-32));
        }
    }
    public static boolean isFree(int x, int y){
        for (int i = 0; i < blocos.size(); i++){
            Blocks bloocoAtual = blocos.get(i);
            if (bloocoAtual.intersects(new Rectangle(x,y,32,32))){
                return false;
            }
        }
        return true;
    }
    public void Render(Graphics g){
        for (int i = 0; i< blocos.size();i++){
            blocos.get(i).Render(g);
        }
    }
}
