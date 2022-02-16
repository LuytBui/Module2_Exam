package model;

public class Contact {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private String email;
    private String facebook;

    public Contact() {
    }

    public Contact(String firstName, String lastName, String phone, String address, String email, String facebook) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.facebook = facebook;
    }

    public Contact(String firstName, String lastName, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = "";
        this.email = "";
        this.facebook = "";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FullName=" + getFullName());
        sb.append(" | Number=" + phone);

        if (address.length() > 0)
            sb.append(" | Address=" + address);
        if (email.length() > 0)
            sb.append(" | Email=" + email);
        if (facebook.length() > 0)
            sb.append(" | Facebook=" + facebook);

        return sb.toString();
    }
    public void copyInfo(Contact contact){
        this.firstName = contact.getFirstName();
        this.lastName = contact.getLastName();
        this.phone = contact.getPhone();
        this.email = contact.getEmail();
        this.address = contact.getAddress();
        this.facebook = contact.getFacebook();
    }

    public String toCSVString(){
        return firstName + ", "
                + lastName + ", "
                + phone + ", "
                + address + ", "
                + email + ", "
                + facebook + ", ";
    }
}
