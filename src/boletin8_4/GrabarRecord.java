/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boletin8_4;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

/**
 *
 * @author fidi
 */
@SuppressWarnings ("serial")
public class GrabarRecord extends JDialog implements ActionListener{
    
   JTextField txfIdentificador;
   JButton btnGuardar,btnCancelar;
   JLabel lblNombre;

    public GrabarRecord(Programa8_4 f) {
        super(f,true);
        setLayout(new FlowLayout());
        setTitle("Guardar Record Primitiva");
        
        //JTextField
        txfIdentificador = new JTextField(9);
        lblNombre = new JLabel("Nombre: ");
        txfIdentificador.setSize(txfIdentificador.getPreferredSize());
        lblNombre.setSize(lblNombre.getPreferredSize());
        add(lblNombre);
        add(txfIdentificador);
        
        //JButton btnGuardar
        btnGuardar = new JButton("Guardar");
        btnGuardar.setSize(btnGuardar.getPreferredSize());
        btnGuardar.addActionListener(this);
        add(btnGuardar);
        
        //JButton btnCancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setSize(btnCancelar.getPreferredSize());
        btnCancelar.addActionListener(this);
        add(btnCancelar);
                
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Programa8_4 principal=(Programa8_4)this.getOwner();
        String ruta = System.getProperty("user.home");
        File archivo= new File(ruta);
        if(ae.getSource()==btnGuardar){       
        try (PrintWriter rc=new PrintWriter(new FileWriter(archivo+"/record.txt", true))){
            rc.println(txfIdentificador.getText()+","+principal.cont);
            
        } catch (Exception e) {
            System.err.println("Error de Acceso al Archivo");
        }
        
        }
        if(ae.getSource()==btnCancelar){
            
        }
    }
}
