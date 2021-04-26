/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.un;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author vimad
 */
public class interfaz {
    
    JPanel p;
    JButton b1, b2, b3; 
    JLabel L1;
    JFrame f;
    
    public interfaz(){
        f = new JFrame();
        f.setLayout(null);
        f.setBounds(20,20,400,600);
        p = new JPanel();
        b1 = new JButton();
        b2 = new JButton();
        L1 = new JLabel();
        
        
        f.add(b1);
        f.add(b2);
        f.add(p);
        
        p.setBounds(f.getX()-20,f.getY()-20,f.getWidth(),f.getHeight());
        Color white = new Color(255,255,255,255);
        Color orange = new Color(255,143,0,255);
        p.setBackground(white);
        p.add(L1);
        b1.setBackground(orange);
        b2.setBackground(orange);
        L1.setIcon(new ImageIcon(getClass().getResource("/dat/un/images/logo3.png")));
        L1.setBounds(100,100,180,180);
        b1.setBounds(100, 200, 200,100);
        b2.setBounds(100, 350, 200,100);
        b1.setVisible(true);
        b2.setVisible(true);
        b1.setText("Agregar Caso");
        b2.setText("Revisar Datos");
        b1.setForeground(white);
        b2.setForeground(white);
        b1.setFont(new Font("Arial", Font.PLAIN, 25));
        b2.setFont(new Font("Arial", Font.PLAIN, 25));
        ActionListener crear = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    Formulario form = new Formulario(3);
                    form.setVisible(true);
                    f.dispose();
                } catch (Exception ex) {
                    System.out.println("error"+ex);;
                }
                
            }
        };
        ActionListener revisar = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    Revision rev = new Revision();
                    rev.setVisible(true);
                    f.dispose();
                } catch (Exception ex) {
                    System.out.println("error"+ex);;
                }
                
            }
        };
        b1.addActionListener(crear);
        b2.addActionListener(revisar);
        f.setVisible(true);
        f.setDefaultCloseOperation(3);
        
    }
    
}
