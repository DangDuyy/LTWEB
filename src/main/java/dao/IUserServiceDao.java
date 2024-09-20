package dao;

import model.UserModel;

public interface IUserServiceDao {
    UserModel findByUsername(String userName);

    UserModel login(String userName, String password);

    boolean register(String userName, String password, String email, String fullname, String phone);

    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);

    boolean resetPass(String email, String password);
}
