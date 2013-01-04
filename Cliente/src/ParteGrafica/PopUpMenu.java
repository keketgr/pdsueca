/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ParteGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Rafael
 */
public class PopUpMenu extends JPopupMenu {

    JanelaUsersSala comunica;

    public PopUpMenu(JanelaUsersSala comunica) {
        this.comunica = comunica;
        JMenuItem menuItemConvidar = new JMenuItem("Convidar");
        add(menuItemConvidar);
        menuItemConvidar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformedConvidar(e);
            }
        });

        JMenuItem menuItemPM = new JMenuItem("Msg privada");
        add(menuItemPM);
        menuItemPM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionPerformedPM(e);
            }
        });
//       JMenuItem  menuItemPerfil = new JMenuItem("Ver Perfil");
//        add(menuItemPerfil);
//        menuItemPerfil.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            }
//        });

    }

    public void actionPerformedPM(ActionEvent e) {
        comunica.PrivateChat(comunica.getSelectedUser());
        //System.out.println("popupmenoPM");

    }

    public void actionPerformedConvidar(ActionEvent e) {
         comunica.Invite(comunica.getSelectedUser());
    }
}
