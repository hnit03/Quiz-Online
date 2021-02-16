/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhinh.utils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author PC
 */
public class Utils {
    public String formatTimeToString(Time time) {
        SimpleDateFormat df = new SimpleDateFormat("mm-ss");
        String timeStr = df.format(time);
        return timeStr;
    }
    public String formatDateTimeToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        String dateFormat = df.format(date);
        return dateFormat;
    }
    public String formatStringToDate(String strDate) {
        java.sql.Date date = java.sql.Date.valueOf(strDate);
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String dateFormat = df.format(date);
        return dateFormat;
    }
    public String formatDateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String dateFormat = df.format(date);
        return dateFormat;
    }
}
