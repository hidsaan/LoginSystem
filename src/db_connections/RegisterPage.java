package db_connections;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


class RegisterPage {
    JFrame f1=null;
    JPanel p1=null;
    JLabel heading, name, contact, email, address, pswd, confpswd ;
    JTextField namebox, contactbox, emailbox, addressbox;
    JPasswordField pswdbox, confpswdbox;
    JButton sbtn, rbtn, backbtn;
    
    RegisterPage(){
        f1= new JFrame("Register");
        p1= new JPanel();
        
        f1.getContentPane().add(p1);
        
        heading= new JLabel("REGISTER");
        name= new JLabel("Enter Name");
        contact= new JLabel("Enter Contact");
        email= new JLabel("Enter Email");
        address= new JLabel("Enter Address");
        pswd= new JLabel("Enter Password");
        confpswd= new JLabel("Confirm Password");
        
        namebox= new JTextField(30);
        contactbox= new JTextField(15);
        emailbox= new JTextField(30);
        addressbox= new JTextField(30);

        pswdbox= new JPasswordField(16);
        confpswdbox= new JPasswordField(16);
        
        sbtn= new JButton("Submit");
        sbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    regAction();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        sbtn.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER)
                {
                    try {
                        regAction();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(RegisterPage.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(RegisterPage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        
        rbtn= new JButton("Reset");
        rbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                namebox.setText("");
                contactbox.setText("");
                emailbox.setText("");
                addressbox.setText("");
                pswdbox.setText("");
                confpswdbox.setText("");
            }
        
        });
        
        backbtn= new JButton ("Go Back");
        backbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    backbtnAction(e);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        Color newcolors= new Color(126, 217, 87);
        
        p1.setLayout(null);

        heading.setBounds(150, 20, 100, 20);
        
        name.setBounds(50, 60, 100, 20);
        namebox.setBounds(200, 60, 150, 20);
        
        contact.setBounds(50, 100, 100, 20);
        contactbox.setBounds(200, 100, 150, 20);
        
        email.setBounds(50, 140, 100, 20);
        emailbox.setBounds(200, 140, 150, 20);
        
        address.setBounds(50, 180, 100, 20);
        addressbox.setBounds(200, 180, 150, 20);
        
        pswd.setBounds(50, 220, 100, 20);
        pswdbox.setBounds(200, 220, 150, 20);
        
        confpswd.setBounds(50, 260, 150, 20);
        confpswdbox.setBounds(200, 260, 150, 20);
        
        sbtn.setBounds(50, 300, 100, 30);
        rbtn.setBounds(160, 300, 100, 30);
        backbtn.setBounds(270, 300, 100, 30);
        
        p1.add(heading);
        
        p1.add(name);
        p1.add(namebox);
        
        p1.add(contact);
        p1.add(contactbox);
        
        p1.add(email);
        p1.add(emailbox);
        
        p1.add(address);
        p1.add(addressbox);
        
        p1.add(pswd);
        p1.add(pswdbox);
        
        p1.add(confpswd);
        p1.add(confpswdbox);
        
        p1.add(sbtn);
        p1.add(rbtn); 
        p1.add(backbtn); 

        f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f1.setLocation(600, 100);
        f1.setSize(400, 500);
        f1.setVisible(true);
        p1.setBackground(newcolors);

    }
    
    public void regAction() throws ClassNotFoundException, SQLException{
        Mydb db= new Mydb();

        String name, contact, email, address, password, confpassword;
        name=namebox.getText();
        contact=contactbox.getText();
        email=emailbox.getText();
        address=addressbox.getText();
        password=pswdbox.getText();
        confpassword=confpswdbox.getText();

        Details details=new Details(name, contact, email, address, password, confpassword);

        boolean reg_status= db.registerUser(details);

        if (reg_status) 
        {
            f1.setVisible(false);
            MainPage mainpage = new MainPage(name);                
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Registeration Falied. Try Again.");
        } 
    }
    
    public void backbtnAction(ActionEvent e) throws ClassNotFoundException, SQLException{
        f1.setVisible(false);
        Manager manager= new Manager();
    }
}
