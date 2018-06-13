/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boletin8_4;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import javax.swing.JDialog;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author fidi
 */
public class VerRecord extends JDialog {

    JTextArea txaRecords;
    JScrollPane srcTexto;
    String t;
    String ruta = System.getProperty("user.home");
    File archivo = new File(ruta + "/record.txt");

    public VerRecord(Programa8_4 vr) {
        super(vr, true);
        setLayout(null);

        //JTextArea txaRecords
        txaRecords = new JTextArea();
        srcTexto = new JScrollPane(txaRecords);
        srcTexto.setSize(150, 150);
        srcTexto.setLocation(15, 15);
        add(srcTexto);

        try (Scanner fc = new Scanner(archivo)) {
            while (fc.hasNext()) {
                t = fc.nextLine();
                txaRecords.append(t + "\n");
            }
        } catch (Exception e) {
            System.err.println("Error de acceso al archivo");
        }

    }

}
