/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.answer;

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
public class AnswerDAO implements Serializable {

    public boolean insertAnwser(String questionID, String answerContent,boolean type) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into Answer(answerID,questionID,answerContent,isCorrect) "
                        + "values(NEWID(),?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, questionID);
                ps.setString(2, answerContent);
                ps.setBoolean(3, type);
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

    public boolean deleteAnwser(String answerID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "delete from Answer "
                        + "where answerID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, answerID);
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
    
    private List<AnswerDTO> answerList;

    public List<AnswerDTO> getAnswerList() {
        return answerList;
    }

    public void getAllAnwserList(String questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select answerID,answerContent,isCorrect "
                        + "from Answer "
                        + "where questionID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, questionID);
                rs = ps.executeQuery();
                while (rs.next()) {  
                    String answerID = rs.getString("answerID");
                    String answerContent = rs.getString("answerContent");
                    boolean type = rs.getBoolean("isCorrect");
                    AnswerDTO dto = new AnswerDTO(answerID, questionID, answerContent, type);
                    if (this.answerList == null) {
                        this.answerList = new ArrayList<>();
                    }
                    this.answerList.add(dto);
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
    
    public void removeListElem() throws SQLException, NamingException {
        while (this.answerList.size() > 0) {
            this.answerList.remove(0);
        }
    }
    
    public AnswerDTO getAnwserDTO(String answerID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        AnswerDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select questionID,answerContent,isCorrect "
                        + "from Answer "
                        + "where answerID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, answerID);
                rs = ps.executeQuery();
                if (rs.next()) {                    
                    String questionID = rs.getString("questionID");
                    String answerContent = rs.getString("answerContent");
                    boolean type = rs.getBoolean("isCorrect");
                    dto = new AnswerDTO(answerID, questionID, answerContent, type);
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
