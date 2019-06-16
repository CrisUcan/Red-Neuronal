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
public class Perceptron {

    public static final int neuronas_capa_salida = 14;
    public static final int neuronas_capa_oculta = 900;

    public void train(Hidden hidden, Output output, double[] input, double[] clase) {

        //FEEDFORWARD
        for (int i = 0; i < neuronas_capa_oculta; i++) {
            hidden.calculateOutput(input, i);
        }

        for (int i = 0; i < neuronas_capa_salida; i++) {
            output.calculateOutput(hidden.getOutputs(), i);
            //System.out.println(output.getOutput(i));
        }

        //BACKPROPAGATION //CAPA SALIDA
        for (int i = 0; i < neuronas_capa_salida; i++) {
            output.estimarError(i,clase[i]);
        }

        for (int i = 0; i < neuronas_capa_salida; i++) {
            output.updateWeights(i, hidden.getOutputs());
        }
        //CAPA OCULTA
        for (int i = 0; i < neuronas_capa_oculta; i++) {
            hidden.stimateError(i, output.getSigmaError(i));
        }

        for (int i = 0; i < neuronas_capa_oculta; i++) {
            hidden.updateWeights(i, input);
        }

    }

}
