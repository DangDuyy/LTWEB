package model;

import java.io.Serializable;
import java.util.Date;

public class UserModel implements Serializable {

    public static final long serialVersionUID = 1L;

    private int id;
    private String userName;
    private String email;
    private String fullName;
    private String password;
    private String images;
    private int roleId;
    private String phone;
    private Date createDate;

    // Default constructor
    public UserModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public UserModel(String userName, String email, String fullName, String password, String images, int roleId, String phone, Date createDate) {
        this.userName = userName;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.images = images;
        this.roleId = roleId;
        this.phone = phone;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", images='" + images + '\'' +
                ", roleId=" + roleId +
                ", phone='" + phone + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}