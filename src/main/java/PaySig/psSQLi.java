/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PaySig;

/**
 *
 * @author laxba
 */
public class psSQLi {

    private static String[] arrSigSQLin;
    private static String[] arrPaySQLin;

    public void loadDataSQLinjection() {
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
}
