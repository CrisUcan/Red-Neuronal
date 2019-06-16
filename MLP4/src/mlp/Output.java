/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

/**
 *
 * @author isma8
 */
public class Output extends Layer {

    private double[] output;
    private double [][] oldWeights;
    private double[] sigma;
    private double[] error;

    public Output(int neuronas, int pesos) {
        super(neuronas, pesos);
        oldWeights = new double [neuronas][pesos];
        output = new double[neuronas];
        sigma = new double[neuronas];
        error = new double[neuronas];
    }

    //BACKPROPAGATION
    public void estimarError(int neurona, double clase) {
        //for (int i = 0; i < error.length; i++) {//FOR 
            error[neurona] = this.derSigmoide(sigma[neurona]) * (output[neurona] - clase);
            //System.out.println("ERROR: "+ output[neurona] +" "+ clase);
        //}
    }

    public double getError(int i) {
        return error[i];
    }

    public void updateWeights(int neurona, double[] inputs) {
        oldWeights[neurona][0] = this.getWeights()[neurona][0];
        this.getWeights()[neurona][0] += this.eta * error[neurona];
        for (int i = 1; i < inputs.length + 1; i++) {
            oldWeights[neurona][i] = this.getWeights()[neurona][i];
            this.getWeights()[neurona][i] += this.eta * error[neurona] * inputs[i - 1];
        }
    }
    
    public double getSigmaError(int index){//hace la referencia al peso
        double result = 0;
        for(int neurona=0; neurona<output.length; neurona++){
            result += oldWeights[neurona][index] * error[neurona];
        }
        return result;
    }

    //FEEDFORWARD
    public void calculateOutput(double[] inputs, int neurona) {
        sigma[neurona] = sigma(inputs, neurona)/255;
        /*if(this.sigmoide(sigma[neurona]) > 0.5){//INTENTAR ASI
            output[neurona] = 1;
        }else if(this.sigmoide(sigma[neurona]) < 0.5){
            output[neurona] = 0;
        }else{
            output[neurona] = this.sigmoide(sigma[neurona]);
        }*/
        output[neurona] = this.sigmoide(sigma[neurona]);
        //System.out.println(output[neurona]);
    }

    public double getOutput(int i) {
        return output[i];
    }

    public double[] getOutputs() {
        return output;
    }

    public double sigma(double[] entradas, int neurona) { //Sumatoria entre las entradas y los pesos
        double sigma = 0;
        for (int i = 1; i < entradas.length + 1; i++) {
            sigma += (entradas[i - 1] * this.getWeights()[neurona][i]);
        }
        sigma += this.getWeights()[neurona][0];
        return sigma;
    }

}
