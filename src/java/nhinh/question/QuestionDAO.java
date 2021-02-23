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
import nhinh.answer.AnswerDAO;
import nhinh.answer.AnswerDTO;
import nhinh.subject.SubjectDAO;
import nhinh.subject.SubjectDTO;
import nhinh.utils.DBHelper;

/**
 *
 * @author PC
 */
public class QuestionDAO implements Serializable {

    private final int RECORDS_IN_PAGE = 20;
    private List<QuestionDTO> questionList;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public List<QuestionDTO> getQuestionList() {
        return questionList;
    }

    public void getAllQuestionToTakeQuiz(String subjectID) throws SQLException, NamingException {
        AnswerDAO adao = new AnswerDAO();
        SubjectDAO sdao = new SubjectDAO();

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select questionID,questionContent,createDate,statusID,creator "
                        + "from Question "
                        + "where subjectID = ? and statusID = 0 "
                        + "ORDER BY NEWID()";
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("questionID");
                    String questionContent = rs.getString("questionContent");
                    String createDate = rs.getString("createDate");
                    SubjectDTO sdto = sdao.getSubjectDTO(subjectID);
                    int statusID = rs.getInt("statusID");
                    adao.getAllAnwserList(questionID);
                    List<AnswerDTO> list = adao.getAnswerList();
                    List<AnswerDTO> listAnswer = new ArrayList<>();
                    if (list != null) {
                        for (AnswerDTO answerDTO : list) {
                            listAnswer.add(answerDTO);
                        }
                    }
                    String creator = rs.getString("creator");
                    QuestionDTO dto = new QuestionDTO(questionID, questionContent, createDate, sdto, statusID,listAnswer, creator);
                    if (this.questionList == null) {
                        this.questionList = new ArrayList<>();
                    }
                    this.questionList.add(dto);
                    adao.removeListElem();
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
        SubjectDAO sdao = new SubjectDAO();
        AnswerDAO adao = new AnswerDAO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select questionID,questionContent,createDate,statusID,creator "
                        + "from Question "
                        + "where subjectID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("questionID");
                    String content = rs.getString("questionContent");
                    String createDate = rs.getString("createDate");
                    SubjectDTO sdto = sdao.getSubjectDTO(subjectID);
                    int statusID = rs.getInt("statusID");
                    adao.getAllAnwserList(questionID);
                    List<AnswerDTO> list = adao.getAnswerList();
                    List<AnswerDTO> listAnswer = new ArrayList<>();
                    if (list != null) {
                        for (AnswerDTO answerDTO : list) {
                            listAnswer.add(answerDTO);
                        }
                    }
                    String creator = rs.getString("creator");
                    QuestionDTO dto = new QuestionDTO(questionID, content, createDate, sdto, statusID,listAnswer, creator);
                    if (this.questionList == null) {
                        this.questionList = new ArrayList<>();
                    }
                    this.questionList.add(dto);
                    adao.removeListElem();
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
        AnswerDAO adao = new AnswerDAO();
        QuestionDTO dto = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select questionContent,createDate,subjectID,statusID "
                        + "from Question "
                        + "where questionID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, questionID);
                rs = ps.executeQuery();
                if (rs.next()) {
                    String questionContent = rs.getString("questionContent");
                    String createDate = rs.getString("createDate");
                    String subjectID = rs.getString("subjectID");
                    SubjectDAO sdao = new SubjectDAO();
                    SubjectDTO sdto = sdao.getSubjectDTO(subjectID);
                    int statusID = rs.getInt("statusID");
                    adao.getAllAnwserList(questionID);
                    adao.getAllAnwserList(questionID);
                    List<AnswerDTO> list = adao.getAnswerList();
                    List<AnswerDTO> listAnswer = new ArrayList<>();
                    if (list != null) {
                        for (AnswerDTO answerDTO : list) {
                            listAnswer.add(answerDTO);
                        }
                    }
                    String creator = rs.getString("creator");
                    dto = new QuestionDTO(questionID, questionContent, createDate, sdto, statusID,listAnswer, creator);
                    adao.removeListElem();
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

    public boolean createNewQuestion(String content, String createDate,
            String subjectID, int statusID, String creator) throws SQLException, NamingException {
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into Question(questionID,questionContent,createDate,subjectID,statusID,creator) "
                        + "values(NEWID(),?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, content);
                ps.setString(2, createDate);
                ps.setString(3, subjectID);
                ps.setInt(4, statusID);
                ps.setString(5, creator);
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

    public boolean updateQuestion(String questionContent, String createDate, String subjectID, int statusID, String questionID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Update Question "
                        + "Set questionContent = ?, "
                        + "createDate = ?, subjectID = ?, statusID = ? "
                        + "where questionID = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, questionContent);
                ps.setString(2, createDate);
                ps.setString(3, subjectID);
                ps.setInt(4, statusID);
                ps.setString(5, questionID);
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

    public void searchQuestion(String searchValue, int status, int pageNo) throws SQLException, NamingException {
        SubjectDAO sdao = new SubjectDAO();
        AnswerDAO adao = new AnswerDAO();
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select questionID,questionContent,createDate,subjectID,statusID,creator "
                        + "from Question "
                        + "where questionContent like ? and statusID = ? "
                        + "ORDER BY createDate ASC "
                        + "offset ? rows "
                        + "fetch next ? row only";
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchValue + "%");
                ps.setInt(2, status);
                int dismissRecord = (pageNo - 1) * RECORDS_IN_PAGE;
                ps.setInt(3, dismissRecord);
                ps.setInt(4, RECORDS_IN_PAGE);
                rs = ps.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("questionID");
                    String questionContent = rs.getString("questionContent");
                    String createDate = rs.getString("createDate");
                    String subjectID = rs.getString("subjectID");
                    SubjectDTO sdto = sdao.getSubjectDTO(subjectID);
                    int statusID = rs.getInt("statusID");
                    adao.getAllAnwserList(questionID);
                    List<AnswerDTO> list = adao.getAnswerList();
                    List<AnswerDTO> listAnswer = new ArrayList<>();
                    if (list != null) {
                        for (AnswerDTO answerDTO : list) {
                            listAnswer.add(answerDTO);
                        }
                    }
                    String creator = rs.getString("creator");
                    QuestionDTO dto = new QuestionDTO(questionID, questionContent, createDate, sdto, statusID,listAnswer, creator);
                    if (this.questionList == null) {
                        this.questionList = new ArrayList<>();
                    }
                    this.questionList.add(dto);
                    adao.removeListElem();
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

    public String getLastQuestionID(String subjectID, String createDate, String creator) throws SQLException, NamingException {
        String questionID = "";
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "select questionID "
                        + "from Question "
                        + "where subjectID = ? and createDate = ? and creator = ? ";
                ps = con.prepareStatement(sql);
                ps.setString(1, subjectID);
                ps.setString(2, createDate);
                ps.setString(3, creator);
                rs = ps.executeQuery();
                if (rs.next()) {
                    questionID = rs.getString("questionID");
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
        return questionID;
    }

    public int getNumberOfPage(String searchValue, int status) throws SQLException, NamingException {
        int size = 0;
        int numofpages = 0;
        try {
            con = DBHelper.makeConnection();
            String sql = "select count(questionID) as 'size' "
                    + "from Question "
                    + "where questionContent like ? and statusID = ? ";
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, "%" + searchValue + "%");
                ps.setInt(2, status);
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
}
