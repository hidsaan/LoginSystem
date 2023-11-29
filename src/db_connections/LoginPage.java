package db_connections;

import java.awt.Color;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

class LoginPage {
    
    JFrame f1=null;
    JPanel p1=null;
    JLabel heading, mail, pswd, forgotpsw;
    JTextField mailbox;
    JPasswordField pswdbox;
    JButton sbtn, rbtn, backbtn;
    
    LoginPage() throws ClassNotFoundException, SQLException{

        f1= new JFrame("Login");
        p1= new JPanel();
        
        f1.getContentPane().add(p1);
        
        heading= new JLabel("LOGIN");
        mail= new JLabel("Enter Email");
        pswd= new JLabel("Enter Password");
        
        forgotpsw= new JLabel("Forgot Password");
        forgotpsw.setForeground(Color.BLUE);
        //forgotpsw.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgotpsw.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                f1.setVisible(false);
                NewPassword newpsw= new  NewPassword();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        mailbox= new JTextField(30);
        pswdbox= new JPasswordField(16);
        
        sbtn= new JButton("Submit");
        sbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loginAction();
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
                        loginAction();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
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
                mailbox.setText("");
                pswdbox.setText("");
            }
        
        });
        
        backbtn= new JButton("Go Back");
        backbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    backbtnAction();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        // Setting layout manager to null
        p1.setLayout(null);

        // Adding each component with setBounds
        heading.setBounds(120, 20, 100, 30);
        
        mail.setBounds(20, 80, 100, 20);
        mailbox.setBounds(130, 80, 150, 20);
        
        pswd.setBounds(20, 120, 100, 20);
        pswdbox.setBounds(130, 120, 150, 20);
        
        sbtn.setBounds(50, 160, 100, 30);
        rbtn.setBounds(160, 160, 100, 30);
        forgotpsw.setBounds(50, 200, 150, 20);
        backbtn.setBounds(160, 200, 100, 30);
        
        
        
        //adding each component in order:
        p1.add(heading);
        p1.add(mail);
        p1.add(mailbox);
        p1.add(pswd);
        p1.add(pswdbox);
        p1.add(sbtn);
        p1.add(rbtn);
        p1.add(forgotpsw);
        p1.add(backbtn);

        f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f1.setLocation(600, 100);
        f1.setSize(300, 400);
        f1.setVisible(true);
        p1.setBackground(Color.PINK);

    }
    
    public void loginAction() throws ClassNotFoundException, SQLException{
       
        Mydb db= new Mydb();
        String email, password;
        email=mailbox.getText();
        password=pswdbox.getText();

        login_details logindetail=new login_details(email, password);

        boolean login_status= db.loginUser(logindetail);

        if (login_status) 
        {
            f1.setVisible(false);
            MainPage mainpage = new MainPage(email);
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, "Login Falied. Try Again.");
        } 
    }
    
    public void backbtnAction() throws ClassNotFoundException, SQLException{
        f1.setVisible(false);
        Manager manager= new Manager();
    }
    
}
