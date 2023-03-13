/**
 * Name : Siddhant Shah
 * Student Number : 22204883
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {
    private int x, y, health, vX, vY;
    private int startX, startY;
    private boolean onGround = false;
    private int controls[] = new int[5];
    private int lastDirection = 1;
    private BufferedImage pImg[];
    private int shootCool = 0;
    private int respawnCool = 0;
    private int playerNum = 0;
    private Flag carryingFlag;

    public Player (int x, int y, Color c, int moveUpC, int moveDownC, int moveLeftC, int moveRightC, int shootC, BufferedImage pImg[], int playerNum) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        this.health = 100;
        this.pImg = pImg;
        this.controls[0] = moveUpC;
        this.controls[1] = moveDownC;
        this.controls[2] = moveLeftC;
        this.controls[3] = moveRightC;
        this.controls[4] = shootC;
        this.playerNum = playerNum;
    }


    public void move() {
        x = x + vX/10;
        y = y + vY/10;
    }


    public void setSpeed(int vX, int vY) {
        this.vX = vX;
        this.vY = vY;
        if (vX > 0) {
            lastDirection = 1;
        } else if (vX < 0) {
            lastDirection = - 1;
        }
    }


    public void teleport(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void teleportStart() {
        this.x = startX;
        this.y = startY;
    }


    public void die() {
        if (carryingFlag != null) {
            carryingFlag.setHeld(0);
            carryingFlag.setHeldBy(null);
            System.out.println("test");
        }
        carryingFlag = null;
        respawnCool++;
    }


    public void setGround(boolean s) {
        onGround = s;
    }


    public void setShootCool(int cool) {
        shootCool = cool;
    }


    public void setCarryingFlag(Flag f) { carryingFlag = f; }


    public void drawPlayer(Graphics g, Player ref) {
        if (respawnCool != 0) {
            return;
        }
        if (lastDirection == 1) {
            g.drawImage(pImg[1], JavaGraphics.screen_w/4 - (ref.returnX() - x), JavaGraphics.screen_h/3*2 - (ref.returnY() - y), 32, 32, null);
        } else {
            g.drawImage(pImg[0], JavaGraphics.screen_w/4 - (ref.returnX() - x), JavaGraphics.screen_h/3*2 - (ref.returnY() - y), 32, 32, null);
        }

    }


    public void tickPlayer() {
        if (shootCool > 0) shootCool--;
        if (respawnCool > 0) respawnCool++;
        if (respawnCool >= 300) {
            respawnCool = 0;
            teleportStart();
        }
    }


    public int returnX(){
        return this.x;
    }


    public int returnY(){
        return this.y;
    }


    public int returnVx() { return this.vX; }


    public int returnVy() { return this.vY; }


    public int returnLastDirection() { return this.lastDirection; }


    public int returnRespawnCool() { return this.respawnCool; }


    public int returnShootCool() { return this.shootCool; }


    public int[] returnControls() { return this.controls; }


    public int returnPlayerNum() { return this.playerNum; }


    public BufferedImage[] returnImages() { return this.pImg; }

    public boolean isDead() {
        if (respawnCool == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean onGround() { return this.onGround; }
}
