/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WhiteBoxScan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author toanvv1
 */
public class ScanPHP {

    public void checkSQLi(ArrayList<String> ContextFile) {
        boolean check_sqli_1 = false;
        boolean check_sqli_2 = false;
        int line = 0;
        String code_line = null;
        for (int i = 0; i < ContextFile.size(); i++) {
            if (ContextFile.get(i).contains("=") & (ContextFile.get(i).contains("sql") || ContextFile.get(i).contains("query")
                    || ContextFile.get(i).contains("stmt")) & (ContextFile.get(i).contains("$") & ContextFile.get(i).contains("\"\"")
                    || ContextFile.get(i).contains("+"))) {
                check_sqli_1 = true;
                line = i + 1;
                code_line = ContextFile.get(i);
            }

        }

        for (String codeLine : ContextFile) {
            if (codeLine.contains("mysql_query")
                    || codeLine.contains("mssql_query") || codeLine.contains("pg_query")) {
                check_sqli_2 = true;
            }
        }

        if (check_sqli_1 == true || check_sqli_2 == true) {
            System.out.println("Potential SQL Injection. "
                    + "The application appears to allow SQL injection via dynamic SQL statements."
                    + " No validator plug-ins were located in the application's XML files.");
            System.out.println(code_line);
            System.out.println("Code Line: " + line);
        }else{
            System.out.println("Not SQL injection");
        }

    }

    public void checkXSS(ArrayList<String> ContextFile) {
        boolean check_xss_1 = false;
        int line = 0;
        String code_line = null;
        for (int i = 0; i < ContextFile.size(); i++) {
            if ((ContextFile.get(i).contains("GET") || ContextFile.get(i).contains("POST") || ContextFile.get(i).contains("COOKIE")
                    || ContextFile.get(i).contains("REQUEST") || ContextFile.get(i).contains("SERVER")) || (ContextFile.get(i).contains("print")
                    || ContextFile.get(i).contains("echo") || ContextFile.get(i).contains("print_r"))
                    & ContextFile.get(i).contains("strip_tags") == false) {
                check_xss_1 = true;
                line = i +1;
                code_line = ContextFile.get(i);
            }
        }
        if (check_xss_1 == true) {
            System.out.println("Potential XSS. "
                    + "The application appears to reflect a user-supplied variable"
                    + " to the screen with no apparent validation or sanitisation.");
            System.out.println(code_line);
            System.out.println("Code Line: " + line);
        }else{
            System.out.println("Not XSS");
        }
    }
    
//    public static void main(String[] args) {
//        ArrayList<String> ContextFile = new ArrayList<>();
//        try (FileReader reader = new FileReader("C:\\Users\\toanvv1\\Downloads\\DVWA-master\\dvwa\\vulnerabilities\\xss_r\\source\\low.php");
//             BufferedReader br = new BufferedReader(reader)) {
//
//            // read line by line
//            String line;
//            while ((line = br.readLine()) != null) {
//                ContextFile.add(line);
//            }
//            
//            checkSQLi(ContextFile);
//            checkXSS(ContextFile);
//
//        } catch (IOException e) {
//            System.err.format("IOException: %s%n", e);
//        }
//    }

}
