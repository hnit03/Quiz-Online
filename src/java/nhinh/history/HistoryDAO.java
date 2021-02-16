/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.history;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nhinh.registration.RegistrationDAO;
import nhinh.registration.RegistrationDTO;
import nhinh.subject.SubjectDAO;
import nhinh.subject.SubjectDTO;
import nhinh.utils.DBHelper;

/**
 *
 * @author PC
 */
public class HistoryDAO implements Serializable {

    public boolean insertHistory(String email, String subjectID, int numOfCorrect, float total, String createDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into History(historyID,email,subjectID,numOfCorrect,total,createDate) "
                        + "values(NEWID(),?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, subjectID);
                ps.setInt(3, numOfCorrect);
                ps.setFloat(4, total);
                ps.setString(5, createDate);
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

    public boolean updateHistory(String historyID, int numOfCorrect, float total) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update History "
                        + "set numOfCorrect = ?, total = ? "
                        + "where historyID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, numOfCorrect);
                ps.setFloat(2, total);
                ps.setString(3, historyID);
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

    public String getHistoryID(String email, String subjectID, String createDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String historyID = "";
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select historyID "
                        + "from History "
                        + "where email = ? and subjectId = ? and createDate = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, subjectID);
                ps.setString(3, createDate);
                rs = ps.executeQuery();
                if (rs.next()) {
                    historyID = rs.getString("historyID");
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
        return historyID;
    }

    private List<HistoryDTO> listHistory;
    public List<HistoryDTO> getListHistory(){
        return listHistory;
    }
    public void getListOfHistory(String email, String subjectID, String createDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        RegistrationDAO rdao = new RegistrationDAO();
        SubjectDAO sdao = new SubjectDAO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select historyID,numOfCorrect, total "
                        + "from History "
                        + "where email like ? and subjectId like ? and createDate like ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + email + "%");
                ps.setString(2, "%" + subjectID + "%");
                ps.setString(3, "%" + createDate + "%");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String historyID = rs.getString("historyID");
                    int numOfCorrect = rs.getInt("numOfCorrect");
                    float total = rs.getFloat("total");
                    
                    RegistrationDTO rdto = rdao.getRegistrationDTO(email);
                    
                    SubjectDTO sdto = sdao.getSubjectDTO(subjectID);
                    
                    HistoryDTO dto = new HistoryDTO(historyID, rdto, sdto, numOfCorrect, total, createDate);
                    if (this.listHistory == null) {
                        this.listHistory = new ArrayList<>();
                    }
                    this.listHistory.add(dto);
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
}
