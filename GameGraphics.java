/**
 * Name : Siddhant Shah
 * Student Number : 22204883
 */
import java.awt.*;
import javax.swing.JComponent;
import java.awt.event.*;
import java.util.LinkedList;

public class GameGraphics extends JComponent{
    Player p[] = {JavaGraphics.p0, JavaGraphics.p1};
    int c_player = 0;

    char[][] map = JavaGraphics.map;
    static int sh = JavaGraphics.screen_h;
    static int sw = JavaGraphics.screen_w;
    static int bh = JavaGraphics.block_h;
    static int nbh = JavaGraphics.numblock_h;
    static int nbw = JavaGraphics.numblock_w;
    static int mlx = JavaGraphics.maplength_x;
    static int mly = JavaGraphics.maplength_y;

    static Flag f[] = {JavaGraphics.f1, JavaGraphics.f2};
    static LinkedList<Bullet> bll = new LinkedList<Bullet>();

    public GameGraphics(int i){
        c_player = i;

        KeyListener listener = new KeyHandler();
        addKeyListener(listener);
        setFocusable(true);
    }

    public void paint(Graphics g) {

        MapLoader.drawMap(g, map, p, c_player);
        Scoreboard.drawScore(g, c_player);

        if (c_player == 0) {
            p[1].drawPlayer(g, p[0]);
        } else {
            p[0].drawPlayer(g, p[1]);
        }
        if (!p[c_player].isDead()) {
            if (p[c_player].returnLastDirection() == 1) {
                g.drawImage(p[c_player].returnImages()[1], sw / 4, sh / 3 * 2, 32, 32, null);
            } else {
                g.drawImage(p[c_player].returnImages()[0], sw / 4, sh / 3 * 2, 32, 32, null);
            }
            g.drawLine(0, 0, 0, sh);
            g.drawLine(sw / 2 - 1, 0, sw / 2 - 1, sh);
        }
        for (int i = 0; i < 2; i++) {
            Physics.gravityTick(map, p[i]);
            Physics.frictionTick(p[i]);
            int[] controls = p[i].returnControls();
            for (int key : KeyHandler.keysPressed) {
                if (key == 27) {
                    //Esc, exit game
                    System.exit(0);
                } else {
                    //Player Controls
                    if (p[i].returnRespawnCool() == 0) {
                        if (key == controls[0]) {   //MOVE_UP
                         
                            if (p[i].onGround()) {
                                p[i].setSpeed(p[i].returnVx(), -100);
                            }
                        } else if (key == controls[2]) { //MOVE_LEFT
                            p[i].setSpeed(-30, p[i].returnVy());
                        } else if (key == controls[3]) { //MOVE_RIGHT
                            p[i].setSpeed(30, p[i].returnVy());
                        } else if (key == controls[4]) {  //SHOOT                         
                            if (p[i].returnShootCool() == 0) {
                                if (p[i].returnLastDirection() == 1) {
                                    bll.add(new Bullet(p[i].returnX() + 31, p[i].returnY() + 13, 5));
                                } else {
                                    bll.add(new Bullet(p[i].returnX() - 9, p[i].returnY() + 13, -5));
                                }
                                p[i].setShootCool(60);
                            }
                        }
                    }
                }
            }
            p[i].tickPlayer();

            Physics.collisionTick(map, p[i]);

            for (Bullet b : bll) {
                b.drawBullet(g, p[c_player]);
            }
            Physics.bulletCollisionTick(map, bll, p);

            for (Flag flag : f) {
                flag.drawFlag(g, p[c_player]);
            }
            Physics.flagCollisionTick(p[i], (i == 0) ? f[1]:f[0], 1);
            Physics.flagCollisionTick(p[i], (i == 0) ? f[0]:f[1], -1);

            p[i].move();
        }

        try {
            Thread.sleep(Main.GAMESPEED);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        repaint();
    }
}
