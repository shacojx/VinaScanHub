/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import payload.A1p;
import payload.A3p;
import signature.A1;
import signature.A3;

/**
 *
 * @author Shaco JX
 */
public class Scan {

    public void Scan(HashSet<String> list) throws IOException {
        System.out.println("[ Checking Vuln Top 10 OWASP ]");
        System.out.println("Please waiting... Checking ...");
        A1 sigA1 = new A1();
        A3 sigA3 = new A3();
        A1p payA1 = new A1p();
        A3p payA3 = new A3p();
        checkVuln(payA1.SQLinjection(), sigA1.SQLinjection(), "SQL injection", list);
        checkVuln(payA1.HTMLinjection(), sigA1.HTMLinjection(), "HTML injection", list);
        checkVuln(payA1.XMLXPathInjection(), sigA1.XMLXPathInjection(), "XML/XPath injection", list);
        checkVuln(payA1.IFrameInjection(), sigA1.IFrameInjection(), "IFrame injection", list);
        checkVuln(payA3.XSS(), sigA3.XSS(), "XSS", list);
        System.out.println("=== STOPED ===");

    }

    public void checkVuln(ArrayList<String> listPay, ArrayList<String> listSig, String vulnName, HashSet<String> list) throws IOException {
        String vuln = "";
        removeDup remove = new removeDup();
        ArrayList<String> list_vuln = new ArrayList<>();
        ArrayList<String> list_tmp = new ArrayList<>();
        for (String x : list) {
            if (x.contains("?") && x.contains("=") && !x.contains("jpg")) {
                list_tmp.add(x.split("=")[0]);
            }
        }
        ArrayList<String> list_point = remove.removeDuplicates(list_tmp);
        for (String x : list_point) {
            vuln = "";
            for (String y : listPay) {
                for (String z : listSig) {
//                        System.out.println("check " + x + y + z);
                    Connection.Response respx = (Connection.Response) Jsoup.connect(x+"=1" + y).execute();
                    Document docx = respx.parse();
                    String bodyx = docx.body().toString();
                    if (bodyx.contains(z)) {
                        list_vuln.add(x+"=");
                        vuln = "vuln";
                        break;
                    }
                }
                if (!vuln.equals("")) {
                    break;
                }
            }

        }

        ArrayList<String> list_entrypoint = remove.removeDuplicates(list_vuln);

        for (String x : list_entrypoint) {
            System.out.println(vulnName + " : " + x);
        }

    }

}
