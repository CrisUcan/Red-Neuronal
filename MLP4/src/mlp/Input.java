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
public class Input extends Layer {

    public Input(int instancias, int atributos) {
        super(instancias, atributos);
    }

    public void text() {
        String text = "100";
        if (text.equals("1000")) {
            System.out.println("texto iguales");
        } else {
            System.out.println("texto diferentes");
        }
    }

}
