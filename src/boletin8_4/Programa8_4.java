/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boletin8_4;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.*;

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

public class Programa8_4 extends JFrame implements ItemListener, ActionListener {

    JCheckBoxEntero[] chkLoteria;
    JLabel lblIcono;
    private int seleccionado;
    private final int maximo = 6;
    JButton btnAceptar;
    ArrayList<Integer> numMaquina = new ArrayList<>(maximo);
    ArrayList<String> boxMaquina = new ArrayList<>();
    JMenuItem mnuGuardar, mnuVer, mnuReset, mnuSalir;
    JMenu mnuJuego;
    JMenuBar mnuMain;

    public Programa8_4() {
        super("Lotería");
        setLayout(null);

        //Js chkLoteria
        chkLoteria = new JCheckBoxEntero[50];
        int x = 105, y = 30;
        for (int i = 0; i < chkLoteria.length; i++) {
            chkLoteria[i] = new JCheckBoxEntero();
            chkLoteria[i].setNumero(i);
            chkLoteria[i].setSize(chkLoteria[i].getPreferredSize());
            chkLoteria[i].setLocation(x, y);
            chkLoteria[i].addItemListener(this);
            y += 30;
            if ((i + 1) % 10 == 0) {
                x += 40;
                y = 30;
            }
            add(chkLoteria[i]);
        }
        chkLoteria[0].setVisible(false);

        //JLabel 
        lblIcono = new JLabel();
        lblIcono.setIcon(new ImageIcon("src/img/bingo.png"));
        lblIcono.setSize(32, 32);
        lblIcono.setLocation(105, 20);
        add(lblIcono);

        //JButton
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setSize(90, 35);
        btnAceptar.setLocation(160, 340);
        btnAceptar.setEnabled(false);
        btnAceptar.addActionListener(this);
        add(btnAceptar);

        //JMenu Juego
        mnuGuardar = new JMenuItem("Guardar");
        mnuGuardar.setMnemonic('G');
        mnuGuardar.addActionListener(this);

        mnuVer = new JMenuItem("Ver");
        mnuVer.setMnemonic('V');
        mnuVer.addActionListener(this);

        mnuReset = new JMenuItem("Reset");
        mnuReset.setMnemonic('R');
        mnuReset.addActionListener(this);

        mnuSalir = new JMenuItem("Salir");
        mnuSalir.setMnemonic('S');
        mnuSalir.addActionListener(this);

        mnuJuego = new JMenu("Juego");
        mnuJuego.setMnemonic('J');
        mnuJuego.add(mnuGuardar);
        mnuJuego.add(mnuVer);
        mnuJuego.addSeparator();
        mnuJuego.add(mnuReset);
        mnuJuego.addSeparator();
        mnuJuego.add(mnuSalir);

        mnuMain = new JMenuBar();
        mnuMain.add(mnuJuego);
        this.setJMenuBar(mnuMain);
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
            if (seleccionado < maximo) {
                for (JCheckBoxEntero ch : chkLoteria) {
                    if (ch.isSelected());
                    ch.setEnabled(true);
                    btnAceptar.setEnabled(false);
                }
            }
        }
        if (ie.getStateChange() == ItemEvent.SELECTED) {
            String boxSelected;
            boxSelected = ((JCheckBox) ie.getSource()).getText();
            boxMaquina.add(boxSelected);

        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int aleatorio;
     
        if (ae.getSource() == btnAceptar) {
            while (numMaquina.size() < maximo) {
                aleatorio = (int) (Math.random() * 49) + 1;
                if (!numMaquina.contains(aleatorio)) {
                    numMaquina.add(aleatorio);
                }
            }
            for (Integer pintar : numMaquina) {
                for (String string : boxMaquina) {
                    if (!string.equals(Integer.toString(pintar))) {
                        chkLoteria[pintar].setBackground(Color.red);

                    } else {
                        chkLoteria[pintar].setBackground(Color.green);
                        break;

                    }
                }
            }
            System.err.println(numMaquina);
            System.err.println(boxMaquina);
        }

        if (ae.getSource() == mnuReset) {
//            for (int i = numMaquina.size() - 1; i >= 0; i--) {
//                numMaquina.remove(i);
//                System.err.println(numMaquina);
//            }
            numMaquina.clear();
            boxMaquina.clear();
            for (int i = 0; i < chkLoteria.length; i++) {

                chkLoteria[i].setSelected(false);
                chkLoteria[i].setBackground(null);
            }

        }
        if(ae.getSource()==mnuGuardar){
            GrabarRecord g=new GrabarRecord(this);
            g.pack();
            g.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            g.setVisible(true);
        }

    }
}

//
//        if(ae.getSource()==btnAceptar){
//            boolean hayRepetido;
//            int numLoteria = 0;
//
//            System.out.println("¡¡LOTERÍA de "+maximo+" BOLAS!!");
//            for (int i = 0; i < maximo; i++) {
//                hayRepetido = true;
//                System.out.println("NEXT BOLA!");
//                while (hayRepetido) {
//                    hayRepetido = false;
//                    numLoteria = (int)(Math.random()*49)+1;
//                    for (Integer bola : numMaquina) {
//                        if (bola == numLoteria) {
//                            hayRepetido = true;
//                            System.out.println("REPETIDA "+numLoteria);
//                        }
//                    }
//
//                    numMaquina.add(numLoteria);
//                    System.out.println("BOLA Nº "+i+" --> "+numLoteria);
//
//                }
//          
//            }
//        }
//
//    }
