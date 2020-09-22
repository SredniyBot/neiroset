import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Img {
    public int height, width;
    BufferedImage img1;

    public Img(String path) throws IOException {
        img1 = ImageIO.read(new File(path));
        this.height = img1.getHeight();
        this.width = img1.getWidth();
    }

    public ArrayList<ArrayList<Color>> frame(int x, int y,int size){
        ArrayList<ArrayList<Color>> m = new ArrayList<ArrayList<Color>>();
        for(int i = 0;i<size;i++){
            ArrayList k = new ArrayList();
            for(int j = 0;j<size;j++){
                int l =img1.getRGB(i+x,j+y);
                Color c= new Color(r(l),g(l),b(l));

                k.add(c);
            }
            m.add(k);
        }
        return m;
    }

    public int r(int h){
        int red = (h >> 16) & 0xff;
        return red;
    }

    public int g(int h){
        int green = (h >>  8) & 0xff;
        return green;
    }

    public int b(int h){
        int blue = (h ) & 0xff;
        return blue;
    }

}