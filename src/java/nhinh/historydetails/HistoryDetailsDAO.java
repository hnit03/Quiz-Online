/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.historydetails;

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
public class HistoryDetailsDAO implements Serializable {

    public boolean insertHistoryDetails(String historyID, String questionID, String answerChosen, String correct) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into HistoryDetails(hdID,historyID,questionID,answerChosen,isCorrect) "
                        + "values(NEWID(),?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, historyID);
                ps.setString(2, questionID);
                ps.setString(3, answerChosen);
                ps.setString(4, correct);
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

    public HistoryDetailsDTO getHistoryDetails(String historyID, String questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        HistoryDetailsDTO hddto = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select hdID,answerChosen,isCorrect "
                        + "from HistoryDetails "
                        + "where historyID = ? and questionID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, historyID);
                ps.setString(2, questionID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String hdID = rs.getString("hdID");
                    String answerChosen = rs.getString("answerChosen");
                    String correct = rs.getString("isCorrect");
                    hddto = new HistoryDetailsDTO(hdID,historyID, questionID, answerChosen, correct);
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
        return hddto;
    }
}
