public class RegistrationInfo {

    private final String username;
    private final String password;
    private final String status;

    public RegistrationInfo(String username, String password, String status){
        this.username = username;
        this.password = password;
        this.status = status;
    }

    public String getName() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getStatus() {return status;}

}
