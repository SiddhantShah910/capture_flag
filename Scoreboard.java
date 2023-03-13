/**
 * Name : Siddhant Shah
 * Student Number : 22204883
 */
import java.awt.*;

public class Scoreboard {
    private static Integer score[] = {0,0};

    public static void setScore(int sc, int n) {
        score[n] = sc;
    }

    public static int getScore(int n) {
        return score[n];
    }

    public static void drawScore(Graphics g, int n) {
        g.setFont(new Font("Arial", Font.PLAIN, 48));
        g.drawString(score[n].toString(), 100,100);
    }
}
