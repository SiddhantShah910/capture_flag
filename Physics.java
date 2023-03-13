/**
 * Name : Siddhant Shah
 * Student Number : 22204883
 */
import java.util.LinkedList;

public class Physics {

    final static int GRAVITYFORP = 3;
    final static double FRICTIONDFORP = 1.1;

    public static void gravityTick(char[][] map, Player p) {
        if (map[(p.returnY())/32 + 1][(p.returnX())/32] == '0') {
            p.setSpeed(p.returnVx(), p.returnVy() + GRAVITYFORP);
            p.setGround(false);
        } else {
            if (!p.onGround()) {
                p.setSpeed(p.returnVx(), 0);
                p.teleport(p.returnX(), p.returnY()/32 * 32);
                p.setGround(true);
            }
        }
    }

    public static void frictionTick(Player p) {
        if (p.onGround()) {
            p.setSpeed((int)(p.returnVx()/FRICTIONDFORP), p.returnVy());
        }
    }

    public static void collisionTick(char[][] map, Player p) {
        //check left
        if (map[(p.returnY()) / 32][(p.returnX()) / 32] != '0') {
            p.setSpeed(0, p.returnVy());
            p.teleport(p.returnX() / 32 * 32 + 32, p.returnY());
        }
        //check right
        if (map[(p.returnY()) / 32][(p.returnX() + 31) / 32] != '0') {
            p.setSpeed(0, 0);p.teleport(p.returnX() / 32 * 32, p.returnY() / 32 * 32);
        }
    }

    public static void bulletCollisionTick(char[][] map, LinkedList<Bullet> bll, Player[] players) {
        for (Bullet b : bll) {
            if (b.returnSpeed() > 0) {
                if (map[(b.returnY())/32][(b.returnX() + b.returnWidth() - 1)/32] != '0') {
                    bll.remove(b);
                    return;
                }
            } else {
                if (map[(b.returnY())/32][(b.returnX())/32] != '0') {
                    bll.remove(b);
                    return;
                }
            }
            for (Player p : players) {
                if ((b.returnX() + b.returnWidth()) > p.returnX()) {
                    if (b.returnX() < p.returnX() + 31) {
                        if (b.returnY() > p.returnY() && b.returnY() < (p.returnY() + 31)) {
                            bll.remove(b);
                            p.die();
                            return;
                        }
                    }
                }
            }
        }
    }

    public static void flagCollisionTick(Player p, Flag f, int held) {
        //check flag
        if (held == -1) {
            if (f.returnX()/32 == f.returnStartX()/32 && f.returnY()/32 == f.returnStartY()/32) {
                return;
            }
        }
        if (f.returnheld() == 0 && !p.isDead()) {
            if ((p.returnX() + 15) > f.returnX()) {
                if (p.returnX() + 15 < f.returnX() + 31) {
                    if (p.returnY() + 15 > f.returnY() && p.returnY() + 15 < (p.returnY() + 31)) {
                        f.setHeld(held);
                        f.setHeldBy(p);
                        p.setCarryingFlag(f);
                        return;
                    }
                }
            }
        }
    }
}
