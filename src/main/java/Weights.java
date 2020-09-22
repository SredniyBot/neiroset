public class Weights {


    private double[][] weights;

    Weights(double[][] weights){
        this.weights=weights;
    }

    Weights(int size1,int size2){
        this.weights=new double[size1][size2];
    }

    public void setWeights(double[][] weights) {
        this.weights = weights;
    }

    public double[][] getWeights(){
        return weights;
    }

    public void fillRnd(){
        for (int i=0;i<weights.length;i++){
            for (int j=0;j<weights[0].length;j++){
                weights[i][j]=Math.random()/100;
            }
        }
    }

}
