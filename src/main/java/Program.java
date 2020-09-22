import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Program {

    public static String path = "src/main/resources/img.png";

    public static void main(String[] args) {
        window();
        window_with_image();
    }

    public static void window(){
        JFrame w = new JFrame();
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w.setResizable(false);
        w.setBounds(20,20,800,800);
        Panel p = new Panel();
        p.setBounds(0,0,800,800);
        w.add(p);
        w.setVisible(true);


    }

    public static void window_with_image(){
        BufferedImage img1 = null;

        try {
            img1 = ImageIO.read(new File(Program.path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JFrame w1 = new JFrame();
        w1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        w1.setResizable(false);
        w1.setIconImage(null);
        w1.setLayout(null);
        w1.setBounds(820,20,800,900);
        ImagePanel p1 = new ImagePanel();
        p1.setBounds(0,0,800,800);
        p1.setLayout(null);
        w1.addMouseMotionListener(p1);
        w1.addMouseListener(p1);
        w1.addKeyListener(p1);
        w1.add(p1);
        w1.setVisible(true);
    }

}


