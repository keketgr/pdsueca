/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package cliente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

//public class ImageTest {
//
//  public static void main(String[] args) {
//    ImagePanel panel = new ImagePanel(new ImageIcon("Imagens/1.png").getImage());
//
//    JFrame frame = new JFrame();
//    frame.getContentPane().add(panel);
//    frame.pack();
//    frame.setVisible(true);
//  }
//}
public class ImagePanel extends JPanel {

    BufferedImage grid;  // declare the image
    int indiceCarta;
    int angulo;
    int cordx;
    int cordy;
    BufferedImage[] cartasmao;
    int espacamento;

    public ImagePanel(BufferedImage[] cartasmao, int angulo, int cordx, int cordy, int dimx, int dimy, int espacamento) {//desenha a mao
        this.grid = null;
        this.espacamento = espacamento;
        this.angulo = angulo;
        this.cordx = cordx;
        this.cordy = cordy;
        this.cartasmao = cartasmao;
        Dimension size;
        if (dimx == 0 || dimy == 0) {
            size = new Dimension(5, 5);
        } else {
            size = new Dimension(dimx, dimy);
        }
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        Color c = new Color(0, 153, 0);
        setBackground(c);

        setSize(size);
        setLayout(null);

        indiceCarta = -1;
    }

    public ImagePanel(BufferedImage grid, int angulo, int cordx, int cordy, int dimx, int dimy) {//desenha uma carta
        this.grid = grid;
        this.angulo = angulo;
        this.cordx = cordx;
        this.cordy = cordy;
        cartasmao = null;
        this.espacamento = 0;
        Dimension size;
        if (dimx == 0 || dimy == 0) {
            size = new Dimension(grid.getWidth(), grid.getHeight());
        } else {
            size = new Dimension(dimx, dimy);
        }
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        Color c = new Color(0, 153, 0);
        setBackground(c);
        setSize(size);
        setLayout(null);
        indiceCarta = -1;
    }

    public int getIndiceCarta() {
        return indiceCarta;
    }

    public void setIndiceCarta(int indiceCarta) {
        this.indiceCarta = indiceCarta;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);       // paint background
        g.setColor(Color.green);


        if (cartasmao != null) {
            drawMao(g);
            angulo = 0;//a imagem roda apenas 1 vez
            return;
        }
        grid = rorateImg(angulo, grid);
        angulo = 0;//a imagem roda apenas 1 vez
        g.drawImage(grid, cordx, cordy, this);

    }

    private void drawMao(Graphics g) {
        
        for (int i = 0; i < cartasmao.length; i++) {
            cartasmao[i] = rorateImg(angulo, cartasmao[i]);
            if (indiceCarta == i) {
                g.drawImage(cartasmao[i], i * espacamento, 0, this);

            } else {
                g.drawImage(cartasmao[i], i * espacamento, 20, this);
            }
        }
    }

    public int getAngulo() {
        return angulo;
    }

    public void setAngulo(int angulo) {
        this.angulo = angulo;
    }

    public int getCordx() {
        return cordx;
    }

    public void setCordx(int cordx) {
        this.cordx = cordx;
    }

    public int getCordy() {
        return cordy;
    }

    public void setCordy(int cordy) {
        this.cordy = cordy;
    }

    public BufferedImage rorateImg(int ang, BufferedImage img) {//rotates 180 degrees
        AffineTransformOp op;
        AffineTransform tx;
        if (angulo == 0) {
            return img;
        }
        int w;// width of buffered image
        int h;//height of buffered image

        w = img.getWidth();
        h = img.getHeight();

        tx = new AffineTransform();
        tx.rotate(Math.toRadians(ang), w, h);//(radian,arbit_X,arbit_Y)        
        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        img = op.filter(img, null);//(sourse,destination)
        if (angulo == 90) {
            tx = new AffineTransform();
            tx.translate(-w,-(h-w) );
            op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
            img = op.filter(img, null);//(sourse,destination)
        }

        return img;
    }
}