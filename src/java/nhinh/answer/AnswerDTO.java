/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.answer;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class AnswerDTO implements Serializable{
    
    private String answerID;
    private String questionID;
    private String answerContent;
    private boolean type;
    
    public AnswerDTO() {
    }

    public AnswerDTO(String answerID, String questionID, String answerContent,boolean type) {
        this.answerID = answerID;
        this.questionID = questionID;
        this.answerContent = answerContent;
        this.type = type;
    }

    /**
     * @return the answerID
     */
    public String getAnswerID() {
        return answerID;
    }

    /**
     * @param answerID the answerID to set
     */
    public void setAnswerID(String answerID) {
        this.answerID = answerID;
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
     * @return the answerContent
     */
    public String getAnswerContent() {
        return answerContent;
    }

    /**
     * @param answerContent the answerContent to set
     */
    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    /**
     * @return the type
     */
    public boolean isType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(boolean type) {
        this.type = type;
    }
    
    
}
