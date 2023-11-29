package db_connections;

class UpdatedDetails {
    private String newName;
    private String newContact;
    private String newemail;
    private String newAddress;

    public UpdatedDetails(String newName, String newContact, String newemail, String newAddress) {
        this.newName = newName;
        this.newContact = newContact;
        this.newemail = newemail;
        this.newAddress = newAddress;    
    }

    public String getNewName() {
        return newName;
    }

    public String getNewContact() {
        return newContact;
    }

    public String getNewemail() {
        return newemail;
    }

    public String getNewAddress() {
        return newAddress;
    }

}
