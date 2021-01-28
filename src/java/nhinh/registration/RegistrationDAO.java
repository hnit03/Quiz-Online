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
                String sql = "select statusID,email, password, roleID "
                        + "from Registration "
                        + "where email = ? and password = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                SHA256 sha = new SHA256();
                String pw = sha.bytesToHex(password);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    int statusID = rs.getInt("statusID");
                    if (statusID == 1) {
                        String id = rs.getString("email");
                        String pass = rs.getString("password");
                        if (email.equals(id) && password.equals(pass)) {
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

}
