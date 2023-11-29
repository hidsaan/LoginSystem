package db_connections;

class DeleteDetails {
    private String delemail;
    private String delpassword;

    public DeleteDetails(String delemail, String delpassword) {
        this.delemail = delemail;
        this.delpassword = delpassword;
    }

    public String getDelemail() {
        return delemail;
    }

    public String getDelpassword() {
        return delpassword;
    }
}
