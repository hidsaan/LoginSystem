package db_connections;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

class MainPage {
    JFrame f1=null;
    JPanel p1=null;
    JLabel heading, manage;
    JButton editusr, deluser, logout;
    
    MainPage(String username){
        f1= new JFrame("Main");
        p1= new JPanel();
        
        f1.getContentPane().add(p1);
        
        heading= new JLabel("WELCOME "+username);
        
        manage= new JLabel("Manage Your Account:");
        
        Color newcolors= new Color(129, 164, 181);
        
        editusr= new JButton("Edit Details");
        editusr.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.setVisible(false);
                NewDetails newdetails= new NewDetails();
            }
        });
        
        deluser= new JButton("Delete Account");
        deluser.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.setVisible(false);
                DeleteUser deluser= new DeleteUser();
            }
        });
        
        logout= new JButton("Logout");
        logout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                f1.setVisible(false);
                JOptionPane.showMessageDialog(null, "You've been logged out.");
                Manager manager= new Manager();
            }
        });
        
        p1.setLayout(null);

        heading.setBounds(100, 20, 300, 40);
        manage.setBounds(80, 70, 300, 20);
        editusr.setBounds(80, 110, 150, 30);
        deluser.setBounds(80, 160, 150, 30);
        logout.setBounds(80, 210, 150, 30);
        
        p1.add(heading);
        p1.add(editusr);
        p1.add(deluser);
        p1.add(logout);
              
        f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f1.setLocation(600, 100);
        f1.setSize(300, 400);
        f1.setVisible(true);
        p1.setBackground(newcolors);
    }
}
