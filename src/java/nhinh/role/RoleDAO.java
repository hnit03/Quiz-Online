/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.role;

import java.io.Serializable;
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
public class RoleDAO implements Serializable{
    
    public String getRoleName(int roleID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String roleName = "";
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select roleName "
                        + "from Role "
                        + "where roleID = ?";
                        
                ps = con.prepareStatement(sql);
                ps.setInt(1, roleID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    roleName = rs.getString("roleName");
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
        return roleName;
    }
}
