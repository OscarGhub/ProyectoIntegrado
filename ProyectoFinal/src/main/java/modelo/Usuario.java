package modelo;

public class Usuario {
    private String username;
    private String password;
    private String gmail;

    public Usuario(String username, String password, String gmail) {
        this.username = username;
        this.password = password;
        this.gmail = gmail;
    }
    public Usuario() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }
}
