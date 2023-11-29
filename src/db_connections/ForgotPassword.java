package db_connections;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

class NewPassword{
    JFrame f1=null;
    JPanel p1=null;
    JLabel heading, fgemail, newpswd, confnew ;
    JTextField emailbox;
    JPasswordField newpswdbox, confnewbox;
    JButton sbtn, rbtn;
    
    NewPassword(){
        f1= new JFrame("Forgot Password");
        p1= new JPanel();
        
        f1.getContentPane().add(p1);
        
        heading= new JLabel("FORGOT PASSWORD");
        fgemail= new JLabel("Enter Your Email");
        newpswd= new JLabel("Enter New Password");
        confnew= new JLabel("Confirm New Password");
        
        emailbox= new JTextField(10);

        newpswdbox= new JPasswordField(10);
        confnewbox= new JPasswordField(10);
        
        sbtn= new JButton("Submit");       
        rbtn= new JButton("Reset");
        Color newcolors= new Color(217, 179, 132);

        p1.setLayout(null);

        heading.setBounds(80, 20, 200, 20);
        
        fgemail.setBounds(20, 60, 150, 20);
        emailbox.setBounds(160, 60, 150, 20);
        
        newpswd.setBounds(20, 100, 150, 20);
        newpswdbox.setBounds(160, 100, 150, 20);
        
        confnew.setBounds(20, 140, 150, 20);
        confnewbox.setBounds(160, 140, 150, 20);
        
        sbtn.setBounds(50, 180, 100, 30);
        rbtn.setBounds(160, 180, 100, 30);
        
        p1.add(heading);
        
        p1.add(fgemail);
        p1.add(emailbox);
        
        p1.add(newpswd);
        p1.add(newpswdbox);
        
        p1.add(confnew);
        p1.add(confnewbox);
        
        p1.add(sbtn);
        sbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    pswAction();
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
                        pswAction();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(NewPassword.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(NewPassword.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        
        p1.add(rbtn);
        rbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                emailbox.setText("");
                newpswdbox.setText("");
            }
        
        });

        f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f1.setLocation(600, 100);
        f1.setSize(350, 500);
        f1.setVisible(true);
        p1.setBackground(newcolors);
    }
    
    public void pswAction() throws ClassNotFoundException, SQLException{
        Mydb db= new Mydb();

        String newPassword, forgotEmail, confpswd;
        forgotEmail=emailbox.getText();
        newPassword=newpswdbox.getText();
        confpswd=confnewbox.getText();

        boolean updatePasswordStatus = db.updatePassword(forgotEmail, newPassword,confpswd );

        if (updatePasswordStatus) 
        {
            f1.setVisible(false);
            LoginPage loginpage= new LoginPage();            
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Password Reset Failed. Try Again.");
        }  
    }
}