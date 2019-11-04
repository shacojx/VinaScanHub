/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.util.ArrayList;
import payload.*;
import signature.*;

/**
 *
 * @author toanvv1
 */
public class LoadData {

    public ArrayList<String> pay_SQLi = new ArrayList<>();
    public ArrayList<String> pay_XSS = new ArrayList<>();
    public ArrayList<String> pay_xMLXpatchi = new ArrayList<>();
    public ArrayList<String> pay_HTMLi = new ArrayList<>();

    public ArrayList<String> sig_HTMLi = new ArrayList<>();
    public ArrayList<String> sig_SQLi = new ArrayList<>();
    public ArrayList<String> sig_XMLXpatchi = new ArrayList<>();
    public ArrayList<String> sig_XSS = new ArrayList<>();

    
    public void LoadPayload() {
        pHTMLi pHTMLi = new pHTMLi();
        pSQLi pSQLi = new pSQLi();
        pXMLXpatchi pXMLXpatchi = new pXMLXpatchi();
        pXSS pXSS = new pXSS();
        pay_HTMLi = pHTMLi.HTMLinjection();
        pay_SQLi = pSQLi.SQLinjection();
        pay_xMLXpatchi = pXMLXpatchi.XMLXPathInjection();
        pay_XSS = pXSS.XSS();
        System.out.println("Load Payload successful!");
    }

    public void LoadSignature() {
        sHTMLi sHTMLi = new sHTMLi();
        sSQLi sSQLi = new sSQLi();
        sXMLXpatchi sXMLXpathi = new sXMLXpatchi();
        sXSS sXSS = new sXSS();
        sig_HTMLi = sHTMLi.HTMLinjection();
        sig_SQLi = sSQLi.SQLinjection();
        sig_XMLXpatchi = sXMLXpathi.XMLXPathInjection();
        sig_XSS = sXSS.XSS();
        System.out.println("Load Signature successful!");
    }
}
