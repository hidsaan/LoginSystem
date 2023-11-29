
package db_connections;

class login_details {
    private String email; 
    private String password;

    public login_details(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

   
}
