/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.registration;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import nhinh.utils.DBHelper;

/**
 *
 * @author PC
 */
public class RegistrationDAO implements Serializable {

    public int checkLogin(String email, String password) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int roleID = -1;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select statusID,email,password, roleID "
                        + "from Registration "
                        + "where email = ? and password = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String id = rs.getString("email");
                    String pass = rs.getString("password");
                    if (email.equals(id) && password.equals(pass)) {
                        int statusID = rs.getInt("statusID");
                        if (statusID == 1) {
                            roleID = rs.getInt("roleID");
                        }
                    }
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return roleID;
    }

    public String getFullname(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String fullname = "";
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select fullname "
                        + "from Registration "
                        + "where email = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    fullname = rs.getString("fullname");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return fullname;
    }

    public boolean createNewAccount(RegistrationDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into Registration(email,password,fullname,roleID,statusID) "
                        + "values(?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getEmail());
                ps.setString(2, dto.getPassword());
                ps.setString(3, dto.getFullname());
                ps.setInt(4, dto.getRoleID());
                ps.setInt(5, dto.getStatusID());
                int success = ps.executeUpdate();
                if (success == 1) {
                    return true;
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean checkEmailDup(String email) throws SQLException, NamingException, NoSuchAlgorithmException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select email "
                        + "from Registration "
                        + "where email = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public RegistrationDTO getRegistrationDTO(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        RegistrationDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select fullname,password,roleID,statusID "
                        + "from Registration "
                        + "where email = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String fullname = rs.getString("fullname");
                    String password = rs.getString("password");
                    int roleID = rs.getInt("roleID");
                    int statusID = rs.getInt("statusID");
                    dto = new RegistrationDTO(email, password, fullname, roleID, statusID);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }
}
