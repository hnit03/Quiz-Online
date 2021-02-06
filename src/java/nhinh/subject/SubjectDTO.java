/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.subject;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class SubjectDTO implements Serializable{
    
    private String subjectID;
    private String subjectName;
    private String time;

    public SubjectDTO() {
    }

    public SubjectDTO(String subjectID, String subjectName, String time) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.time = time;
    }

    /**
     * @return the subjectID
     */
    public String getSubjectID() {
        return subjectID;
    }

    /**
     * @param subjectID the subjectID to set
     */
    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    /**
     * @return the subjectName
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * @param subjectName the subjectName to set
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }
    
    
    
    
}
