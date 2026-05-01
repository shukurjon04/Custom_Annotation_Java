package model;

public class Check {
    private boolean isUserName;
    private boolean isPhone;
    private boolean isEmail;
    private boolean isPassword;

    public Check(boolean isUserName, boolean isPhone, boolean isEmail, boolean isPassword) {
        this.isUserName = isUserName;
        this.isPhone = isPhone;
        this.isEmail = isEmail;
        this.isPassword = isPassword;
    }

    public Check() {
    }

    public boolean isUserName() {
        return isUserName;
    }

    public void setUserName(boolean userName) {
        isUserName = userName;
    }

    public boolean isPhone() {
        return isPhone;
    }

    public void setPhone(boolean phone) {
        isPhone = phone;
    }

    public boolean isEmail() {
        return isEmail;
    }

    public void setEmail(boolean email) {
        isEmail = email;
    }

    public boolean isPassword() {
        return isPassword;
    }

    public void setPassword(boolean password) {
        isPassword = password;
    }
}
