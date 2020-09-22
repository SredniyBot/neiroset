public class neiroset {

    private static double TeachK = 0.0001;

    public static double[][] forward(double[][] L1, double[][] W, double [][] L0){
        int Y= L1.length;
        int X= L0.length;
        double[][] trueL1= new double[Y][2];
        for(int y = 0;y<Y;y++){
            double param = 0;
            for(int x =0;x<X;x++) {
                param+=L0[x][0]*W[x][y];
            }
            trueL1[y][0]=usingF(param);
            trueL1[y][1]=L1[y][1];
        }
        return trueL1;
    }

    public static double[][] findError(double[][] L1, double[][] W, double [][] L0){
        System.out.println();
        int Y= L0.length;
        int X= L1.length;
        double[][] trueL0Mistakes= new double[Y][2];
        for(int y = 0;y<Y;y++){
            double param = 0;
            for(int x =0;x<X;x++) {
                param+=L1[x][1]*W[y][x];
            }
            trueL0Mistakes[y][1]=param;
            trueL0Mistakes[y][0]=L0[y][0];
        }
        return trueL0Mistakes;
    }

    private static double usingF(double m){

        double f=m;
        if(f>255){
            f=255;
        }
        if(f<0){
            f=0;
        }
        return f;

       // return 255/(1+Math.pow(Math.E,-m/255));
    }

    private static double mistakeF(double x){
//        if(x>255||x<0){
//            return 0.0001;
//        }
//        return 0.1;

        return x/(1+Math.abs(x));
        //return usingF(x)*(255-usingF(x));
    }

    public static double[][] teaching(double[][] L1, double[][] W, double [][] L0){
        int Y= L1.length;
        int X= L0.length;
        double W1[][] = new double[W.length][W[0].length];
        for(int y = 0;y<Y;y++) {
            double Wp;
            for (int x = 0; x < X; x++) {
                Wp = W[x][y]+TeachK*mistakeF(L1[y][1]*L0[x][0]);
                if(Wp>1){
                    Wp=1;
                }else if(Wp<0){
                    Wp=0;
                }
                W1[x][y]=Wp;

            }
        }
        return W1;
    }

}
