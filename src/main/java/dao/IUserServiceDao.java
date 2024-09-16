package dao;

import model.UserModel;

public interface IUserServiceDao {
    UserModel findByUsername(String userName);

    UserModel login(String userName, String password);
}
