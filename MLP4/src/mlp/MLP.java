/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author isma8
 */
public class MLP {

    public static void main(String[] args) throws FileNotFoundException {
        String path = "C://Users//Lopz//Desktop";

        Input input = new Input(42, 900);
        
        input.importWeights(path, 0);
        //input.importWeights(path, 1);
        //input.importWeights(path, 2);

        Hidden hidden = new Hidden(Perceptron.neuronas_capa_oculta, 901);
       // hidden.importWeights(path, 1);
        Output output = new Output(Perceptron.neuronas_capa_salida, Perceptron.neuronas_capa_oculta + 1);
        //output.importWeights(path, 2);
        
        

        hidden.iniWeights();
        output.iniWeights();

        Perceptron p = new Perceptron();
        double ECM_NEURONA = 0, ECM_INSTANCIA = 0, ECM_EPOCA = 0, aux;
        for (int epoca = 0; epoca < 10000; epoca++) {//EPOCAS
            for (int instancia = 0; instancia <42; instancia++) {
                p.train(hidden, output, input.getWeights()[instancia], Data.clases[instancia]);
                System.out.println("INSTANCIA "+instancia+" :");
                for (int neurona = 0; neurona < 14; neurona++) {
                    //System.out.print(output.getOutput(neurona)+"   ");
                    System.out.println("NEURONA "+neurona+": "+Data.objetivo[instancia][neurona]+"\t"+output.getOutput(neurona)+"\t"+Math.abs(Data.objetivo[instancia][neurona] - output.getOutput(neurona)));
                    ECM_NEURONA += Math.abs(Data.objetivo[instancia][neurona] - output.getOutput(neurona));
                }
                aux = ECM_NEURONA/14;
                System.out.println("Error instancia: "+aux);
                ECM_NEURONA = 0;
                ECM_INSTANCIA += aux;
                System.out.println("-------------------------------------");
            }
            System.out.println("EPOCA "+epoca+" ECM EPOCA: "+ECM_INSTANCIA/42);
            ECM_INSTANCIA = 0;
            System.out.println("//////////////////////////////////////////////////////////////////////////");
        }
        //output.exportWeights(path, 2);
       // hidden.exportWeights(path, 1);

        /*for (int epoca = 0; epoca < 70000; epoca++) {//EPOCAS
            int instancia = 0;
            while (instancia < 42) {
                p.train(hidden, output, input.getWeights()[instancia], Data.clases[instancia]);
                System.out.print("INSTANCIA " + instancia + " :");
                for (int neurona = 0; neurona < 14; neurona++) {
                    System.out.print(output.getOutput(neurona)+"   ");
                    ECM_NEURONA += Math.abs(Data.objetivo[instancia][neurona] - output.getOutput(neurona));
                }
                //System.out.println();
                ECM_INSTANCIA += ECM_NEURONA / 14;
                System.out.println(" ECM: \t" + ECM_INSTANCIA);
                if (ECM_INSTANCIA < 0.01) {
                    instancia++;
                }
                ECM_NEURONA = 0;
                ECM_INSTANCIA = 0;
            }
            //System.out.println("EPOCA "+epoca+" ECM EPOCA: "+ECM_EPOCA);
            //System.out.println("---------------------------------------------------------------");
        }*/

    }

}
