package dao;

import model.UserModel;

public interface IUserDao {
    UserModel findByUsername(String userName);
}