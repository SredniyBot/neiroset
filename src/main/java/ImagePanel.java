import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel implements MouseMotionListener, MouseListener, KeyListener {

    private static BufferedImage img1 = null;
    private static BufferedImage img2 = null;
    private static int mx_position,my_position;
    private static double k=1,d=1;

    public void paint(Graphics g){
        g.clearRect(0,0,2000,3000);
        try {
            img1 = ImageIO.read(new File(Program.path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(needToRS(),0,0,null);
        g.setColor(Color.RED);
        g.drawRect((int)(Panel.dx*d),(int)(Panel.dy*d),(int)(Panel.size*d),(int)(Panel.size*d));
        mouseRect(g);
        repaint();
    }

    public static void mouseRect(Graphics g){
        g.setColor(Color.BLUE);
        if(mx_position-Panel.size*d<img2.getWidth()&&my_position-Panel.size*d<img2.getHeight()){
            g.drawRect((int) (mx_position-Panel.size*d/2-8) ,(int)(my_position-32-Panel.size*d/2),(int)(Panel.size*d),(int)(Panel.size*d));
        }
    }

    public void mouseClicked(MouseEvent e) {
        Panel.dx=(int) (e.getX()/d-Panel.size*d/2-8);
        Panel.dy=(int)(e.getY()/d-32-Panel.size*d/2);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
        mx_position = e.getX();
        my_position = e.getY();
    }

    public static BufferedImage needToRS(){
        if(img1.getWidth()>img1.getHeight()&&img1.getWidth()>800){
            k =(double)img1.getHeight()/img1.getWidth();
            d=800/(double)img1.getWidth();
            //                                                                  System.out.println(d);
            img2 =resizeImage(img1,  800, (int) (800*k));
        }else if(img1.getWidth()<img1.getHeight()&&img1.getHeight()>800){
            k = (double)(img1.getWidth())/img1.getHeight();
            img2 =resizeImage(img1, (int) (800*k) , 800);
            d=800/(double)img1.getHeight();
        }else{
            img2=img1;
        }
        return img2;
    }

    public static BufferedImage resizeImage(BufferedImage image, int width, int height) {
        int type = (image.getTransparency() == Transparency.OPAQUE)
                ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage bi = new BufferedImage(width, height, type);
        Graphics2D graphics = bi.createGraphics();
        graphics.drawImage(image, 0, 0, width, height, null);
        graphics.dispose();
        return bi;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==32){
            Panel.stop=!Panel.stop;
        }
        if(e.getKeyCode()==49){
            Panel.collage=!Panel.collage;
        }
        if(e.getKeyCode()==27){
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}