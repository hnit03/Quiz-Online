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
import nhinh.utils.SHA256;

/**
 *
 * @author PC
 */
public class RegistrationDAO implements Serializable {

    public int checkLogin(String email, String password) throws SQLException, NamingException, NoSuchAlgorithmException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int roleID = -1;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select statusID,email, roleID "
                        + "from Registration "
                        + "where email = ? and password = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int statusID = rs.getInt("statusID");
                    if (statusID == 1) {
                        String id = rs.getString("email");
                        if (email.equals(id)) {
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
}
