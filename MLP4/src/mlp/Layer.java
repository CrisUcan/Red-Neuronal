/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mlp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

/**
 *
 * @author isma8
 */
public class Layer {

    public double eta = 1; //tasa de aprendizaje
    private double weights[][]; //neuronas y pesos, instancias y atributos

    public Layer(int i, int j) {
        weights = new double[i][j];
        /*for(int x=0; x<i; x++){
            for(int y=0; y<j; y++){
                System.out.println("Neurona "+x+" Peso "+weights[x][y]);
            }
            System.out.println();
        }*/
    }

    public double sigmoide(double x) { //Función de activación sigmoide
        return 1 / (1 + Math.pow(Math.E, -x));
    }

    public double derSigmoide(double x) { //Función prima de activación sigmoide
        return sigmoide(x) * (1 - sigmoide(x));
    }

    public double sigma(double[] entradas, int neurona) { //Sumatoria entre las entradas y los pesos
        double sigma = 0;
        for (int i = 1; i < entradas.length; i++) {
            sigma += (entradas[i-1] * weights[neurona][i]);
        }
        sigma += weights[neurona][0];
        return sigma;
    }
    
    
    public double [][] getWeights(){
        return this.weights;
    }

    public void iniWeights() {
        Random r = new Random();
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                weights[i][j] =1.9;//r.nextDouble();
                //System.out.println(i+" "+j+": "+weights[i][j]);
            }
        }
    }
    
    public void setWeights(int index,double w0, double w1, double w2){
        weights[index][0] = w0;
        weights[index][1] = w1;
        weights[index][2] = w2;
        //System.out.println("Neurona "+index+" Peso "+weights[index][0]);
        //System.out.println("Neurona "+index+" Peso "+weights[index][1]);
        //System.out.println("Neurona "+index+" Peso "+weights[index][2]);
        //System.out.println("-----------------------------");
    }

    public void exportWeights(String path, int layer) {//0 input //1 hidden //2 output
        try {

            File file;
            switch (layer) {
                case 0:
                    file = new File(path + "/input.txt");
                    break;
                case 1:
                    file = new File(path + "/hidden.txt");
                    break;
                case 2:
                    file = new File(path + "/output.txt");
                    break;
                default:
                    file = new File("");
                    System.err.println("Imposible exportar");
                    break;
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < weights.length; i++) {
                for (int j = 0; j < weights[i].length - 1; j++) {
                    bw.write(weights[i][j] + ",");
                }
                bw.write(weights[i][weights[i].length - 1] + "");
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void importWeights(String path, int layer) {//0 input //1 hidden //2 output
        try {
            File file;
            switch (layer) {
                case 0:
                    file = new File(path + "/input.txt");
                    break;
                case 1:
                    file = new File(path + "/hidden.txt");
                    break;
                case 2:
                    file = new File(path + "/output.txt");
                    break;
                default:
                    file = new File("");
                    System.err.println("Imposible exportar");
                    break;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st, number;
            int n = 0, w = 0; //n = neuron w = weight
            while ((st = br.readLine()) != null) {
                number = "";
                for(int i=0; i<st.length(); i++){
                    if(st.charAt(i) != ','){
                        number += st.charAt(i);
                    }else{
                        weights[n][w]=Double.parseDouble(number);
                        w++;
                        number = "";
                    }
                }
                weights[n][w]=Double.parseDouble(number);
                n++;
                w = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            switch (layer) {
                case 0:
                    System.out.println("Weights loaded input layer");
                    break;
                case 1:
                    System.out.println("Weights loaded hidden layer");
                    break;
                case 2:
                    System.out.println("Weights loaded output layer");
                    break;
                default:
                    System.out.println("it was not possible load the layer");
                    break;
            }
        }
    }
    
   

}
