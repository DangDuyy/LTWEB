package dao;

import model.UserModel;

public interface IUserDao {
    UserModel findByUsername(String userName);

    void updatePassword(String email,String password);
    void insert(UserModel user);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
}