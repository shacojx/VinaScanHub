/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author toanvv1
 */
public class MyScan {

    private Date date;
    private String url;
    private int total_vuln;
    private ArrayList<String> vuln;

    public MyScan() {
    }

    public MyScan(Date date, String url, int total_vuln, ArrayList<String> vuln) {
        this.date = date;
        this.url = url;
        this.total_vuln = total_vuln;
        this.vuln = vuln;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTotal_vuln() {
        return total_vuln;
    }

    public void setTotal_vuln(int total_vuln) {
        this.total_vuln = total_vuln;
    }

    public ArrayList<String> getVuln() {
        return vuln;
    }

    public void setVuln(ArrayList<String> vuln) {
        this.vuln = vuln;
    }

    @Override
    public String toString() {
        return "MyScan{" + "date=" + date + ", url=" + url + ", total_vuln=" + total_vuln + ", vuln=" + vuln + '}';
    }

}
