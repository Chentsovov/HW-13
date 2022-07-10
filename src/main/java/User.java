import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User userData = (User) o;
        return id == userData.id && Objects.equals(name, userData.name) && Objects.equals(username, userData.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username);
    }

    @Override
    public String toString() {
        return  " Users data : "
                + "\n id       = "        + id
                + "\n name     = "      + name
                + "\n username = "  + username
                + "\n email    = "     + email
                + "\n address  = "   + address
                + "\n phone    = "     + phone
                + "\n website  = "   + website
                + "\n company  = "   + company
                + "\n" + "-".repeat(130);
    }
}