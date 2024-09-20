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
        String sql = "SELECT * FROM users WHERE username = ?";
        UserModel user = null;

        try (Connection conn = DBConnectionMySQL.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void updatePassword(String email, String newPassword) {
        String sql = "UPDATE users SET password = ? WHERE email = ?";
        try (Connection conn = DBConnectionMySQL.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

            @Override
    public void insert(UserModel user) {
        String sql = "INSERT INTO users (email, username, fullname, password, images, roleid, phone, createdate) VALUES (?,?,?,?,?,?,?,?)";
        try (Connection conn = DBConnectionMySQL.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getUserName());
            ps.setString(3, user.getFullName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getImages());
            ps.setInt(6, user.getRoleId());
            ps.setString(7, user.getPhone());
            ps.setDate(8, new java.sql.Date(user.getCreateDate().getTime())); // Convert java.util.Date to java.sql.Date
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DBConnectionMySQL.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DBConnectionMySQL.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkExistPhone(String phone) {
        String query = "SELECT * FROM users WHERE phone = ?";
        try (Connection conn = DBConnectionMySQL.getDatabaseConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, phone);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();
        UserModel user = userDao.findByUsername("duy");
        System.out.println(user);
    }
}