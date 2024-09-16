package dao.impl;

import config.DBConnectionMySQL;
import dao.IUserDao;
import dao.IUserServiceDao;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserServiceImpl implements IUserServiceDao {
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
        if (user!=null && password.equals(user.getPassword())){
            return user;
        }
        return null;
    }

    public static void main(String[] args) {
        try{
            IUserServiceDao userService = new UserServiceImpl();
            System.out.println(userService.login("duy","123"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
