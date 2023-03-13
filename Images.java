/**
 * Name : Siddhant Shah
 * Student Number : 22204883
 */
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {

    public static BufferedImage grass;
    public static BufferedImage sky;
    public static BufferedImage flag;
    public static BufferedImage p1[] = new BufferedImage[2];
    public static BufferedImage p2[] = new BufferedImage[2];

    public static void loadImages() throws IOException {
        grass = ImageIO.read(new File("src/resources/stone.png"));
        sky = ImageIO.read(new File("src/resources/sky3.jpg"));
        flag = ImageIO.read(new File("src/resources/flag4.png"));
        p1[0] = ImageIO.read(new File("src/resources/f2.png"));
        p1[1] = ImageIO.read(new File("src/resources/f1.png"));
        p2[0] = ImageIO.read(new File("src/resources/f4.png"));
        p2[1] = ImageIO.read(new File("src/resources/f3.png"));
    }
}
