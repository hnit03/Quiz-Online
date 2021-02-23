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

    public boolean insertHistory(String email, String subjectID, int numOfCorrect, int totalQuestion, float total, String createDate) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into History(historyID,email,subjectID,numOfCorrect,totalQuestion,total,createDate) "
                        + "values(NEWID(),?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, subjectID);
                ps.setInt(3, numOfCorrect);
                ps.setInt(4, totalQuestion);
                ps.setFloat(5, total);
                ps.setString(6, createDate);
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

    public List<HistoryDTO> getListHistory() {
        return listHistory;
    }
    private final int RECORDS_IN_PAGE = 20;

    public void getListOfHistory(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        RegistrationDAO rdao = new RegistrationDAO();
        SubjectDAO sdao = new SubjectDAO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select historyID,subjectID,numOfCorrect,totalQuestion, total,createDate "
                        + "from History "
                        + "where email = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String historyID = rs.getString("historyID");
                    int numOfCorrect = rs.getInt("numOfCorrect");
                    int totalQuestion = rs.getInt("totalQuestion");
                    float total = rs.getFloat("total");
                    String subjectID = rs.getString("subjectID");
                    String createDate = rs.getString("createDate");
                    RegistrationDTO rdto = rdao.getRegistrationDTO(email);

                    SubjectDTO sdto = sdao.getSubjectDTO(subjectID);

                    HistoryDTO dto = new HistoryDTO(historyID, rdto, sdto, numOfCorrect,totalQuestion, total, createDate);
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

    public int getNumberOfPage(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int size = 0;
        int numofpages = 0;
        try {
            con = DBHelper.makeConnection();
            String sql = "select count(historyID) as 'size' "
                    + "from History "
                    + "where email = ?";
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                rs = ps.executeQuery();
                if (rs.next()) {
                    size = rs.getInt("size");
                }
            }
            numofpages = (int) Math.ceil(1.0 * size / RECORDS_IN_PAGE);
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
        return numofpages;
    }

    public HistoryDTO getHistoryDTO(String historyID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        HistoryDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select email,subjectID,numOfCorrect,totalQuestion, total,createDate "
                        + "from History "
                        + "where historyID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, historyID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("email");
                    RegistrationDAO rdao = new RegistrationDAO();
                    RegistrationDTO rdto = rdao.getRegistrationDTO(email);

                    String subjectID = rs.getString("subjectID");
                    SubjectDAO sdao = new SubjectDAO();
                    SubjectDTO sdto = sdao.getSubjectDTO(subjectID);

                    int numOfCorrect = rs.getInt("numOfCorrect");
                    int totalQuestion = rs.getInt("totalQuestion");
                    float total = rs.getFloat("total");
                    String createDate = rs.getString("createDate");
                    dto = new HistoryDTO(historyID, rdto, sdto, numOfCorrect,totalQuestion, total, createDate);
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

    public void searchHistory(String searchValue, String email, int pageNo) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        RegistrationDAO rdao = new RegistrationDAO();
        SubjectDAO sdao = new SubjectDAO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select historyID,subjectID,numOfCorrect,totalQuestion, total,createDate "
                        + "from History "
                        + "where email = ? and subjectID in "
                        + "(select subjectID "
                        + "from Subject "
                        + "where subjectName like ? and statusID = 0) "
                        + "order by createDate asc "
                        + "offset ? rows "
                        + "fetch next ? row only";
                ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, "%" + searchValue + "%");
                int dismissRecord = (pageNo - 1) * RECORDS_IN_PAGE;
                ps.setInt(3, dismissRecord);
                ps.setInt(4, RECORDS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String historyID = rs.getString("historyID");
                    int numOfCorrect = rs.getInt("numOfCorrect");
                    int totalQuestion = rs.getInt("totalQuestion");
                    float total = rs.getFloat("total");
                    String subjectID = rs.getString("subjectID");
                    String createDate = rs.getString("createDate");
                    RegistrationDTO rdto = rdao.getRegistrationDTO(email);

                    SubjectDTO sdto = sdao.getSubjectDTO(subjectID);

                    HistoryDTO dto = new HistoryDTO(historyID, rdto, sdto, numOfCorrect,totalQuestion, total, createDate);
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
