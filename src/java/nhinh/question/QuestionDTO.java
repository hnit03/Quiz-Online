/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.question;

import java.io.Serializable;
import java.util.List;
import nhinh.answer.AnswerDTO;
import nhinh.subject.SubjectDTO;

/**
 *
 * @author PC
 */
public class QuestionDTO implements Serializable{
    
    private String questionID;
    private String content;
    private String createDate;
    private SubjectDTO subjectDTO;
    private int statusID;
    private List<AnswerDTO> answerList;
    private String creator;
    public QuestionDTO() {
    }

    public QuestionDTO(String questionID, String content, String createDate, SubjectDTO subjectDTO, int statusID,List<AnswerDTO> answerList,String creator) {
        this.questionID = questionID;
        this.content = content;
        this.createDate = createDate;
        this.subjectDTO = subjectDTO;
        this.statusID = statusID;
        this.answerList = answerList;
        this.creator = creator;
    }

    /**
     * @return the questionID
     */
    public String getQuestionID() {
        return questionID;
    }

    /**
     * @param questionID the questionID to set
     */
    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the createDate
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the subjectDTO
     */
    public SubjectDTO getSubjectDTO() {
        return subjectDTO;
    }

    /**
     * @param subjectDTO the subjectDTO to set
     */
    public void setSubjectDTO(SubjectDTO subjectDTO) {
        this.subjectDTO = subjectDTO;
    }

    /**
     * @return the statusID
     */
    public int getStatusID() {
        return statusID;
    }

    /**
     * @param statusID the statusID to set
     */
    public void setStatusID(int statusID) {
        this.statusID = statusID;
    }

    /**
     * @return the answerList
     */
    public List<AnswerDTO> getAnswerList() {
        return answerList;
    }

    /**
     * @param answerList the answerList to set
     */
    public void setAnswerList(List<AnswerDTO> answerList) {
        this.answerList = answerList;
    }

    /**
     * @return the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator the creator to set
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    
}
