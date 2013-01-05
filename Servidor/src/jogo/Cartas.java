/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package jogo;

import java.io.Serializable;

/**
 *
 * @author Paulo
 */
/*
 * id     naipe
 * 0    espadas
 * 1    paus
 * 2    ouros
 * 3    copas
 * 
 */
public class Cartas implements Serializable {

    private int naipe;
    private int valor;
    private int tipo;

    public Cartas(int naipe, int valor, int tipo) {
        this.naipe = naipe;
        this.valor = valor;
        this.tipo = tipo;
    }

    public int getNaipe() {
        return naipe;
    }

    public int getTipo() {
        return tipo;
    }

    public int getValor() {
        return valor;
    }

    @Override
    public String toString() {
        String out = "";
        switch (tipo) {
            case 12:
                out += "A";
                break;
            case 11:
                out += "7";
                break;
            case 10:
                out += "R";
                break;
            case 9:
                out += "V";
                break;
            case 8:
                out += "D";
                break;
            default:
                out += tipo;
        }
        switch (naipe) {
            case 0:
                out += " Espadas";
                break;
            case 1:
                out += " Ouros";
                break;
            case 2:
                out += " Paus";
                break;
            case 3:
                out += " Copas";
                break;
        }
        return out;
    }
}
