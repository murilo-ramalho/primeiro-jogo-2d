package minizelda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Game extends Canvas implements Runnable, KeyListener {
    public static int width = 640, height = 480;
    public static int SCALE = 3;
    public static Player player;
    public List<Inimigo> inimigos = new ArrayList<>();

    public World world;

    public Game() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(width, height));
        new SpriteSheet();
        player = new Player(32,32);
        world = new World();
        inimigos.add(new Inimigo(464,120));
        inimigos.add(new Inimigo(264,120));

    }
    public void Tick(){
        player.Tick();
        for (int i = 0; i < inimigos.size(); i++){
            inimigos.get(i).Tick();
        }
    }
    public void Render(){
        BufferStrategy bs = this.getBufferStrategy();

        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.GREEN);
        g.fillRect(0,0,width*SCALE, height*SCALE);

        player.Render(g);
        world.Render(g);

        for (int i = 0; i < inimigos.size(); i++){
            inimigos.get(i).Render(g);
        }

        bs.show();
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle("mini zelda");
        frame.pack();
        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(game).start();
    }

    @Override
    public void run() {
        while (true){
            Tick();
            Render();
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_Z){
            player.shoot = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP){
            player.up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.left = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP){
            player.up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            player.down = false;
        }
    }
}