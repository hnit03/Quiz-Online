
package nhinh.historydetails;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class HistoryDetailsDTO implements Serializable{
    
    private String hdID;
    private String historyID;
    private String questionID;
    private String answerChosen;
    private String correct;
    
    public HistoryDetailsDTO() {
    }

    public HistoryDetailsDTO(String hdID,String historyID, String questionID, String answerChosen, String correct) {
        this.hdID = hdID;
        this.historyID = historyID;
        this.questionID = questionID;
        this.answerChosen = answerChosen;
        this.correct = correct;
    }

    /**
     * @return the historyID
     */
    public String getHistoryID() {
        return historyID;
    }

    /**
     * @param historyID the historyID to set
     */
    public void setHistoryID(String historyID) {
        this.historyID = historyID;
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
     * @return the answerChosen
     */
    public String getAnswerChosen() {
        return answerChosen;
    }

    /**
     * @param answerChosen the answerChosen to set
     */
    public void setAnswerChosen(String answerChosen) {
        this.answerChosen = answerChosen;
    }

    /**
     * @return the isCorrect
     */
    public String getCorrect() {
        return correct;
    }

    /**
     * @param isCorrect the isCorrect to set
     */
    public void setCorrect(String correct) {
        this.correct = correct;
    }

    /**
     * @return the hdID
     */
    public String getHdID() {
        return hdID;
    }

    /**
     * @param hdID the hdID to set
     */
    public void setHdID(String hdID) {
        this.hdID = hdID;
    }
    
    
}
