/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import jogo.Cartas;

/**
 *
 * @author Paulo
 */
public class Getlink {

    static private BufferedImage carta = null;
    private static String[] listapaus = {"src/Imagens/1.png", "src/Imagens/49.png", "src/Imagens/45.png", "src/Imagens/41.png", "src/Imagens/37.png", "src/Imagens/33.png", "src/Imagens/29.png", "src/Imagens/13.png", "src/Imagens/9.png", "src/Imagens/5.png"};
    private static String[] listaespadas = {"src/Imagens/2.png", "src/Imagens/50.png", "src/Imagens/46.png", "src/Imagens/42.png", "src/Imagens/38.png", "src/Imagens/34.png", "src/Imagens/30.png", "src/Imagens/14.png", "src/Imagens/10.png", "src/Imagens/6.png"};
    private static String[] listacopas = {"src/Imagens/3.png", "src/Imagens/51.png", "src/Imagens/47.png", "src/Imagens/43.png", "src/Imagens/39.png", "src/Imagens/35.png", "src/Imagens/31.png", "src/Imagens/15.png", "src/Imagens/11.png", "src/Imagens/7.png"};
    private static String[] listaouros = {"src/Imagens/4.png", "src/Imagens/52.png", "src/Imagens/48.png", "src/Imagens/44.png", "src/Imagens/40.png", "src/Imagens/36.png", "src/Imagens/32.png", "src/Imagens/16.png", "src/Imagens/12.png", "src/Imagens/8.png"};
    private static String[] lista;
    private static String img;

    public Getlink() {
    }

    public BufferedImage getB() {
        img = "b1fv.bnp";
        try {
            carta = ImageIO.read(new File(img));

        } catch (IOException e) {
            System.out.println("Erro ao carregar a imagem :" + e);
        }
        return carta;
    }

    public BufferedImage getcarta(Cartas c) {
        if (c.getNaipe() == 0) {
            lista = listaespadas;
        } else if (c.getNaipe() == 1) {
            lista = listaouros;
        } else if (c.getNaipe() == 2) {
            lista = listapaus;
        } else {
            lista = listacopas;
        }

        switch (c.getTipo()) {
            case 12:
                img = lista[0];
                break;
            case 2:
                img = lista[1];
                break;
            case 3:
                img = lista[2];
                break;
            case 4:
                img = lista[3];
                break;
            case 5:
                img = lista[4];
                break;
            case 6:
                img = lista[5];
                break;
            case 11:
                img = lista[6];
                break;
            case 8:
                img = lista[8];
                break;
            case 9:
                img = lista[7];
                break;
            case 10:
                img = lista[9];
                break;
            default:
                img = lista[0];
                break;

        }
        try {
            carta = ImageIO.read(new File(img));
        } catch (IOException e) {
            System.out.println("Erro ao carregar a imagem :" + e);
        }
        return carta;
    }
}
