/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boletin8_4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author fidi
 */
class JCheckBoxEntero extends JCheckBox {

    private int numero;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
        this.setText(numero + "");
    }

}

public class Programa8_4 extends JFrame implements ItemListener,ActionListener {

    JCheckBoxEntero[] chkLoteria;
    JLabel lblIcono;
    private int seleccionado;
    private final int maximo = 6;
    JButton btnAceptar;
    int [] numMaquina = new int[6];

    public Programa8_4() {
        super("Lotería");
        setLayout(null);

        //JRadioButtons chkLoteria
        chkLoteria = new JCheckBoxEntero[50];
        int x = 100, y = 30;
        for (int i = 0; i < chkLoteria.length; i++) {
            chkLoteria[i] = new JCheckBoxEntero();
            chkLoteria[i].setNumero(i);
            chkLoteria[i].setSize(chkLoteria[i].getPreferredSize());
            chkLoteria[i].setLocation(x, y);
            chkLoteria[0].setVisible(false);
            chkLoteria[i].addItemListener(this);
            y += 30;
            if ((i + 1) % 10 == 0) {
                x += 40;
                y = 30;
            }
            add(chkLoteria[i]);

        }
        //JLabel 
        lblIcono = new JLabel();
        lblIcono.setIcon(new ImageIcon("src/img/bingo.png"));
        lblIcono.setSize(32, 32);
        lblIcono.setLocation(100, 20);
        add(lblIcono);
        
        //JButton
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setSize(90, 35);
        btnAceptar.setLocation(160,340);
        btnAceptar.setEnabled(false);
        btnAceptar.addActionListener(this);
        add(btnAceptar);
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {

        JCheckBox source = (JCheckBox) ie.getSource();
        if (source.isSelected()) {
            seleccionado++;
            if (seleccionado == maximo) {
                for (JCheckBoxEntero ch : chkLoteria) {
                    if (!ch.isSelected()) {
                        ch.setEnabled(false);
                        btnAceptar.setEnabled(true);
                    }
                }
            }
        } else {
            seleccionado--;
            if (seleccionado<maximo) {
                for (JCheckBoxEntero ch : chkLoteria) {
                    if(ch.isSelected());
                    ch.setEnabled(true);
                    btnAceptar.setEnabled(false);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==btnAceptar){
            for (int i = 0; i < numMaquina.length; i++) {
                numMaquina[i]= (int)(Math.random()*49)+1;
                System.out.println(numMaquina[i]);
            }
        }
    }
}
