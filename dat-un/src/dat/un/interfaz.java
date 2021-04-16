/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.un;

import java.awt.Color;
import javax.swing.*;

/**
 *
 * @author vimad
 */
public class interfaz extends javax.swing.JFrame{
    
    JPanel p;
    
    public interfaz(){
        initComponents();
    }
    
    private void initComponents(){
        this.setLayout(null);
        this.setBounds(20,20,1200,700);
        this.setDefaultCloseOperation(3);
        p = new JPanel();
        this.add(p);
        p.setBounds(0,0,this.getWidth()-this.getX(),this.getHeight()-this.getY());
        p.setBackground(Color.white);
        
    }
    
    
    
}
