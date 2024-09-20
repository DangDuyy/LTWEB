package dao.impl;

import config.DBConnectionMySQL;
import dao.IUserDao;
import dao.IUserServiceDao;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServiceImpl implements IUserServiceDao {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());
    IUserDao userDao = new UserDaoImpl();

    @Override
    public UserModel findByUsername(String username) {
        String sql = "select * from users where username = ?";
        UserModel user = null;

        try {
            Connection conn = DBConnectionMySQL.getDatabaseConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setImages(rs.getString("images"));
                user.setFullName(rs.getString("fullName"));
                user.setRoleId(rs.getInt("roleId"));
                user.setPhone(rs.getString("phone"));
                user.setCreateDate(rs.getDate("createDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public UserModel login(String userName, String password) {
        UserModel user = this.findByUsername(userName);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(String userName, String password, String email, String fullname, String phone) {
        if (userDao.checkExistUsername(userName))
            return false;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        userDao.insert(new UserModel(userName, email, fullname, password, null, 5, phone, date));
        return true;
    }

    @Override
    public boolean resetPass(String email, String password) {
        LOGGER.log(Level.INFO, "Attempting to reset password for email: {0}", email);
        if (!userDao.checkExistEmail(email)) {
            LOGGER.log(Level.WARNING, "Email does not exist: {0}", email);
            return false;
        }
        userDao.updatePassword(email, password);
        LOGGER.log(Level.INFO, "Password reset successful for email: {0}", email);
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }

    public static void main(String[] args) {
        try {
            IUserServiceDao userService = new UserServiceImpl();
            System.out.println(userService.login("duy", "123"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}