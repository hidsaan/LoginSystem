package db_connections;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

class NewDetails {
    JFrame f1=null;
    JPanel p1=null;
    JLabel heading, newname, newcontact, oldemaill, newaddress;
    JTextField newnamebox, newcontactbox, oldemailbox, newaddressbox;
    JButton sbtn, rbtn;
    
    NewDetails(){
        f1= new JFrame("Edit Details");
        p1= new JPanel();
        
        f1.getContentPane().add(p1);
        
        heading= new JLabel("EDIT DETAILS");
        oldemaill= new JLabel("Enter Old Email");
        newname= new JLabel("Enter New Name");
        newcontact= new JLabel("Enter New Contact");
        newaddress= new JLabel("Enter New Address");
        
        oldemailbox= new JTextField(30);
        newnamebox= new JTextField(20);
        newcontactbox= new JTextField(15);
        newaddressbox= new JTextField(30);
        
        Color newcolors= new Color(171, 181, 129);
        
        sbtn= new JButton("Submit");
        rbtn= new JButton("Reset");
        
        p1.setLayout(null);

        heading.setBounds(150, 20, 200, 20);

        oldemaill.setBounds(50, 60, 150, 20);
        oldemailbox.setBounds(200, 60, 150, 20);

        newname.setBounds(50, 100, 150, 20);
        newnamebox.setBounds(200, 100, 150, 20);

        newcontact.setBounds(50, 140, 150, 20);
        newcontactbox.setBounds(200, 140, 150, 20);

        newaddress.setBounds(50, 180, 150, 20);
        newaddressbox.setBounds(200, 180, 150, 20);

        sbtn.setBounds(50, 220, 100, 30);
        rbtn.setBounds(160, 220, 100, 30);
        
        p1.add(heading);
        
        p1.add(oldemaill);
        p1.add(oldemailbox);

        p1.add(newname);
        p1.add(newnamebox);
        
        p1.add(newcontact);
        p1.add(newcontactbox);
        
        p1.add(newaddress);
        p1.add(newaddressbox);
        
        p1.add(sbtn);
        sbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    editAction();
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
                        editAction();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(NewDetails.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(NewDetails.class.getName()).log(Level.SEVERE, null, ex);
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
                oldemailbox.setText("");
                newnamebox.setText("");
                newcontactbox.setText("");
                newaddressbox.setText("");
            }
        
        });

        f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f1.setLocation(600, 100);
        f1.setSize(400, 500);
        f1.setVisible(true);
        p1.setBackground(newcolors);
    }
    
    public void editAction() throws ClassNotFoundException, SQLException{
        Mydb db= new Mydb();
        
        String newName, newContact, newemail, newAddress;
        newName=newnamebox.getText();
        newContact=newcontactbox.getText();
        newemail=oldemailbox.getText();
        newAddress=newaddressbox.getText();

        UpdatedDetails updatedDetails = new UpdatedDetails(newName, newContact, newemail, newAddress);

        boolean updateStatus = db.updateUser(updatedDetails);

        if (updateStatus) 
        {
            JOptionPane.showMessageDialog(null, "Details edited successfully.");
            f1.setVisible(false);
            MainPage mainpage= new MainPage(newName);             
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Failed to Update Details. Try Again.");
        }
    }
}