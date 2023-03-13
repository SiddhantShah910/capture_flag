/**
 * Name : Siddhant Shah
 * Student Number : 22204883
 */
import java.awt.*;

public class Flag {
    private int x, y;
    private int startX, startY;
    private int endX, endY;
    private int held = 0;
    private Player heldBypl;
    public Flag(int x, int y, int endX, int endY) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.endX = endX;
        this.endY = endY;
    }

    public void setHeld(int held) { // Sets if the flag is being held
        this.held = held;
    }

    public void setHeldBy(Player p) { //Sets the player who is holding the flag
        this.heldBypl = p;
    }

    public int returnX() { //Returns the X Coordinate
        return x;
    }

    public int returnY() {
        return y;
    }

    public int returnStartX() {
        return startX;
    }

    public int returnStartY() {
        return startY;
    }

    public int returnheld() {
        return held;
    }

    public void drawFlag(Graphics g, Player ref) {
        if (held != 0) {
            this.x = heldBypl.returnX();
            this.y = heldBypl.returnY();
        }
        if (held == 1) {
            checkEnd();
        } else if (held == -1) {
            checkStart();
        }
        g.drawImage(Images.flag, JavaGraphics.screen_w/4 - (ref.returnX() - x), JavaGraphics.screen_h/3*2 - (ref.returnY() - y) - 32,null);
    }

    public void checkStart() {
        if (x/32 == startX/32 && y/32 == startY /32) {
            held = 0;
            heldBypl.setCarryingFlag(null);
            heldBypl = null;
        }
    }


    public void checkEnd() {
        if (x/32 == endX/32 && y/32 == endY /32) {
            Scoreboard.setScore(Scoreboard.getScore(heldBypl.returnPlayerNum())+1, heldBypl.returnPlayerNum());
            held = 0;
            heldBypl.setCarryingFlag(null);
            heldBypl = null;
            x = startX;
            y = startY;
        }
    }
}
