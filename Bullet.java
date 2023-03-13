/**
 * Name : Siddhant Shah
 * Student Number : 22204883
 */
import java.awt.*;

public class Bullet {
    private int x, y;
    private int width = 18;
    private int height = 12;
    private int speed;
    public Bullet(int x, int y, int bulspeed) {
        this.x = x;
        this.y = y;
        this.speed = bulspeed;
    }

    public void drawBullet(Graphics g, Player ref) {
        g.setColor(Color.BLACK);
        g.fillRect(JavaGraphics.screen_w/4 - (ref.returnX() - x), JavaGraphics.screen_h/3*2 - (ref.returnY() - y),width,height);
        x += speed;
    }


    public int returnX() {
        return x;
    }


    public int returnY() {
        return y;
    }

    public int returnWidth() {
        return width;
    }

    public int returnSpeed() {
        return speed;
    }
}
