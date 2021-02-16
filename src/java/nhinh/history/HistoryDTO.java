/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.history;

import java.io.Serializable;
import nhinh.registration.RegistrationDTO;
import nhinh.subject.SubjectDTO;

/**
 *
 * @author PC
 */
public class HistoryDTO implements Serializable {

    private String historyID;
    private RegistrationDTO rdto;
    private SubjectDTO sdto;
    private int numOfCorrect;
    private float totalPoint;
    private String createDate;

    public HistoryDTO() {
    }

    public HistoryDTO(String historyID, RegistrationDTO rdto, SubjectDTO sdto, int numOfCorrect, float totalPoint, String createDate) {
        this.historyID = historyID;
        this.rdto = rdto;
        this.sdto = sdto;
        this.numOfCorrect = numOfCorrect;
        this.totalPoint = totalPoint;
        this.createDate = createDate;
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
     * @return the rdto
     */
    public RegistrationDTO getRdto() {
        return rdto;
    }

    /**
     * @param rdto the rdto to set
     */
    public void setRdto(RegistrationDTO rdto) {
        this.rdto = rdto;
    }

    /**
     * @return the sdto
     */
    public SubjectDTO getSdto() {
        return sdto;
    }

    /**
     * @param sdto the sdto to set
     */
    public void setSdto(SubjectDTO sdto) {
        this.sdto = sdto;
    }

    /**
     * @return the numOfCorrect
     */
    public int getNumOfCorrect() {
        return numOfCorrect;
    }

    /**
     * @param numOfCorrect the numOfCorrect to set
     */
    public void setNumOfCorrect(int numOfCorrect) {
        this.numOfCorrect = numOfCorrect;
    }

    /**
     * @return the totalPoint
     */
    public float getTotalPoint() {
        return totalPoint;
    }

    /**
     * @param totalPoint the totalPoint to set
     */
    public void setTotalPoint(float totalPoint) {
        this.totalPoint = totalPoint;
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

    
}
