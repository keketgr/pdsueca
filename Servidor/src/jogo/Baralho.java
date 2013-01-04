/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.ArrayList;

/**
 *
 * @author Paulo
 */
public class Baralho {

    private ArrayList<Cartas> baralho = null;

    public Baralho() {
        baralho = new ArrayList<>();
        criarbaralho();
        baralha();
        baralha();
        baralha();
    }

    public void criarbaralho() {
        Cartas c;
        if (!baralho.isEmpty()) {
            baralho.removeAll(baralho);
        }
        for (int i = 0; i < 4; i++) {
            c = new Cartas(i, 11, 12);//ás
            baralho.add(c);
            for (int j = 2; j < 7; j++) {
                c = new Cartas(i, 0, j);//sam the kid xD
                baralho.add(c);
            }
            c = new Cartas(i, 10, 11);//bisca
            baralho.add(c);
            c = new Cartas(i, 2, 8);//dama
            baralho.add(c);
            c = new Cartas(i, 3, 9);//valete
            baralho.add(c);
            c = new Cartas(i, 4, 10);//rei
            baralho.add(c);
        }
    }

    public int gettipo(int i) {
        return baralho.get(i).getTipo();
    }

    public int getvalor(int i) {
        return baralho.get(i).getValor();
    }

    public int getnaipe(int i) {
        return baralho.get(i).getTipo();
    }
    
    public Cartas getcarta(int pos){
        return baralho.get(pos);
    }

    public void baralha() {
        int c_random;
        ArrayList<Cartas> aux = new ArrayList<>();
        aux.addAll(baralho);
        baralho.removeAll(baralho);
        while (!aux.isEmpty()) {
            c_random = 0 + (int) (Math.random() * aux.size());
            baralho.add(aux.get(c_random));
            aux.remove(c_random);
        }
    }

    public int getncartasnaipe(ArrayList<Cartas> cartas, int n) {
        int c = 0;
        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i).getNaipe() == n) {
                c++;
            }
        }

        return c;
    }

    public int getcartamaisbaixa(ArrayList<Cartas> cartas, int naipe) {
        int n = -1;
        int v = cartas.get(0).getTipo();
        int f = 0;
        for (int i = 0; i < cartas.size(); i++) {
            if (cartas.get(i).getNaipe() == naipe) {
                if (f == 0) {
                    v = cartas.get(i).getTipo();
                    n = i;
                    f = 1;
                }
                if (cartas.get(i).getTipo() < v) {
                    v = cartas.get(i).getTipo();
                    n = i;
                }
            }
        }

        return n;
    }

    public ArrayList<Cartas> get10cartas() {

        ArrayList<Cartas> temp = new ArrayList<>();
        ArrayList<Cartas> org = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            temp.add(baralho.get(0));
            baralho.remove(0);
        }
        int pos = 0;
        for (int i = 0; i < 4; i++) {
            int lim = getncartasnaipe(temp, i);
            for (int j = 0; j < lim; j++) {
                pos = getcartamaisbaixa(temp, i);
                org.add(temp.get(pos));
                temp.remove(pos);
            }
        }
        return org;
    }
}
