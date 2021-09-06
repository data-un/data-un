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
    JLabel L1, t1;
    JFrame f;

    public interfaz(){
        f = new JFrame();
        f.setLayout(null);
        f.setBounds(20,20,900,600);
        p = new JPanel();
        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        L1 = new JLabel();
        t1 = new JLabel();


        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(t1);
        f.add(p);

        p.setBounds(f.getX()-20,f.getY()-20,f.getWidth()-20,f.getHeight()-20);
        Color white = new Color(255,255,255,255);
        Color orange = new Color(255,143,0,255);
        p.setBackground(white);
        p.add(L1);

        b1.setBackground(orange);
        b2.setBackground(orange);
        b3.setBackground(orange);

        L1.setIcon(new ImageIcon(getClass().getResource("/dat/un/images/logo3.png")));
        L1.setBounds(100, 150, 300, 300);
        t1.setBounds(375, 375, 300, 250);

        t1.setVisible(false);

        b1.setBounds(125, 350, 200, 100);
        b2.setBounds(350, 350, 200, 100);
        b3.setBounds(575, 350, 200, 100);

        b1.setVisible(true);
        b2.setVisible(true);
        b3.setVisible(true);

        b1.setText("Agregar Caso");
        b2.setText("Revisar Datos");
        b3.setText("Edad promedio");

        b1.setForeground(white);
        b2.setForeground(white);
        b3.setForeground(white);

        b1.setFont(new Font("Arial", Font.PLAIN, 20));
        b2.setFont(new Font("Arial", Font.PLAIN, 20));
        b3.setFont(new Font("Arial", Font.PLAIN, 20));

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
        ActionListener edadPromedio = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    SQLiteConnect connect = new SQLiteConnect();
                    double mean = connect.get_birth_dates();

                    t1.setVisible(true);

                    t1.setText(String.format("Edad promedio de: %.2f", mean));
                } catch (Exception ex) {
                    System.out.println("error"+ex);;
                }

            }
        };
        b1.addActionListener(crear);
        b2.addActionListener(revisar);
        b3.addActionListener(edadPromedio);
        f.setVisible(true);
        f.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    }

}
