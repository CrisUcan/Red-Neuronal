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
public class Hidden extends Layer {

    private double[] sigma;
    private double[] output;
    private double[] error;

    public Hidden(int neuronas, int pesos) {
        super(neuronas, pesos);
        output = new double[neuronas];
        sigma = new double[neuronas];
        error = new double[neuronas];
    }

    //BACKPROGATION
    public void stimateError(int neurona, double errorSigma) {
        error[neurona] = this.derSigmoide(sigma[neurona]) * errorSigma;
        //System.out.println("ERROR NEURONA "+neurona+" : "+error[neurona]);
    }

    public void updateWeights(int neurona, double [] inputs) {
        this.getWeights()[neurona][0] += this.eta * error[neurona];
        for (int i = 1; i < inputs.length+1; i++) {
            this.getWeights()[neurona][i] -= this.eta * error[neurona] * inputs[i-1];
        }
    }

    //FEEDFORWARD
    public void calculateOutput(double[] inputs, int neurona) {
        double aux = this.sigma(inputs, neurona)/255;
        output[neurona] = this.sigmoide(aux);
        
    }

    public double getOutput(int i) {
        return output[i];
    }

    public double[] getOutputs() {
        return output;
    }
}
