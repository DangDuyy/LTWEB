package dao.impl;

import config.DBConnectionMySQL;
import dao.IUserDao;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements IUserDao {

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

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        UserModel user = userDao.findByUsername("duy");
        System.out.println(user);
    }
}