/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.subject;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nhinh.utils.DBHelper;

/**
 *
 * @author PC
 */
public class SubjectDAO implements Serializable {

    private List<SubjectDTO> subjectList;

    public List<SubjectDTO> getSubjectList() {
        return subjectList;
    }

    public void getAllSubject() throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select subjectID,subjectName,time "
                        + "from Subject";
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String subjectID = rs.getString("subjectID");
                    String subjectName = rs.getString("subjectName");
                    String time = rs.getString("time");
                    SubjectDTO dto = new SubjectDTO(subjectID, subjectName,time);
                    if (this.subjectList == null) {
                        this.subjectList = new ArrayList<>();
                    }
                    this.subjectList.add(dto);
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
    }

    public SubjectDTO getSubjectDTO(String sID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        SubjectDTO dto =null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select subjectID,subjectName,time "
                        + "from Subject "
                        + "where subjectID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, sID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String subjectID = rs.getString("subjectID");
                    String subjectName = rs.getString("subjectName");
                    String time = rs.getString("time");
                    dto = new SubjectDTO(subjectID, subjectName,time);
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
    
    public boolean createNewSubject(String subjectName, String time) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into Subject(subjectID,subjectName,time) "
                        + "values(NEWID(),?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectName);
                ps.setString(2, time);

                int row = ps.executeUpdate();
                if (row > 0) {
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
