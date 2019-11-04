/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Scan;

import function.AttackGet;
import function.AttackPost;
import function.LoadData;
import function.Scan;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author toanvv1
 */
public class Scan_SQLi {

    public void ScanSQLi(Element element, String urlAction) throws IOException {
        AttackGet ag = new AttackGet();
        AttackPost ap = new AttackPost();
        LoadData data = new LoadData();
        String vuln = "SQL injection";
        ag.attackGet(urlAction, data.pay_SQLi, data.sig_SQLi, vuln);
        ap.attackPost(element, urlAction, data.pay_SQLi, data.sig_SQLi, vuln);
    }
    
    public void attackGet(String urlAction) throws IOException {
        Scan scan = new Scan();
        LoadData data = new LoadData();
        String fURL = urlAction.split("\\?")[0];
        String lURL = urlAction.split("\\?")[1];

        Map<String, String> mapParemeter;
        boolean checkVuln = false;

        for (String payload : data.pay_SQLi) {
            try {
                mapParemeter = new HashMap<>();
                for (String s : lURL.split("&")) {
                    String key = s.split("\\=")[0];
                    String value = "";
                    try {
                        value = s.split("\\=")[1] + payload;
                    } catch (Exception e) {
                        System.out.println("Error attack Get: " + e);
                    }
                    mapParemeter.put(key, value);
                }

                Document doc = Jsoup.connect(fURL).data(mapParemeter).userAgent("Mozilla").followRedirects(false).get();

                for (String signature : data.sig_SQLi) {
                    if (doc.body().toString().contains(signature)) {
                        checkVuln = true;
                        System.out.println("GET SQL injection : " + urlAction);
                        System.out.println("Pay = " + payload);
                        System.out.println("Sig = " + signature);
                        scan.list_vuln.add("GET SQL injection : " + urlAction);
                        break;
                    }
                }
                if (checkVuln) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error attack Get: " + urlAction + " ||| " + e);
            }
        }
    }

}
