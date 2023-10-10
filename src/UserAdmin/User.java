package UserAdmin;

public class User {
    private int ID;
    private String name;
    private String pasword;

    User(int ID) {
        setID();
    }

    User(int ID, String name, String pasword) {
        setID();
        setName();
        setPasword();

    }

    private void setID() {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    private void setPasword() {
        this.pasword = pasword;
    }

    public String getPasword() {
        return pasword;
    }

    private void setName() {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID: " + ID +
                ", name: '" + name + '\'' +
                ", pasword: '" + pasword + '\'' +
                '}';
    }
}
