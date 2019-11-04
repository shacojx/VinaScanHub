/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signature;

import java.util.ArrayList;

/**
 *
 * @author toanvv1
 */
public class sSQLi {
    public ArrayList<String> SQLinjection() {
        ArrayList<String> list = new ArrayList<>();
        list.add("You have an error in your SQL syntax");
        list.add("supplied argument is not a valid MySQL");
        list.add("mysql_fetch_array() expects parameter 1 to be resource, boolean given in");
        list.add("java.sql.SQLException: Syntax error or access violation");
        list.add("java.sql.SQLException: Unexpected end of command");
        list.add("PostgreSQL query failed: ERROR: parser:");
        list.add("XPathException");
        list.add("Warning: SimpleXMLElement::xpath():");
        list.add("[Microsoft][ODBC SQL Server Driver]");
        list.add("Microsoft OLE DB Provider for ODBC Drivers");
        list.add("[Microsoft][ODBC Microsoft Access Driver]");
        list.add("supplied argument is not a valid ldap");
        list.add("DB2 SQL error:");
        list.add("Interbase Injection");
        list.add("Sybase message:");
        list.add("Unclosed quotation mark after the character string");
        return list;
    }
}
