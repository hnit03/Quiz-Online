/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.question;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nhinh.subject.SubjectDAO;
import nhinh.subject.SubjectDTO;
import nhinh.utils.DBHelper;

/**
 *
 * @author PC
 */
public class QuestionDAO implements Serializable {

    private List<QuestionDTO> questionList;

    public List<QuestionDTO> getQuestionList() {
        return questionList;
    }

    public void getAllQuestionToTakeQuiz(String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select questionID,questionContent,answer1,answer2,answer3,answer4,answerCorrect,createDate,statusID "
                        + "from Question "
                        + "where subjectID = ? and statusID = 0 "
                        + "ORDER BY NEWID()";
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("questionID");
                    String questionContent = rs.getString("questionContent");
                    String answer1 = rs.getString("answer1");
                    String answer2 = rs.getString("answer2");
                    String answer3 = rs.getString("answer3");
                    String answer4 = rs.getString("answer4");
                    String answerCorrect = rs.getString("answerCorrect");
                    String createDate = rs.getString("createDate");
                    SubjectDAO sdao = new SubjectDAO();
                    SubjectDTO sdto = sdao.getSubjectDTO(subjectID);
                    int statusID = rs.getInt("statusID");
                    QuestionDTO dto = new QuestionDTO(questionID, questionContent, answer1, answer2, answer3, answer4, answerCorrect, createDate, sdto, statusID);
                    if (this.questionList == null) {
                        this.questionList = new ArrayList<>();
                    }
                    this.questionList.add(dto);
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

    public void getAllQuestionByAdmin(String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select questionID,questionContent,answer1,answer2,answer3,answer4,answerCorrect,createDate,statusID "
                        + "from Question "
                        + "where subjectID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("questionID");
                    String questionContent = rs.getString("questionContent");
                    String answer1 = rs.getString("answer1");
                    String answer2 = rs.getString("answer2");
                    String answer3 = rs.getString("answer3");
                    String answer4 = rs.getString("answer4");
                    String answerCorrect = rs.getString("answerCorrect");
                    String createDate = rs.getString("createDate");
                    SubjectDAO sdao = new SubjectDAO();
                    SubjectDTO sdto = sdao.getSubjectDTO(subjectID);
                    int statusID = rs.getInt("statusID");
                    QuestionDTO dto = new QuestionDTO(questionID, questionContent, answer1, answer2, answer3, answer4, answerCorrect, createDate, sdto, statusID);
                    if (this.questionList == null) {
                        this.questionList = new ArrayList<>();
                    }
                    this.questionList.add(dto);
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

    public QuestionDTO getQuestionDTO(String questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        QuestionDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select questionContent,answer1,answer2,answer3,answer4,answerCorrect,createDate,subjectID,statusID "
                        + "from Question "
                        + "where questionID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, questionID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String questionContent = rs.getString("questionContent");
                    String answer1 = rs.getString("answer1");
                    String answer2 = rs.getString("answer2");
                    String answer3 = rs.getString("answer3");
                    String answer4 = rs.getString("answer4");
                    String answerCorrect = rs.getString("answerCorrect");
                    String createDate = rs.getString("createDate");
                    String subjectID = rs.getString("subjectID");
                    SubjectDAO sdao = new SubjectDAO();
                    SubjectDTO sdto = sdao.getSubjectDTO(subjectID);
                    int statusID = rs.getInt("statusID");
                    dto = new QuestionDTO(questionID, questionContent, answer1, answer2, answer3, answer4, answerCorrect, createDate, sdto, statusID);
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

    public String getSubjectFromQuestion(String questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String subjectID = "";
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select subjectID "
                        + "from Question "
                        + "where questionID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, questionID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    subjectID = rs.getString("subjectID");
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
        return subjectID;
    }

    public int getNumberOfQuestion(String subjectID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int number = 0;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select count(questionID) "
                        + "from Question "
                        + "where subjectID = ? and statusID = 0";
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    number = rs.getInt(1);
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
        return number;
    }

    public boolean createNewQuestion(String content, String answer1, String answer2, String answer3, String answer4, String answerCorrect, String createDate,
            String subjectID, int statusID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into Question(questionID,questionContent,answer1,answer2,answer3,answer4,answerCorrect,createDate,subjectID,statusID) "
                        + "values(NEWID(),?,?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, content);
                ps.setString(2, answer1);
                ps.setString(3, answer2);
                ps.setString(4, answer3);
                ps.setString(5, answer4);
                ps.setString(6, answerCorrect);
                ps.setString(7, createDate);
                ps.setString(8, subjectID);
                ps.setInt(9, statusID);

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

    public boolean updateQuestion(QuestionDTO dto) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Question "
                        + "Set questionContent = ?, answer1 = ?,answer2 = ?,answer3 = ?,answer4 = ?,answerCorrect = ?, "
                        + "createDate = ?, subjectID = ?, statusID = ? "
                        + "where questionID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getContent());
                ps.setString(2, dto.getAnswer1());
                ps.setString(3, dto.getAnswer2());
                ps.setString(4, dto.getAnswer3());
                ps.setString(5, dto.getAnswer4());
                ps.setString(6, dto.getAnswerCorrect());
                ps.setString(7, dto.getCreateDate());
                ps.setString(8, dto.getSubjectDTO().getSubjectID());
                ps.setInt(9, dto.getStatusID());
                ps.setString(10, dto.getQuestionID());
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

    public boolean deleteQuestion(String questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Question "
                        + "set statusID = 1 "
                        + "where questionID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, questionID);
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

    public void searchQuestion(String searchValue, int status) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select questionID,questionContent,answer1,answer2,answer3,answer4,answerCorrect,createDate,subjectID,statusID "
                        + "from Question "
                        + "where questionContent like ? and statusID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchValue + "%");
                ps.setInt(2, status );
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("questionID");
                    String questionContent = rs.getString("questionContent");
                    String answer1 = rs.getString("answer1");
                    String answer2 = rs.getString("answer2");
                    String answer3 = rs.getString("answer3");
                    String answer4 = rs.getString("answer4");
                    String answerCorrect = rs.getString("answerCorrect");
                    String createDate = rs.getString("createDate");
                    String subjectID = rs.getString("subjectID");
                    SubjectDAO sdao = new SubjectDAO();
                    SubjectDTO sdto = sdao.getSubjectDTO(subjectID);
                    int statusID = rs.getInt("statusID");
                    if (this.questionList == null) {
                        this.questionList = new ArrayList<>();
                    }
                    QuestionDTO dto = new QuestionDTO(questionID, questionContent, answer1, answer2, answer3, answer4, answerCorrect, createDate, sdto, statusID);
                    this.questionList.add(dto);
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
