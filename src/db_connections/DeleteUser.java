package db_connections;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;


class DeleteUser {
    JFrame f1=null;
    JPanel p1=null;
    JLabel heading, delmail, delpswd;
    JTextField delmailbox;
    JPasswordField delpswdbox;
    JButton sbtn, rbtn;
    
    DeleteUser(){
        f1= new JFrame("Delete User");
        p1= new JPanel();
        
        f1.getContentPane().add(p1);
        
        heading= new JLabel("DELETE USER");
        delmail= new JLabel("Enter Email");
        delpswd= new JLabel("Enter Password");
        
        delmailbox= new JTextField(10);
        delpswdbox= new JPasswordField(10);
        
        Color newcolors= new Color(177, 129, 181);
        
        sbtn= new JButton("Submit");
        sbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    delAction();
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
                        delAction();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(DeleteUser.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(DeleteUser.class.getName()).log(Level.SEVERE, null, ex);
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
                delmail.setText("");
                delpswd.setText("");
            }
        
        });

        p1.setLayout(null);

        heading.setBounds(120, 20, 150, 20);

        delmail.setBounds(20, 60, 150, 20);
        delmailbox.setBounds(130, 60, 150, 20);

        delpswd.setBounds(20, 100, 150, 20);
        delpswdbox.setBounds(130, 100, 150, 20);

        sbtn.setBounds(50, 140, 100, 30);
        rbtn.setBounds(160, 140, 100, 30);
        
        p1.add(heading);
        
        p1.add(delmail);
        p1.add(delmailbox);
        
        p1.add(delpswd);
        p1.add(delpswdbox);
        
        p1.add(sbtn);
        p1.add(rbtn);

        f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f1.setLocation(600, 100);
        f1.setSize(300, 400);
        f1.setVisible(true);
        p1.setBackground(newcolors);
    }
    
    public void delAction() throws ClassNotFoundException, SQLException{
        Mydb db= new Mydb();

        String delemail, delpassword;
        delemail=delmailbox.getText();
        delpassword=delpswdbox.getText();

        DeleteDetails deleteDetails = new DeleteDetails(delemail, delpassword);

        boolean deletestatus = db.deleteUser(deleteDetails);
        if (deletestatus)
        {
            f1.setVisible(false);
            Manager manager= new Manager();            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Failed to Delete. Try Again.");
        }
    }
}
