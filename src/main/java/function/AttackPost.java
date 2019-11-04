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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author toanvv1
 */
public class AttackPost {

    public void attackPost(Element element, String urlAction, ArrayList<String> ListPay, ArrayList<String> ListSig, String vulnName) throws IOException {
        Scan s = new Scan();
        Map<String, String> map = new HashMap<String, String>();
        Elements ele = null;
        boolean checkVuln = false;

        for (String payload : ListPay) {
            try {
                map = new HashMap<String, String>();
                ele = element.getElementsByAttribute("name");
                for (Element e1 : ele) {
                    if (!e1.attr("type").contains("submit") && !e1.attr("type").contains("button")) {
                        map.put(e1.attr("name"), payload);
                    } else {
                        if (e1.attr("name").length() != 0) {
                            map.put(e1.attr("name"), e1.attr("value"));
                        }
                    }
                }

                Document doc = null;
                if (element.attr("method").contains("post")) {
                    doc = Jsoup.connect(urlAction).data(map).userAgent("Mozilla").followRedirects(false).post();
                } else {
                    doc = Jsoup.connect(urlAction).data(map).userAgent("Mozilla").followRedirects(false).get();
                }

                for (String signature : ListSig) {
                    if (doc.body().toString().contains(signature)) {
                        checkVuln = true;
                        System.out.println(element.attr("method").toUpperCase() + " " + vulnName + " : " + element.attr("abs:action"));
                        System.out.println("Pay = " + payload);
                        System.out.println("Sig = " + signature);
                        s.list_vuln.add(element.attr("method").toUpperCase() + " " + vulnName + " : " + element.attr("abs:action"));
                        break;
                    }
                }
                if (checkVuln) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error attack Post: " + e);
            }
        }
    }
}
