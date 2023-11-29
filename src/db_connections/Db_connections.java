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


public class Db_connections {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        Manager manager= new Manager();

    }
    
}

class Manager
{
    JFrame f1=null;
    JPanel p1=null;
    JLabel heading;
    JButton loginbtn, signupbtn;
    
    Manager(){
        
        f1= new JFrame("Main Menu");
        p1= new JPanel();
        
        f1.getContentPane().add(p1);
        
        heading= new JLabel("WELCOME");
        
        loginbtn= new JButton("Login");
        loginbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loginbtnAction(e);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        signupbtn= new JButton("Register");
        signupbtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    regbtnAction(e);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //Layout:
        p1.setLayout(null);

        heading.setBounds(100, 20, 300, 40);
        loginbtn.setBounds(90, 80, 100, 40);
        signupbtn.setBounds(90, 140, 100, 40);
        
        //adding each component in order:
        p1.add(heading);
        p1.add(loginbtn);
        p1.add(signupbtn);

        f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f1.setLocation(600, 100);
        f1.setSize(300, 400);
        f1.setVisible(true);
        p1.setBackground(Color.gray);
    }
    
    public void loginbtnAction(ActionEvent e) throws ClassNotFoundException, SQLException{
        f1.setVisible(false);
        LoginPage loginpage= new LoginPage();
    }
    
    public void regbtnAction(ActionEvent e) throws ClassNotFoundException, SQLException{
        f1.setVisible(false);
        RegisterPage loginpage= new RegisterPage();
    }
}

/*class Manager
{
    Scanner sc=new Scanner (System.in);
    Mydb db= new Mydb();
    
    Manager() throws SQLException, ClassNotFoundException
    {
        boolean exitProgram = false;

        while (!exitProgram) {

            System.out.println("Welcome");

            System.out.println("To access our services, Press:");
            System.out.println("1: Register User");
            System.out.println("2: Login");
            System.out.println("3: Forgot Password");
            System.out.println("4: Edit Details");
            System.out.println("5: Delete Account");
            System.out.println("6: Exit");

            int response=sc.nextInt();

            switch (response)
            {
                case 1:
                    
                    RegisterPage registerpage= new RegisterPage();

                    System.out.println("Register User");
                    System.out.println("Enter Name:");
                    String name= sc.next();
                    System.out.println("Enter Contact:");
                    String contact= sc.next();
                    System.out.println("Enter Email:");
                    String email= sc.next();
                    System.out.println("Enter Address:");
                    String address= sc.next();
                    System.out.println("Enter Password:");
                    String password= sc.next();
                    System.out.println("Confirm Password:");
                    String confpassword= sc.next();

                    Details details=new Details(name, contact, email, address, password, confpassword);

                    boolean reg_status= db.registerUser(details);

                    if(reg_status)
                    {
                        System.out.println("Registeration Successful");
                    }
                    else
                    {
                        System.out.println("Regiteration Failed. Try Again Later.");
                    }

                break;

                case 2:

                    LoginPage loginpage= new LoginPage();

                    System.out.println("Enter Email:");
                    String lemail= sc.next();
                    System.out.println("Enter Password:");
                    String lpassword= sc.next();

                    //login_details ldet=new login_details(lemail, lpassword);

                    //boolean login_status= db.loginUser(ldet);

                    if (login_status) {
                        System.out.println("Login Successful");
                    } else {
                        System.out.println("Login Failed");
                    }               
                break;

                case 3:
                    
                    ForgotPassword forgotpassword= new ForgotPassword();
                                        
                    System.out.println("Enter Email:");
                    String forgotEmail = sc.next();
                    
                    if (db.userExists(forgotEmail)) 
                    {
                        NewPassword newpswd= new NewPassword();
                        /*String newPassword = generateNewPassword(sc);

                        boolean updatePasswordStatus = db.updatePassword(forgotEmail, newPassword);

                        if (updatePasswordStatus) 
                        {
                            System.out.println("Password reset successful");
                        } 
                        else 
                        {
                            System.out.println("Failed to reset password. Try Again Later.");
                        }
                    } 
                    else 
                    {
                        NoEmail noemail= new NoEmail();
                    }

                break;

                case 4:
                    
                    EditDetails editdetails= new EditDetails();

                    System.out.println("Edit Details");
                    System.out.println("Enter Email:");
                    
                    String updateEmail = sc.next();

                    if (db.userExists(updateEmail)) {
                        
                        NewDetails newdetails= new NewDetails();

                        System.out.println("Enter New Name:");
                        String newName = sc.next();
                        System.out.println("Enter New Contact:");
                        String newContact = sc.next();
                        System.out.println("Enter New Address:");
                        String newAddress = sc.next();
                        System.out.println("Enter New Password:");
                        String newPassword = sc.next();
                        System.out.println("Confirm New Password:");
                        String newConfPassword = sc.next();

                        UpdatedDetails updatedDetails = new UpdatedDetails(newName, newContact, updateEmail, newAddress, newPassword, newConfPassword);

                        boolean updateStatus = db.updateUser(updatedDetails);

                        if (updateStatus) 
                        {
                            System.out.println("Updated Successfully");
                        } 
                        else 
                        {
                            System.out.println("Failed to Update. Try Again Later.");
                        }
                    } 
                    else 
                    {
                        System.out.println("User does not exist.");
                    }
                break;

                case 5:
                    
                    DeleteUser del= new DeleteUser();
                    System.out.println("Enter Email:");
                    String delemail= sc.next();
                    System.out.println("Enter Password:");
                    String delpassword= sc.next();
                    
                    DeleteDetails deleteDetails = new DeleteDetails(delemail, delpassword);
                    
                    boolean deletestatus = db.deleteUser(deleteDetails);
                    if (deletestatus)
                    {
                        System.out.println("Deleted Successfully");
                    }
                    else
                    {
                        System.out.println("Failed to Delete");
                    }
                break;

                case 6:
                    System.out.println("Exiting the program");
                    exitProgram = true;
                break;

                default:
                    System.out.println("Invalid option. Please try again.");
                break;
            }
        }
    }
    
    private String generateNewPassword(Scanner scanner) {
        System.out.println("Enter the new password:");
        return scanner.next();    
    }
}*/
