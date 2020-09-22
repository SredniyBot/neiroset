import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

public class Panel extends JPanel  {
    Img img;
    ArrayList<ArrayList<Color>> colorFrame;
    public static int rectWidth = 19;
    public static int size=20;
    public static int dx,dy;
    public static int neiroBoxSize = 10;
    public static int [] layers={301,250,200,250,100};


    public static boolean collage=false;
    public static boolean stop=false;

    public static neiro Red = new neiro(layers);
    public static neiro Blue = new neiro(layers);
    public static neiro Green = new neiro(layers);
    double[][] L0RED = new double[size*size-neiroBoxSize*neiroBoxSize][2];
    double[][] L0BLUE = new double[size*size-neiroBoxSize*neiroBoxSize][2];
    double[][] L0GREEN = new double[size*size-neiroBoxSize*neiroBoxSize][2];
    double[] KEYRED = new double[neiroBoxSize*neiroBoxSize];
    double[] KEYBLUE = new double[neiroBoxSize*neiroBoxSize];
    double[] KEYGREEN = new double[neiroBoxSize*neiroBoxSize];

    public void paint(Graphics g){
        g.clearRect(0,0,2000,2000);
        try {
            img = new Img(Program.path);
            colorFrame = img.frame(dx,dy,size);
        } catch (IOException e) {
            // e.printStackTrace();
        }
        int n=0;
        int an=0;
        for(int x=0;x<size;x++){
            for(int y=0;y<size;y++){
                Color c=colorFrame.get(x).get(y);
                g.setColor(c);
                if(x>(size-neiroBoxSize)/2-1&&x<(size-neiroBoxSize)/2+neiroBoxSize&&y>(size-neiroBoxSize)/2-1&&y<(size-neiroBoxSize)/2+neiroBoxSize){
                    //System.out.println((size-neiroBoxSize)/2-1+neiroBoxSize);
                    KEYRED[an]=c.getRed();
                    KEYBLUE[an]=c.getBlue();
                    KEYGREEN[an]=c.getGreen();
                    an++;
                }else{
                    L0RED[n][0]=c.getRed();
                    L0BLUE[n][0]=c.getBlue();
                    L0GREEN[n][0]=c.getGreen();
                    n++;
                }
                g.fillRect(10+rectWidth*x,10+rectWidth*y,rectWidth,rectWidth);
            }
        }


        paintNeiroRes(g);

        teachNeiro(KEYRED,KEYGREEN,KEYBLUE);
        if(!stop) {
            if (dx + size < img.width) {
                dx++;
            } else {
                dy += 1;
                dx = 0;
            }
        }
        g.setColor(Color.RED);
        g.drawRect(10+(size-neiroBoxSize)/2*rectWidth,10+(size-neiroBoxSize)/2*rectWidth,neiroBoxSize*rectWidth,neiroBoxSize*rectWidth);

        repaint();
   }

   private void paintNeiroRes(Graphics g){
       Color[][] fr = getResult(L0RED,L0GREEN,L0BLUE);
       for(int x = 0;x<neiroBoxSize;x++){
           for(int y = 0;y<neiroBoxSize;y++){
               g.setColor(fr[x][y]);
               if(collage) {
                   g.fillRect(10+rectWidth*(size-neiroBoxSize)/2 + y * rectWidth, 10+rectWidth*(size-neiroBoxSize)/2 + x * rectWidth, rectWidth, rectWidth);
               }else{
                   g.fillRect(400 + y * rectWidth, 400 + x * rectWidth, rectWidth, rectWidth);
               }
           }
       }
   }

    private Color [][] mirror(Color [][] src){
        Color [][] mir1=new Color[src.length][src[0].length];
        for(int y=0; y<src.length;y++){
            for(int x=0; x<src.length;x++){
                mir1[x][y]=src[x][src[0].length-1-y];
            }
        }
        return mir1;

    }

    private Color [][] rotate(Color [][] sourceArr) {
        Color [][] retArr = new Color[sourceArr[0].length][sourceArr.length];
        int retArrI = 0;
        int retArrJ = sourceArr.length - 1;
        for (Color[] srI : sourceArr){
            for (Color srJ : srI)
                retArr[retArrI++][retArrJ] = srJ;
            retArrI = 0;
            retArrJ--;
        }
        return retArr;
    }

   public Color[][] getResult(double[][] r,double[][] g,double[][] b){

        double[][] red =Red.findSolution(addNeuron(r));
        double[][] green =Green.findSolution(addNeuron(g));
        double[][] blue =Blue.findSolution(addNeuron(b));
        Color[] z =  new Color[neiroBoxSize*neiroBoxSize];
       for(int x = neiroBoxSize*neiroBoxSize-1;x >=0;x--){
            z[x]= new Color((int)red[x][0],(int)green[x][0],(int)blue[x][0]);
        }

       Color[][] m=new Color[neiroBoxSize][neiroBoxSize];
       int a=0;
       for(int x=0;x<neiroBoxSize;x++){
           for(int y=0;y<neiroBoxSize;y++){
               m[x][y]=z[a];
               a++;
           }
       }
       return mirror(rotate(m));
   }

    private double[][] addNeuron(double[][] r){
        double[][] r1=new double[r.length+1][2];
        for (int i=0;i<=r.length;i++){
            if(i==r.length){
                r1[i][0]=255;
            }else{
                r1[i][0]=r[i][0];
            }
        }
        return r1;
    }

    public void teachNeiro(double[] r,double[] g,double[] b){

        Red.teach(r);
        Green.teach(g);
        Blue.teach(b);
    }


}