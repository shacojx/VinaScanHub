/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WhiteBoxScan;

import java.util.ArrayList;

/**
 *
 * @author toanvv1
 */
public class ScanJava {

    public void CheckServlet(ArrayList<String> ContextFile) {
        boolean check_servlet = false;

        for (String codeLine : ContextFile) {
            if (check_servlet == false & (codeLine.contains("extends HttpServlet") || codeLine.contains("implements Servlet"))) {
                check_servlet = true;
            }
        }

        for (int i = 0; i < ContextFile.size(); i++) {
            if (check_servlet == true & ContextFile.get(i).contains("Thread.sleep")) {
                System.out.print("Use of Thread.sleep() within a Java servlet");
                System.out.println("The use of Thread.sleep() within Java servlets is discouraged");
                System.out.println(ContextFile.get(i));
                System.out.println("Code Line: " + i + 1);
            }
        }

    }

    public void CheckSQLi(ArrayList<String> ContextFile) {

        for (int i = 0; i < ContextFile.size(); i++) {
            if (ContextFile.get(i).contains("=") & (ContextFile.get(i).contains("sql") || ContextFile.get(i).contains("query"))
                    & (ContextFile.get(i).contains("\"\"") & ContextFile.get(i).contains("+"))) {
                System.out.println("Potential SQL Injection. "
                        + "The application appears to allow SQL injection via dynamic SQL statements."
                        + " No validator plug-ins were located in the application's XML files.");
                System.out.println(ContextFile.get(i));
                System.out.println("Code Line: " + i + 1);
            }
            if (ContextFile.get(i).contains("prepareStatement") || ContextFile.get(i).contains("executeQuery")
                    || ContextFile.get(i).contains("query") || ContextFile.get(i).contains("queryForObject")
                    || ContextFile.get(i).contains("queryForList") || ContextFile.get(i).contains("queryForInt")
                    || ContextFile.get(i).contains("queryForMap") || ContextFile.get(i).contains("update")
                    || ContextFile.get(i).contains("getQueryString") || ContextFile.get(i).contains("executeQuery")
                    || ContextFile.get(i).contains("createNativeQuery") || ContextFile.get(i).contains("createQuery")) {
                if (ContextFile.get(i).contains("\"\"") & ContextFile.get(i).contains("+")) {
                    System.out.println("Potential SQL Injection. "
                            + "The application appears to allow SQL injection via dynamic SQL statements."
                            + " No validator plug-ins were located in the application's XML files.");
                    System.out.println(ContextFile.get(i));
                    System.out.println("Code Line: " + i + 1);
                }
            }
        }
    }
    
    public void CheckXSS(ArrayList<String> ContextFile){
        
    }

}
