public class Layer {

    private double[][] layer;
    private double[] means;
    private double[] mistakes;

    Layer(double[][] layer){
        this.layer=layer;
        means=new double[layer.length];
        mistakes=new double[layer.length];
        for(int i=0;i<layer.length;i++){
            this.means[i]=layer[i][0];
            this.mistakes[i]=layer[i][1];
        }
    }

    Layer(int length){
        this.layer=new double[length][2];
        means=new double[layer.length];
        mistakes=new double[layer.length];
        for(int i=0;i<layer.length;i++){
            this.means[i]=layer[i][0];
            this.mistakes[i]=layer[i][1];
        }
    }


    public void setLayer(double[][] layer) {
        this.layer = layer;
    }

    public void setMeans(double[] means) {
        this.means = means;
        for (int i=0;i<layer.length;i++){
            layer[i][1]=means[i];
        }
    }

    public void setMistakes(double[] mistakes) {
        this.mistakes = mistakes;
        for (int i=0;i<layer.length;i++){
            layer[i][1]=mistakes[i];
        }
    }

    public double[][] getLayer(){
        return layer;
    }

    public double[] getMeans() {
        return means;
    }

    public double[] getMistakes() {
        return mistakes;
    }
}
