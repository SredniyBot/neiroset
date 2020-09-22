import java.time.Year;
import java.util.ArrayList;

public class neiro {

    private ArrayList<Layer> layers;
    private ArrayList<Weights> weights;

    neiro(int [] numberOfNeiro){
        layers=new ArrayList<>();
        weights=new ArrayList<>();

        for(int number=0;number<numberOfNeiro.length;number++){
            layers.add(new Layer(numberOfNeiro[number]));
        }

        for(int number=0;number<numberOfNeiro.length-1;number++){
            Weights w = new Weights(numberOfNeiro[number],numberOfNeiro[number+1]);
            w.fillRnd();
            weights.add(w);
        }

    }

    public double[][] findSolution(double[][] L){
        layers.set(0,new Layer(L));
        for(int layer=0;layer<layers.size()-1;layer++){
            layers.set(layer+1,
                    new Layer(
                            neiroset.forward(
                                    layers.get(layer+1).getLayer(),
                                    weights.get(layer).getWeights(),
                                    layers.get(layer).getLayer())));
        }

//        L0=L;
//        L1= neiroset.forward(L1,W0,L);
//        L2= neiroset.forward(L2,W1,L1);
//        L3= neiroset.forward(L3,W2,L2);
        return layers.get(layers.size()-1).getLayer();
    }

    public void teach(double[] k){
        findError(k);

        for (int i=0;i<weights.size();i++){
            weights.set(i,new Weights(
                    neiroset.teaching(
                            layers.get(i+1).getLayer(),
                            weights.get(i).getWeights(),
                            layers.get(i).getLayer())));
        }

//        W0= neiroset.teaching(L1,W0,L0);
//        W1= neiroset.teaching(L2,W1,L1);
//        W2= neiroset.teaching(L3,W2,L2);
    }

    private void findError(double[] k){

        Layer l=layers.get(layers.size()-1);
        double[] mistake=l.getMistakes();
        for (int i=0;i<k.length;i++){
            mistake[i]=k[i]-l.getMeans()[i];
        }
        l.setMistakes(mistake);
        layers.set(layers.size()-1,l);

        for(int i=layers.size()-1;i>0;i--){
            layers.set(i-1,new Layer(neiroset.findError(layers.get(i).getLayer(),weights.get(i-1).getWeights(),layers.get(i-1).getLayer())));
            double[] l1=new Layer(neiroset.findError(layers.get(i).getLayer(),weights.get(i-1).getWeights(),layers.get(i-1).getLayer())).getMistakes();

        }

//        for(int i = 0;i<L3.length;i++){
//            L3[i][1]=k[i]-L3[i][0];
//        }
//
//        L2= neiroset.findError(L3,W2,L2);
//        L1= neiroset.findError(L2,W1,L1);
//        L0= neiroset.findError(L1,W0,L0);
    }

}
