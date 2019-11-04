/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import payload.pSQLi;
import signature.sSQLi;

/**
 *
 * @author toanvv1
 */
public class AttackGet {

    public void attackGet(String urlAction, ArrayList<String> ListPay, ArrayList<String> ListSig, String vulnName) throws IOException {
        Scan scan = new Scan();
        String fURL = urlAction.split("\\?")[0];
        String lURL = urlAction.split("\\?")[1];

        Map<String, String> mapParemeter;
        boolean checkVuln = false;

        for (String payload : ListPay) {
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

                for (String signature : ListSig) {
                    if (doc.body().toString().contains(signature)) {
                        checkVuln = true;
                        System.out.println("GET " + vulnName + " : " + urlAction);
                        System.out.println("Pay = " + payload);
                        System.out.println("Sig = " + signature);
                        scan.list_vuln.add("GET " + vulnName + " : " + urlAction);
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
