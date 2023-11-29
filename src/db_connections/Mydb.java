package db_connections;

import java.sql.*;

public class Mydb {
    
    Connection con=null;

    PreparedStatement st =null;

    //PreparedStatement pst= null;

    public Mydb() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/servicedb", "root", "");
        
        System.out.println("Connection Established");
    }

    public boolean registerUser(Details details) throws SQLException
    {
        String qry="insert into register values('"+details.getName()+"','"+details.getContact()+"','"+details.getEmail()+"','"+details.getAddress()+"','"+details.getPassword()+"','"+details.getConfpassword()+"')";
        st= con.prepareStatement(qry);
        int row= st.executeUpdate();
        
        if (row>0)
        {
            String qry1="insert into login values('"+details.getEmail()+"','"+details.getPassword()+"')";
            st= con.prepareStatement(qry1);
            int row1= st.executeUpdate();
            
            return true;
        }
        
        else
        {
            return false;
        }
    }
    
    boolean loginUser(login_details logindetail) throws SQLException {
    String qry = "SELECT * FROM login WHERE email=? AND password=?";
        st = con.prepareStatement(qry);
        
        st.setString(1, logindetail.getEmail());
        st.setString(2, logindetail.getPassword());
    
        ResultSet rs = st.executeQuery();

        if (rs.next()) 
        {
            return true;
        } 
        else 
        {
            return false;
        }    
    }
    
    public boolean updatePassword(String newemail, String newPassword, String confpswd) throws SQLException {
        String qry = "update register set password='"+newPassword+"', confpassword='"+confpswd+"' where email='"+newemail+"'";
        st = con.prepareStatement(qry);
        int row = st.executeUpdate();
        
        if (row>0)
        {
            String qry1="update login set password='"+newPassword+"' where email='"+newemail+"'";
            st= con.prepareStatement(qry1);
            int row1= st.executeUpdate();
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /*public boolean userExists(String newemail) throws SQLException {
        String qry = "select * from register where email='"+newemail+"'";
        st = con.prepareStatement(qry);
        ResultSet rs = st.executeQuery();

        if (rs.next()) 
        {
            return true;
        } 
        else 
        {
            return false;
        }    
    }*/
    
    boolean updateUser(UpdatedDetails updatedDetails)throws SQLException {
        String qry = "update register set name='"+updatedDetails.getNewName()+"', contact='"+updatedDetails.getNewContact()+"', address='"+updatedDetails.getNewAddress()+"' where email='"+updatedDetails.getNewemail()+"'";
        st = con.prepareStatement(qry);
        int row = st.executeUpdate();

        if (row>0)
        {
            return true;
        }
        else
        {
            return false;
        }    
    }

    public boolean deleteUser(DeleteDetails deleteDetails) throws SQLException
    {
        String qry = "delete from register where email='"+deleteDetails.getDelemail()+"'";
        st = con.prepareStatement(qry);
        int row = st.executeUpdate();
        
        if (row>0)
        {
            String qry1="delete from login where email='"+deleteDetails.getDelemail()+"'";
            st= con.prepareStatement(qry1);
            int row1= st.executeUpdate();
            return true;
        }
        else
        {
            return false;
        }
    }  
}
