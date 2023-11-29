package db_connections;

class Details {
    private String name;
    private String contact;
    private String email;
    private String address;
    private String password;
    private String confpassword;

    public Details(String name, String contact, String email, String address, String password, String confpassword) {
        this.name = name;
        this.contact = contact;
        this.email = email;
        this.address = address;
        this.password = password;
        this.confpassword = confpassword;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getConfpassword() {
        return confpassword;
    }

}
