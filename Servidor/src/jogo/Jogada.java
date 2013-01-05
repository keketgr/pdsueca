/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package jogo;

import java.util.ArrayList;

/**
 *
 * @author Paulo
 */
public class Jogada {

    private ArrayList<Cartas> jogada;

    public Jogada() {
        jogada = new ArrayList<>();
    }

    public void limpajogada() {
        jogada.clear();
    }

    public void addjogada(Cartas c) {
        System.out.println("carta: " + c.getTipo() + " naipe " + c.getNaipe());
        jogada.add(c);
    }

    public int getCartasRecebidas() {
        if (jogada.isEmpty()) {
            return 0;
        }
        return jogada.size();
    }

    public Cartas getCarta(int pos) {
        if (!jogada.isEmpty() || pos <= (jogada.size() - 1)) {
            return jogada.get(pos);
        }
        return null;
    }

    public int recolhebaza(Cartas trunfo, int first) {
        int vencedor = -1;
        //ve se foi jogado trunfo
        System.out.println("Cartas recebidas");
        for(int k=0;k<4;k++){
           System.out.println(jogada.get(k).toString()+"tipo: "+jogada.get(k).getTipo());
        
        }
        for (int i = 0; i < 4; i++) {
            if(vencedor ==-1){
                vencedor=i;
            }
            else{
                    if(jogada.get(i).getNaipe()==jogada.get(vencedor).getNaipe() || jogada.get(i).getNaipe() == trunfo.getNaipe()){
                        if(jogada.get(i).getNaipe() == jogada.get(vencedor).getNaipe()){
                            if (jogada.get(i).getTipo() > jogada.get(vencedor).getTipo()) {
                                vencedor = i;
                            }
                        }
                        else if(jogada.get(i).getNaipe() == trunfo.getNaipe()){//eliminar condicao??
                            vencedor=i;
                        }
                    }
            }
        }
        
        return vencedor;
    }

    public int contapontos() {
        int t = 0;

        for (int i = 0; i < 4; i++) {
            t += jogada.get(i).getValor();
            System.out.print(i + ":valor: " + jogada.get(i).getValor());
        }
        return t;
    }
}
