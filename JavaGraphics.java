/**
 * Name : Siddhant Shah
 * Student Number : 22204883
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class JavaGraphics{
    public static int screen_w;
    public static int screen_h;
    public static int block_w = 32;
    public static int block_h = 32;
    public static int numblock_w;
    public static int numblock_h;
    public static int maplength_x = 100;
    public static int maplength_y = 50;
    public static char[][] map;
    public static Player p0;
    public static Player p1;
    public static Flag f1;
    public static Flag f2;

    public JavaGraphics() {
        try {
            Images.loadImages();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame window = new JFrame();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        screen_w = dim.width;
        screen_h = dim.height;
        numblock_w = screen_w / block_w;
        numblock_h = screen_h / block_h;

        map = MapLoader.loadMap("src/resources/map.dat");

        window.setSize(screen_w, screen_h);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        p0 = new Player(screen_w/4,screen_h/3 * 2 , new Color(125,80,50),'W', 'S', 'A', 'D', 'Q', Images.p1, 0);
        p1 = new Player(32*100 - screen_w/4,screen_h/3 * 2, new Color(75,180,255), KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, 'P', Images.p2, 1);

        GameGraphics graphicsLeft = new GameGraphics(0);
        graphicsLeft.setPreferredSize(new Dimension(screen_w / 2, screen_h));
        GameGraphics graphicsRight = new GameGraphics(1);
        graphicsRight.setPreferredSize(new Dimension(screen_w / 2, screen_h));
        panel.add(graphicsLeft, BorderLayout.WEST);
        panel.add(graphicsRight, BorderLayout.EAST);
        panel.setBackground(Color.BLACK);
        window.add(panel);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setUndecorated(true);
        window.setVisible(true);
    }
}
